package com.nooglers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter( filterName = "ModuleCreateFilter", urlPatterns = "/addModule" )
public class ModuleCreateFilter extends HttpFilter {


    @Override
    public void doFilter(ServletRequest req , ServletResponse res , FilterChain chain) throws ServletException, IOException {
        var request = ( HttpServletRequest ) req;
        var response = ( HttpServletResponse ) res;

        String invalidNameMessage = null;

        String method = request.getMethod();

        if ( method.equalsIgnoreCase("post") ) {
            final String name = request.getParameter("name");
            if ( name == null || name.isBlank() ) invalidNameMessage = "name is invalid";
            else chain.doFilter(request , response);
        } else {
            chain.doFilter(request , response);
        }


        if ( invalidNameMessage != null ) {
            req.setAttribute("invalidNameException" , invalidNameMessage);
            request.getRequestDispatcher("/view/module/add.jsp").forward(req , res);
        }


    }
}
