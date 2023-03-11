package com.nooglers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter(filterName = "FolderCreateValidationFilter", urlPatterns = "/folder/add")
public class FolderCreateValidationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String title = req.getParameter("title");
        if (req.getMethod().equalsIgnoreCase("post")) {
            if (title == null || title.isBlank()) {
                request.setAttribute("title_error", "Title can not be null");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/folder/add.jsp");
                dispatcher.forward(req, res);
            } else chain.doFilter(req, res);
        } else
            chain.doFilter(req, res);
    }
}
