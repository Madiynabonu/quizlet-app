package com.nooglers.servlets.aclass.groupmember;

import com.nooglers.dao.UserDao;
import com.nooglers.domains.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet( name = "ClassMemberServlet", value = "/group/member" )
public class ClassMemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDao.getInstance();
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        request.setAttribute("groupId" , groupId);
        request.setAttribute("users" , userDao.getAllById(groupId));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/group/member/member.jsp");
        dispatcher.forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        request.setAttribute("groupId" , groupId);
        UserDao userDao = UserDao.getInstance();
        if ( Objects.isNull(username) || username.trim().isBlank() ) {
            request.setAttribute("users" , userDao.getAll());
        } else {
            List<User> users = userDao.getAllByUserName(username);
            request.setAttribute("users" , users);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/group/member/member.jsp");
        requestDispatcher.forward(request , response);
    }
}
