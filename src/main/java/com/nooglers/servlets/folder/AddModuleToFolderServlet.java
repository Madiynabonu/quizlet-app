package com.nooglers.servlets.folder;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Module;
import com.nooglers.services.ModuleService;
import com.nooglers.utils.Validators;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet( name = "AddModuleToFolderServlet", value = "/appendModule" )
public class AddModuleToFolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        final String fid = request.getParameter("fid");
        final String mid = request.getParameter("mid");

        if ( Validators.checkForNullOrBlank(fid) && Validators.checkForNullOrBlank(mid) ) {
            final ModuleDao moduleDao = ModuleDao.getInstance();
            final Module byId = moduleDao.findById(Integer.valueOf(mid));
            moduleDao.save(byId , fid);
            request.getRequestDispatcher("/setModule?fid=" + fid).forward(request , response);
        }
    }


}
