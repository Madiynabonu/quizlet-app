package com.nooglers.servlets.folder;

import com.nooglers.dao.FolderDao;
import com.nooglers.domains.Folder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet( name = "GetFolderWithModulesServlet", value = "/folder/folders" )
public class FoldersListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {


        final Integer userId = ( Integer ) request.getSession().getAttribute("user_id");
        request.setAttribute("userId" , userId);

        FolderDao folderDao = FolderDao.getInstance();
        List<Folder> allUserFolders = folderDao.getAllUserFolders(userId);
        System.out.println("Foldersss" + allUserFolders);
        request.setAttribute("folders" , allUserFolders);

        request.getRequestDispatcher("/view/folder/getAll.jsp").forward(request , response);

    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {

    }

}
