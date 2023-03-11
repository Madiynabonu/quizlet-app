package com.nooglers.servlets.test;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dto.SendMessageDto;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.services.ModuleService;
import com.nooglers.services.QuizService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;

import java.io.IOException;

import static com.nooglers.utils.MessageUtil.setMessage;

@WebServlet( name = "TestServlet", urlPatterns = "/test" )
public class TestServlet extends HttpServlet {

    final QuizService quizService =QuizService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        Integer userId = ( Integer ) req.getSession().getAttribute("user_id");
        Integer moduleId = Integer.valueOf(req.getParameter("mid"));

//        if ( !quizService.doesUserHaveAccessToThisModule(moduleId , userId) ) {
//            setMessage(req , new SendMessageDto("Opps!" , "You don't have access to this study module" , "cards" , "/getModule?mid=" + moduleId));
//            req.getRequestDispatcher("/utils/error.jsp").forward(req , resp);
//        }
         if ( quizService.numberOfQuestions(moduleId) < 2 ) {
            setMessage(req , new SendMessageDto(
                    "Opps!" ,
                    "You don't have enough cards to start quizzes" ,
                    "cards" ,
                    "/addcard?mid="+moduleId));
            req.getRequestDispatcher("/utils/error.jsp").forward(req , resp);
        } else {
            final SolveQuestionDto solveQuestionDto = quizService.generateTest(userId , moduleId);
            System.out.println(solveQuestionDto);
            req.setAttribute("question" , solveQuestionDto);
            req.setAttribute("hasNext" , true);
            req.getRequestDispatcher("/view/quiz/test.jsp").forward(req , resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {


        Integer questionId = Integer.valueOf(req.getParameter("questionId"));
        String answer = req.getParameter("value");
        Integer userId = ( Integer ) req.getSession().getAttribute("user_id");
        quizService.submit(questionId , answer);

        final int questionLeft = quizService.questionLeft(userId);

        System.out.println("questions left=" + questionLeft);
        if ( questionLeft == 0 ) {
//            req.setAttribute("result" , quizService.finish(userId));
            resp.sendRedirect("/test/result");
        } else {
            final SolveQuestionDto next = quizService.next(userId);
            req.setAttribute("question" , next);
            req.getRequestDispatcher("/view/quiz/test.jsp").forward(req , resp);
        }


//        Duration.ofHours(ChronoUnit.HOURS.between(LocalDateTime.now() , LocalDateTime.now().plusHours(10))).toHours()
    }
}
