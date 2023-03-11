package com.nooglers.servlets.module;

import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;

import java.io.IOException;

@WebServlet( name = "ModuleGetAllServlet", value = "/editModule" )
public class ModuleEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        int moduleId = Integer.parseInt(request.getParameter("id"));
        ModuleDao instance = ModuleDao.getInstance();
        Module module = instance.findById(moduleId);
        Integer createdBy = module.getCreatedBy().getId();
        System.out.println("createdBy = " + createdBy);
//        HttpSession session = request.getSession();
////        int userId = (int) session.getAttribute("user_id");
//        if (createdBy != 1) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/home/home.jsp");
//            dispatcher.forward(request, response);
//        }
        request.setAttribute("module" , module);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/module/update.jsp");
        dispatcher.forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        int moduleId = Integer.parseInt(request.getParameter("moduleId"));
        System.out.println("moduleId = " + moduleId);
        String name = request.getParameter("name");

        String description = request.getParameter("description");
        String aPublic = request.getParameter("radio");
        System.out.println(aPublic);
        ModuleDao moduleDao = ModuleDao.getInstance();
        boolean isPublic = aPublic.equals("public");


        Module module = moduleDao.findById(moduleId);

        module.setName(name);
        module.setDescription(description);
        module.setPublic(isPublic);

        moduleDao.update(module);
//        request.setAttribute("moduleId" , moduleId);
//        request.setAttribute("module" , module);
//        request.getRequestDispatcher("/view/module/get.jsp").forward(request , response);

        response.sendRedirect("getModule?mid=" + moduleId);

    }
}
