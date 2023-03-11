package com.nooglers.servlets.aclass.classmodule;

import com.nooglers.dao.ClassDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.Module;
import com.nooglers.services.ClassService;
import com.nooglers.utils.Validators;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "GetModuleToClassServlet", value = "/class/module/get")
public class GetModuleToClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("groupId");
        if (Validators.checkForNullOrBlank(id)) {
            ClassService classService = ClassService.getInstance();
            HttpSession session = request.getSession();
            Integer userId = (Integer) session.getAttribute("user_id");
            Integer groupId = Integer.valueOf(id);
            ClassDao instance = ClassDao.getInstance();
            Class aClass = instance.get(groupId);
            Set<Module> groupModules = classService.getGroupModules(groupId, userId);
            request.setAttribute("isUpdateAble", aClass.isPermissionToUpdateSets());
            request.setAttribute("modules", groupModules);
            request.setAttribute("groupId", groupId);
//            request.setAttribute("isOwner", userId.equals(group.getCreatedBy()));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/group/module/classmoduleadd.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
