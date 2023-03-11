package com.nooglers.servlets.folder;

import com.nooglers.dao.FolderDao;
import com.nooglers.domains.Folder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "FoldersUpdateServlet", urlPatterns = "/folder/update")
public class FolderUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("user_id");
        request.setAttribute("userId", userId);


        Integer id = Integer.valueOf(request.getParameter("id"));
        FolderDao instance = FolderDao.getInstance();
        Folder folder = instance.get(id);
        Integer createdBy = folder.getCreatedBy().getId();

//        request.setAttribute("userId", 1);

        request.setAttribute("folder", folder);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/folder/update.jsp");
        requestDispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer folderId = Integer.valueOf(request.getParameter("folderId"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");


        FolderDao folderDao = FolderDao.getInstance();
        String userId = request.getParameter("userId");
        Folder folder = folderDao.get(folderId);

        folder.setTitle(title);
        folder.setDescription(description);

        folderDao.update(folder);

        request.setAttribute("folderId", folderId);
        request.setAttribute("folder", folder);
        request.getRequestDispatcher("/view/folder/get.jsp").forward(request, response);


    }

}