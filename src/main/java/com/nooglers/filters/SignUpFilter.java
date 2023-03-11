package com.nooglers.filters;

import com.nooglers.dao.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@WebFilter(filterName = "SignUpFilter", urlPatterns = {"/signup"})
public class SignUpFilter implements Filter {
    private ConcurrentHashMap<String, String> SIGN_UP_FILTER = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        UserDao userDao = UserDao.getInstance();
        String signupemail = request.getParameter("signupemail");
        String signupusername = request.getParameter("signupusername");
        String signuppassword = request.getParameter("signuppassword");
        if (signupemail == null || signupemail.isBlank()) {
            SIGN_UP_FILTER.put("signupemail_error", "Email can not be empty");
        } else if (!Objects.isNull(userDao.get(signupemail))) {
            SIGN_UP_FILTER.put("signupemail_error", String.format("User with email %s already exist", signupemail));
        }

        if (signupusername == null || signupusername.isBlank()) {
            SIGN_UP_FILTER.put("signupusername_error", "Username can not be empty");
        } else if (!Objects.isNull(userDao.get(signupusername))) {
            SIGN_UP_FILTER.put("signupusername_error", String.format("User with username %s already exist", signupusername));
        }

        if (signuppassword == null || signuppassword.isBlank()) {
            SIGN_UP_FILTER.put("signuppassword_error", "Password can not be empty");
        }

        if (SIGN_UP_FILTER.size() > 0) {
            request.setAttribute("signup_credentials", SIGN_UP_FILTER);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/auth/register.jsp");
            dispatcher.forward(request, response);
            SIGN_UP_FILTER = new ConcurrentHashMap<>();
        } else {
            chain.doFilter(req, res);
        }
    }
}
