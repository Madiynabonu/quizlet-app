package com.nooglers.servlets.folder;

import com.nooglers.dao.FolderDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "FoldersDeleteServlet", value = "/folder/delete")
public class FoldersDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("user_id");
        request.setAttribute("userId", userId);
//        request.setAttribute("userId", 1);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/folder/delete.jsp");
        requestDispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        System.out.println("userId = " + userId);

        FolderDao dao = FolderDao.getInstance();

        String folderId = request.getParameter("folderId");
        dao.delete(Integer.valueOf(folderId));
        response.sendRedirect("/folder/folders");
    }

}

