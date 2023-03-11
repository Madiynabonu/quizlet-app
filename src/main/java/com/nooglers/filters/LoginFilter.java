package com.nooglers.filters;

import com.nooglers.dao.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

//@WebFilter(filterName = "LoginFilter",urlPatterns = "/login")
public class LoginFilter implements Filter {
    private static final ConcurrentHashMap<String, String> LOGIN_FILTER = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest req , ServletResponse res , FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = ( HttpServletRequest ) req;
        HttpServletResponse response = ( HttpServletResponse ) res;
        UserDao userDao = UserDao.getInstance();
        String loginemail = request.getParameter("loginemail");
        String loginpassword = request.getParameter("loginpassword");
        if ( loginemail == null || loginemail.isBlank() ) {
            LOGIN_FILTER.put("loginemail_error" , "Email can not be empty");
        } else if ( !Objects.isNull(userDao.get(loginemail)) ) {
            LOGIN_FILTER.put("loginemail_error" , String.format("User with email %s already exist" , loginemail));
        }

        if ( loginpassword == null || loginpassword.isBlank() ) {
            LOGIN_FILTER.put("loginpassword_error" , "Password can not be empty");
        }

        if ( LOGIN_FILTER.size() > 0 ) {
            request.setAttribute("login_credentials" , LOGIN_FILTER);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/auth/login.jsp");
            dispatcher.forward(request , response);
        } else {
            chain.doFilter(req , res);
        }
    }
}
