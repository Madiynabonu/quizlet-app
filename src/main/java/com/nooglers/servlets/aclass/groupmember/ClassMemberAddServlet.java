package com.nooglers.servlets.aclass.groupmember;

import com.nooglers.dao.ClassDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.User;
import com.nooglers.services.ClassService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ClassMemberAddServlet", value = "/group/member/add")
public class ClassMemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ClassDao classDao = ClassDao.getInstance();
//        UserDao userDao = UserDao.getInstance();
        final ClassService classService = ClassService.getInstance();


        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));

        classService.addUser(userId,groupId);

        response.sendRedirect("/group");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {


    }
}
