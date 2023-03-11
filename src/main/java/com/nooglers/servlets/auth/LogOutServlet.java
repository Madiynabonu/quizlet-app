package com.nooglers.servlets.auth;

import com.nooglers.dao.CookieDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@WebServlet( name = "LogOutServlet", value = "/logout" )
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        CookieDao cookieDao = CookieDao.getInstance();
        final Integer userId = ( Integer ) request.getSession().getAttribute("user_id");
        final boolean b = cookieDao.removeCookie(userId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/auth/login.jsp");
        dispatcher.forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

    }
}
