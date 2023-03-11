package com.nooglers.filters;

import com.nooglers.dao.UserDao;
import com.nooglers.domains.AppCookie;
import com.nooglers.utils.AES;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@WebFilter( filterName = "HasCookieFilter", urlPatterns = "/*" )
public class HasCookieFilter implements Filter {
    private static final List<String> WHITE_LIST = List.of(
//            "/" ,
//            "/home" ,
            "/login" , "/signup" , "/resources/.+" , "/css/.+");
    private static final Predicate<String> isSecure = (uri) -> {
        for ( String item : WHITE_LIST )
            if ( uri.matches(item) ) return true;
        return false;
    };

    @Override
    public void doFilter(ServletRequest req , ServletResponse res , FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = ( HttpServletRequest ) req;
        HttpServletResponse response = ( HttpServletResponse ) res;
        String requestURI = request.getRequestURI();
        final UserDao userDao = UserDao.getInstance();


        if ( isSecure.test(requestURI) ) {
            chain.doFilter(req , res);
        } else {
            Cookie[] cookies = request.getCookies();
            if ( Objects.isNull(cookies) ) {
                response.sendRedirect("/login?next=" + requestURI);
            } else {
                Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("remember_me")).findAny().ifPresentOrElse(cookie -> {
                            try {

                                final AppCookie uCookie = userDao.getCookie(cookie);

                                if ( uCookie != null ) {
                                     request.getSession().setAttribute("user_id" , uCookie.getUser().getId());
                                    chain.doFilter(req , res);
                                } else {
                                    response.sendRedirect("/login?next=" + requestURI);
                                }


                            } catch ( IOException | ServletException e ) {
                                e.printStackTrace();
                            }
                        } , () -> {
                            try {
                                response.sendRedirect("/login?next=" + requestURI);
                            } catch ( IOException e ) {
                                e.printStackTrace();
                            }
                        }

                );
            }
        }
    }
}
