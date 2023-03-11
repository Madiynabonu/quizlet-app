package com.nooglers.servlets.folder;

import com.nooglers.dao.FolderDao;
import com.nooglers.domains.Folder;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import com.nooglers.services.ModuleService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebServlet( name = "FolderGetServlet", urlPatterns = "/folder/getfolder" )
public class FolderGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = ( Integer ) session.getAttribute("user_id");
        Integer folderId = Integer.valueOf(request.getParameter("fid"));
        request.setAttribute("userId" , userId);
        request.setAttribute("folderId" , folderId);


        ModuleService moduleService = ModuleService.getInstance();


//        Integer folderId = Integer.valueOf(request.getParameter("fid"));

        final Set<Module> modules = moduleService.getModulesByFolderId(folderId);

        FolderDao folderDao = FolderDao.getInstance();
        Folder folder = folderDao.get(folderId);
//        final Set<Module> modules = folder.getModules();

        System.out.println(modules);
        request.setAttribute("modules" , modules);
        request.setAttribute("folder" , folder);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/folder/get.jsp");
        requestDispatcher.forward(request , response);


    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        Integer folderId = Integer.valueOf(request.getParameter("folderId"));
        System.out.println("userId = " + userId);

        User user = new User();
        user.setId(Integer.valueOf(userId));
        final FolderDao dao = FolderDao.getInstance();
        String title = request.getParameter("title");
        String description = request.getParameter("description");


        dao.get(folderId);

        response.sendRedirect("/view/folder/get.jsp");
    }

}
