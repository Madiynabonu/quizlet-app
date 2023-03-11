package com.nooglers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "CardAddFilter", value = "/addcard")
//@MultipartConfig( location = "/home/otash/apps/library/upload" )
public class CardAddFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        var request = (HttpServletRequest) req;
        var response = (HttpServletResponse) res;

        String invalidTermMessage = null;
        String invalidDescriptionMessage = null;

        String method = request.getMethod();

        if (method.equalsIgnoreCase("post")) {

            final String term = req.getParameter("term");
            final String description = req.getParameter("description");

            if (term == null || term.isBlank()) invalidTermMessage = "Term is invalid!";

            if (description == null || description.isBlank()) invalidDescriptionMessage = "Description is invalid!";


            if (invalidTermMessage != null || invalidDescriptionMessage != null) {
                req.setAttribute("invalidNameException", invalidTermMessage);
                req.setAttribute("invalidDescriptionException", invalidDescriptionMessage);
                request.getRequestDispatcher("/view/card/add.jsp").forward(req, res);
            } else {
                chain.doFilter(req, res);
            }



        } else {
            chain.doFilter(request, response);
        }
    }
}