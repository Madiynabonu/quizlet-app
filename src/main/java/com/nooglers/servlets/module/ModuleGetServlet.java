package com.nooglers.servlets.module;

import com.nooglers.configs.ThreadSafeBeansContainer;
import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Card;
import com.nooglers.domains.Module;
import com.nooglers.domains.User;
import com.nooglers.domains.progress.UserProgress;
import com.nooglers.dto.SendMessageDto;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.services.ModuleService;
import com.nooglers.services.QuizService;
import com.nooglers.services.userprogress.UserProgressService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.nooglers.utils.MessageUtil.setMessage;

@WebServlet(name = "ModuleGetServlet", urlPatterns = "/getModule")
public class ModuleGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final QuizService quizService = QuizService.getInstance();
        final ModuleService moduleService = ModuleService.getInstance();
        final UserProgressService userProgressService = UserProgressService.getInstance();

        final Integer moduleId = Integer.valueOf(request.getParameter("mid"));
        final Integer userId = (Integer) Objects.requireNonNullElse(request.getSession().getAttribute("user_id"), 1);
        System.out.println(userId);

//        if ( !quizService.doesUserHaveAccessToThisModule(moduleId , userId) ) {
//            setMessage(request , new SendMessageDto("Opps!" , "This module does not belong to you" , "study modules" , "/listModule"));
//            request.getRequestDispatcher("/utils/error.jsp").forward(request , response);
//        } else {
        final Module module = moduleService.getById(moduleId);
        moduleService.updateLastSeend(module);
        request.setAttribute("module", module);

        final List<UserProgress> up = userProgressService.getUserProgress(userId, moduleId);
        final List<UserProgress> low = up.stream().filter(userProgress -> userProgress.getScore() <= 0).toList();
        final List<UserProgress> med = up.stream().filter(userProgress -> userProgress.getScore() > 0 && userProgress.getScore() < 15).toList();
        final List<UserProgress> high = up.stream().filter(userProgress -> userProgress.getScore() >= 15).toList();

        final List<Card> cards = moduleService.getCards(moduleId);
        System.out.println(cards);
        request.setAttribute("cards", cards);
        request.setAttribute("newAdded", low);
        request.setAttribute("inProgress", med);
        request.setAttribute("mastered", high);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/module/get.jsp");
        requestDispatcher.forward(request, response);
    }


//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int moduleId = Integer.parseInt(request.getParameter("moduleId"));
//        System.out.println("moduleId in get servlet = " + moduleId);
//
//        request.getParameter("userId");
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/module/get.jsp");
//        requestDispatcher.forward(request , response);

        response.sendError(405, "Method not allowed");
    }
}
