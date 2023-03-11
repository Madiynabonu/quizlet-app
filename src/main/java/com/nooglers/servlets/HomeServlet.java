package com.nooglers.servlets;

import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Module;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet( name = "HomeServlet", value = "/home" )
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        ModuleDao moduleDao = ModuleDao.getInstance();
        final Integer userId = ( Integer ) request.getSession().getAttribute("user_id");
        final List<Module> modules = moduleDao.findAllRecentModulesByUser(userId);
        request.setAttribute("modules" , modules.subList(0, Math.min(modules.size() , 2)));
        request.getRequestDispatcher("/home.jsp").
                forward(request , response);

    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        response.sendError(405 , "Method not allowed");
    }
}
