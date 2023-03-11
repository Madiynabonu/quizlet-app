

package com.nooglers.servlets.folder;

import com.nooglers.dao.FolderDao;
import com.nooglers.domains.Folder;
import com.nooglers.domains.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet( name = "FolderAddServlet", urlPatterns = "/folder/create" )
public class
FolderAddServ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = ( Integer ) session.getAttribute("user_id");
        request.setAttribute("userId" , userId);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/folder/add.jsp");
        requestDispatcher.forward(request , response);


    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        System.out.println("userId = " + userId);

        User user = new User();
        user.setId(userId);
        final FolderDao dao = FolderDao.getInstance();
        String title = request.getParameter("title");
        String description = request.getParameter("description");


        Folder folder = Folder.builder()
                .title(title)
                .description(description)
                .createdBy(user)
                .build();

        Folder savedFolder = dao.save(folder);

//        System.out.println("savedFolder = " + savedFolder);
        request.setAttribute("folder" , savedFolder);

        FolderDao folderDao = FolderDao.getInstance();
        List<Folder> allUserFolders = folderDao.getAllUserFolders(userId);
        System.out.println("Foldersss" + allUserFolders);
        request.setAttribute("folders" , allUserFolders);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/folder/get.jsp");
        requestDispatcher.forward(request , response);

    }

}