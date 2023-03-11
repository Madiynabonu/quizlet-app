package com.nooglers.servlets.folder;

import com.nooglers.dao.FolderDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet( name = "UnsetModuleServlet", urlPatterns = "/unsetModule" )
public class UnsetModuleServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        final FolderDao folderDao = FolderDao.getInstance();
        final Integer mid = Integer.valueOf(req.getParameter("mid"));
        final Integer fid = Integer.valueOf(req.getParameter("fid"));
        final String next = req.getParameter("next");

        System.out.println(folderDao.unsetModule(mid , fid));


        resp.sendRedirect(next);

    }
}
