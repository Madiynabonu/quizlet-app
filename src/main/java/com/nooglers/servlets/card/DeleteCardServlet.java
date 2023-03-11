package com.nooglers.servlets.card;

import com.nooglers.dao.CardDao;
import com.nooglers.domains.Card;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import static com.nooglers.configs.ThreadSafeBeansContainer.*;

@WebServlet( name = "DeleteCardServlet", value = "/deletecard" )
public class DeleteCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        String cardId = request.getParameter("cardId");
//        String moduleId = request.getParameter("moduleId");
//        System.out.println("cardId = " + cardId);

        final Card delete = CardDao.getInstance().delete(Integer.valueOf(cardId));
//        response.sendRedirect("/getModule?mid=" + moduleId);
        response.sendRedirect("/getModule?mid=" +delete.getModule().getId());
    }
}