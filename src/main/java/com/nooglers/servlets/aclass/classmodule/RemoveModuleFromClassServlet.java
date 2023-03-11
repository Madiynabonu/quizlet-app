package com.nooglers.servlets.aclass.classmodule;

import com.nooglers.dao.ClassDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.Module;
import com.nooglers.services.ClassService;
import com.nooglers.utils.Validators;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "RemoveModuleFromClassServlet", value = "/class/module/remove")
public class RemoveModuleFromClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("groupId");
        if (Validators.checkForNullOrBlank(id)) {
            ClassService classService = ClassService.getInstance();
            Integer groupId = Integer.valueOf(id);
            ClassDao classDao = ClassDao.getInstance();
            Integer moduleId = Integer.valueOf(request.getParameter("moduleId"));
            classService.remove(moduleId, groupId);
            request.setAttribute("groupId", groupId);
            response.sendRedirect("/mygroup?gid=" + groupId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
