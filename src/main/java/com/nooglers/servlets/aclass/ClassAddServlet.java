package com.nooglers.servlets.aclass;

import com.nooglers.dao.ClassDao;
import com.nooglers.domains.Class;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@WebServlet( name = "ClassAddServlet", value = "/group/add" )
public class ClassAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/group/add.jsp");
        dispatcher.forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();

        String classname = request.getParameter("classname");
        String schoolname = request.getParameter("schoolname");
        String description = request.getParameter("description");
        String updatepermission = request.getParameter("updatepermission");
        String invitepermission = request.getParameter("invitepermission");
        HttpSession session = request.getSession();
        Integer userId = ( Integer ) session.getAttribute("user_id");
        Class aClass = Class.builder()
                .createdBy(userId)
                .name(classname)
                .schoolName(schoolname)
                .description(description)
                .permissionToInvite(!Objects.isNull(invitepermission) && invitepermission.equalsIgnoreCase("on"))
                .permissionToUpdateSets(!Objects.isNull(updatepermission) && updatepermission.equalsIgnoreCase("on"))
                .invitationLink(String.valueOf(UUID.randomUUID()))
                .build();
        classDao.save(aClass);
        response.sendRedirect("/group");
    }
}
