package com.nooglers.servlets.card;

import com.nooglers.dao.CardDao;
import com.nooglers.domains.Card;
import com.nooglers.domains.Document;
import com.nooglers.utils.ImageUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.nooglers.configs.ThreadSafeBeansContainer.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;


@WebServlet(name = "UpdateCardServlet", value = "/editcard")
@MultipartConfig(location = "/home/otash/apps/library/uploads")
public class UpdateCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        final Integer cardId = Integer.valueOf(req.getParameter("cardId"));
        final CardDao cardDao = CardDao.getInstance();
        final Card byId = cardDao.findById(cardId);
        req.setAttribute("card", byId);

        req.getRequestDispatcher("/view/card/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Integer cardId = Integer.valueOf(request.getParameter("cardId"));
        String term = request.getParameter("term");
        String description = request.getParameter("description");
        String moduleId = request.getParameter("moduleId");

        CardDao cardDao = CardDao.getInstance();
        Card card = cardDao.get(cardId);

//        Part newImage = request.getPart("image");
//        if ( !newImage.getSubmittedFileName().equals("") ) {
//            Document document = ImageUtils.saveImage(newImage);
//            card.setDocument(document);
//        }

        card.setTitle(term);
        card.setDescription(description);
        card.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Tashkent")));
        cardDao.update(card);

        response.sendRedirect("/getModule?mid=" + moduleId);
    }
}
