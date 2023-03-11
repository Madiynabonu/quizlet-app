package com.nooglers.servlets.aclass.groupmember;

import com.nooglers.dao.ClassDao;
import com.nooglers.dao.UserDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "ClassMemberRemoveServlet", value = "/group/member/remove")
public class ClassMemberRemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));

        Class aClass = classDao.get(groupId);
        Set<User> users = aClass.getUsers();
        request.setAttribute("users", users);
        request.setAttribute("groupId", groupId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/group/member/member_remove.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassDao classDao = ClassDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer groupId = Integer.valueOf(request.getParameter("groupId"));
        Class aClass = classDao.get(groupId);
        final User userDaoById = userDao.findById(userId);
        HttpSession session = request.getSession();
        Integer sessionId = (Integer) session.getAttribute("user_id");
        if (aClass.getCreatedBy().equals(sessionId)) {
            Set<User> users = aClass.getUsers();
            users.remove(userDaoById);
            aClass.setUsers(users);
            classDao.update(aClass);
        }
        response.sendRedirect("/group");
    }
}
