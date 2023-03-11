package com.nooglers.servlets.test;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.services.QuizService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet( name = "TestRecordsServlet", value = "/records" )
public class TestRecordsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        QuizService quizService = QuizService.getInstance();
        final Integer userId = ( Integer ) request.getSession().getAttribute("user_id");
        final List<QuizHistory> quizHistories = quizService.getQuizHistories(userId);
        request.setAttribute("quizHistories" , quizHistories);
        request.getRequestDispatcher("/view/quiz/records.jsp").forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

    }
}
