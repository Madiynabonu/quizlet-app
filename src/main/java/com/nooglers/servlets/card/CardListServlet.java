package com.nooglers.servlets.card;

import com.nooglers.dao.CardDao;
import com.nooglers.domains.Card;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.nooglers.configs.ThreadSafeBeansContainer.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CardListServlet", value = "/getcards")
public class CardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int moduleId = (int)request.getAttribute("moduleId");
        int moduleId = 1;

        List<Card> cards = CardDao.getInstance().getCardsByModuleId(moduleId);
        request.setAttribute("cards", cards);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/card/getcards.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("req.getParameter(\"button\") = " + req.getParameter("button1"));
        System.out.println("req.getParameter(\"button\") = " + req.getParameter("button2"));

        String button1 = req.getParameter("button1");
        String button2 = req.getParameter("button2");
        String button = button1;
        if (button1 == null)
            button = button2;


        String[] split = button.split("/");
       // req.setAttribute("cardId", split[1]);
        Card card = CardDao.getInstance().get(Integer.valueOf(split[1]));
        card.setId(Integer.valueOf(split[1]));
        req.setAttribute("card", card);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/card/" + split[0] + ".jsp");
        requestDispatcher.forward(req, resp);
    }
}