package com.nooglers.servlets.test;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.services.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet( name = "FinishTestServlet", urlPatterns = "/test/result" )
public class FinishTestServlet extends HttpServlet {

    private final QuizService quizService = QuizService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        final Integer userId = Objects.requireNonNullElse(( Integer ) req.getSession().getAttribute("user_id") , 1);
        final QuizHistory finish = quizService.finish(userId);
        if ( Objects.isNull(finish) ) {
            req.getRequestDispatcher("/home").forward(req , resp);
        return;
        };
        req.setAttribute("quizHistory" , finish);
        req.setAttribute("questions" ,quizService.getQuestions(finish.getId()) );
        req.getRequestDispatcher("/view/quiz/result.jsp").forward(req , resp);
    }
}
