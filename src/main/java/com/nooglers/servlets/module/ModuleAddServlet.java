package com.nooglers.servlets.module;

import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet( name = "ModuleServlet", urlPatterns = "/addModule" )
public class ModuleAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = ( int ) session.getAttribute("user_id");
        request.setAttribute("userId" , userId);
        request.setAttribute("fid" , request.getParameter("fid"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/module/add.jsp");
        requestDispatcher.forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        User user = new User();
        user.setId(Integer.valueOf(userId));
        System.out.println("userId  post " + userId);

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String aPublic = Objects.requireNonNullElse(request.getParameter("radio") , "public");
        boolean isPublic = aPublic.equals("public");
        final String folderId = request.getParameter("fid");


        Module module = Module.builder().name(name).description(description).createdBy(user).isPublic(isPublic).build();
        ModuleDao dao = ModuleDao.getInstance();
        dao.save(module , folderId);

        if ( folderId.isBlank() ) {
            request.setAttribute("moduleId" , module.getId());
            request.setAttribute("moduleName" , module.getName());
            request.setAttribute("module" , module);
            request.setAttribute("userId" , userId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/module/get.jsp");
            requestDispatcher.forward(request , response);
        } else {
            response.sendRedirect("/folder/getfolder?fid=" + folderId);
        }


    }
}
