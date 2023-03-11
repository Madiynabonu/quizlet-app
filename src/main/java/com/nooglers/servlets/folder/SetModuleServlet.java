package com.nooglers.servlets.folder;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dto.ModuleSetDto;
import com.nooglers.services.ModuleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet( name = "SetModuleServlet", value = "/setModule" )
public class SetModuleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        final String folderId = request.getParameter("fid");

        final ModuleService moduleService =ModuleService.getInstance();

        if ( folderId != null && !folderId.isBlank() ) {

            final Integer userId = ( Integer ) request.getSession().getAttribute("user_id");
            final Integer   folderIdInteger = Integer.valueOf(folderId);
            final List<ModuleSetDto> dtos = moduleService.getModules(userId , folderIdInteger);

            request.setAttribute("dtos" , dtos);
            request.setAttribute("folderId",folderId);
            request.getRequestDispatcher("/view/folder/setModule.jsp").forward(request , response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

    }
}
