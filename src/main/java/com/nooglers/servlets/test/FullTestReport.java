package com.nooglers.servlets.test;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.QuizHistoryDao;
import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.services.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet( name = "FullTestReport", urlPatterns = "/fullrecord" )
public class FullTestReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {


        final QuizService quizService = QuizService.getInstance();
        final QuizHistoryDao quizHistoryDao = QuizHistoryDao.getInstance();

        final Integer quizHistoryId = Integer.valueOf(req.getParameter("qhid"));
        final Integer userId = ( Integer ) req.getSession().getAttribute("user_id");

        final QuizHistory quizHistory = quizHistoryDao.findById(quizHistoryId);
        final List<Question> questions = quizService.getQuestions(quizHistoryId);

        req.setAttribute("questions" , questions);
        req.setAttribute("quizHistory" , quizHistory);

        req.getRequestDispatcher("/view/quiz/result.jsp").forward(req,resp);

    }
}
