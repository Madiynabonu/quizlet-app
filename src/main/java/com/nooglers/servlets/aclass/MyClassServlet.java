package com.nooglers.servlets.aclass;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.domains.Class;
import com.nooglers.domains.Module;
import com.nooglers.services.ClassService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "MyClassServlet", urlPatterns = "/mygroup")
public class MyClassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ClassService classService = ClassService.getInstance();
        final Integer groupId = Integer.valueOf(req.getParameter("gid"));
        final Integer userId = (Integer) req.getSession().getAttribute("user_id");
        final Class group = classService.getGroup(groupId);
        Set<Module> moduleList = classService.getModules(groupId);
        final boolean equals = group.getCreatedBy().equals(userId);
        System.out.println(equals);
        req.setAttribute("isOwner", equals);
        req.setAttribute("group", group);
        req.setAttribute("groupId", groupId);
        req.setAttribute("isUpdateAble", group.isPermissionToUpdateSets());
        req.setAttribute("modules", moduleList);
        req.getRequestDispatcher("/view/group/mygroup.jsp").forward(req, resp);
    }
}
