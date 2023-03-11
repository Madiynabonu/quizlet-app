package com.nooglers.services;

import com.nooglers.dao.test.QuestionDao;
import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.domains.test.Variant;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.enums.QuizType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class QuizService {
    private static final ThreadLocal<QuizService> QUIZ_SERVICE_THREAD_LOCAL = ThreadLocal.withInitial(QuizService::new);
    QuestionDao dao = QuestionDao.getInstance();

    public SolveQuestionDto generateTest(@NonNull Integer userId , Integer setId) {
        return dao.generateTest(userId , setId);
    }

    public SolveQuestionDto next(@NonNull Integer userId) {
        return dao.next(userId);
    }

    public void submit(@NonNull Integer questionId , String answer) {
        dao.submit(questionId , answer);
    }

    public int questionLeft(Integer userId) {
        return dao.questionLeft(userId);
    }

    public QuizHistory finish(Integer userId) {
        return dao.finish(userId);
    }

    public List<Question> getQuestions(Integer quizHistoryId) {
        return dao.getQuestions(quizHistoryId);
    }

    public String getTerm(Question question) {
        return dao.getTerm(question.getId());
    }

    public String getUserAnswer(Question question) {
        if ( question.getUserAnswer() == null ) return "did not answer"; // TODO localized message
        if ( question.getQuizType().equals(QuizType.TEST) )
            return getVariantById(Integer.valueOf(question.getUserAnswer()) , question).getTerm();
        return question.getUserAnswer();

    }

    private Variant getVariantById(Integer variantId , Question question) {
        return dao.getVariantById(variantId);
    }


    private String getTerm(List<Variant> variants) {
        return variants.stream().filter(Variant::isCorrect).map(Variant::getTerm).findAny().get();
    }

    public boolean doesUserHaveAccessToThisModule(Integer moduleId , Integer userId) {
        return dao.doesUserHaveAccessToThisModule(moduleId , userId);
    }

    public Long numberOfQuestions(Integer moduleId) {
        return dao.numberOfQuestions(moduleId);
    }


    public List<QuizHistory> getQuizHistories(Integer userId) {

        return dao.getQuizHistories(userId);
    }

//    public static QuizService getInstance() {
//        return QUIZ_SERVICE_THREAD_LOCAL.get();
//    }


    public static QuizService getInstance() {
        return QuizService.QUIZ_SERVICE_THREAD_LOCAL.get();
    }
}
