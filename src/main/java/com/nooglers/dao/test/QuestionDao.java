package com.nooglers.dao.test;


import com.nooglers.dao.BaseDAO;
import com.nooglers.domains.test.Variant;
import com.nooglers.dto.SolveQuestionDto;
import com.nooglers.services.QuizService;
import com.nooglers.utils.ApplicationUtils;
import com.nooglers.utils.EntityProvider;
import com.nooglers.domains.Card;
import com.nooglers.domains.User;
import com.nooglers.domains.progress.UserProgress;
import com.nooglers.domains.test.Question;
import com.nooglers.domains.test.QuizHistory;
import com.nooglers.enums.QuizType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.time.LocalDateTime;
import java.util.*;

import static com.nooglers.utils.ApplicationUtils.*;

public class QuestionDao extends BaseDAO<Question, Integer> implements EntityProvider {

    private static ThreadLocal<QuestionDao> QUESTION_DAO = ThreadLocal.withInitial(QuestionDao::new);

    public static QuestionDao getInstance() {
        return QUESTION_DAO.get();
    }

    public SolveQuestionDto generateTest(Integer userId, Integer setId) {


        clearUnfinishedTest(userId);
        var quizHistoryBuilder = QuizHistory.builder();
        final EntityManager em = entityManager;

        User createdBy = em.find(User.class, userId);
        quizHistoryBuilder.createdBy(createdBy);

//        entityManager.get().createQuery("update quiz_history qh set qh.deleted=cast(1 as short ) where qh.createdBy=?1 and qh.finishedAt is null").setParameter(1 , createdBy).executeUpdate();

        String selectCards = """
                select c from card c
                            where c <> all (select up.card
                                               from user_progress up
                                               where up.user=?1) and c.module.id=?2 and c.deleted=0
                """;

        @SuppressWarnings("unchecked") List<Card> cardsList = em.createQuery(selectCards, Card.class).setParameter(1, createdBy).setParameter(2, setId).getResultList();

        em.getTransaction().begin();


        for (Card card : cardsList)
            em.persist(UserProgress.builder().user(createdBy).card(card).build());

        List<UserProgress> resultList = em.createQuery("select up from user_progress up where up.user=?1 and up.card.module.id=?2 and up.card.deleted=0 order by up.score", UserProgress.class).setParameter(1, createdBy).setParameter(2, setId).setMaxResults(MAX_QUESTION_COUNT * 2).getResultList();

        quizHistoryBuilder
                .totalQuestionCount(Math.min(resultList.size(), MAX_QUESTION_COUNT))
                .startedAt(LocalDateTime.now());

        QuizHistory quizHistory = quizHistoryBuilder.build();
        em.persist(quizHistory);


        for (int i = 0; i < resultList.size() && i < MAX_QUESTION_COUNT; i++) {
            final UserProgress userProgress = resultList.get(i);
            Card card = userProgress.getCard();
            var questionBuilder = Question.builder().card(card).quizHistory(quizHistory);
            int randomInt = random.nextInt(3);
            String description = card.getDescription();
            String term = card.getTitle();

            if (randomInt == 0) { // true-false

                Question question = questionBuilder.definition(description).displayTerm(term).quizType(QuizType.TRUE_FALSE).build();
                Card randomCard = getRandomCard(card, resultList);

                if (!random.nextBoolean()) {
                    question.setDisplayTerm(randomCard.getTitle());
                    final Variant variant = Variant.builder().isCorrect(false).term(randomCard.getTitle()).question(question).build();
                    em.persist(variant);
                }
                em.persist(Variant.builder().isCorrect(true).term(term).question(question).build());

            } else if (randomInt == 1) {
                Question question = questionBuilder.quizType(QuizType.WRITING).displayTerm(term).definition(description).build();
                Variant variant = Variant.builder().question(question).term(term).isCorrect(true).build();
                em.persist(variant);


            } else { // if ( randomInt == 2 )
                questionBuilder.definition(description).quizType(QuizType.TEST).build();
                Question question = questionBuilder.displayTerm(term).build();
                List<Variant> wrongAnswers = createWrongAnswersForTest(term, question, resultList);
                wrongAnswers.add(Variant.builder().question(question).isCorrect(true).term(term).build());
                Collections.shuffle(wrongAnswers);
                addAll(wrongAnswers);
            }
        }

        em.getTransaction().commit();

        return next(userId);

    }

    private void clearUnfinishedTest(Integer userId) {

        try {
            begin();
            entityManager.createQuery(
                    "delete from quiz_history  q where q.createdBy.id=?1 and q.finishedAt is null ")
                    .setParameter(1, userId)
                    .executeUpdate();
            commit();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

    }

    private void addAll(Collection<Variant> wrongAnswers) {
        for (Variant wrongAnswer : wrongAnswers)
            entityManager.persist(wrongAnswer);

    }

    private Card getRandomCard(Card currentCard, List<UserProgress> resultList) {
        Collections.shuffle(resultList);
        return resultList.stream().filter(up -> up.getCard() != currentCard).findAny().get().getCard();
    }

    public SolveQuestionDto next(Integer userId) {
        try {
            final Question question = entityManager.createQuery("from question q  join quiz_history  qh on qh.id=q.quizHistory.id where qh.createdBy.id=?1 and q.userAnswer = null and qh.finishedAt is null", Question.class).setParameter(1, userId).setMaxResults(1).getSingleResult();
            return new SolveQuestionDto(question.getQuizType().name(), question.getDefinition(), variants(question.getId()), question.getId(), question.getQuizHistory().getTotalQuestionCount(), questionLeft(userId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private List<Variant> createWrongAnswersForTest(String correctTitle, Question question, List<UserProgress> resultList) {

        List<Variant> wrongAnswers = new ArrayList<>();
        for (int i = 0; i < resultList.size() && i < 4; i++) {
            final Card card = resultList.get(i).getCard();
            String term = card.getTitle();
            if (!term.equals(correctTitle)) {
                final Variant variant = Variant.builder().term(term).isCorrect(false).question(question).build();
                wrongAnswers.add(variant);
            }
        }
        return wrongAnswers;
    }

    public void submit(Integer questionId, String answer) {
        begin();
        entityManager.createQuery("update question q set q.userAnswer=?1 where q.id=?2").setParameter(1, answer).setParameter(2, questionId).executeUpdate();
        commit();
    }

    public int questionLeft(Integer userId) {
        try {
            return entityManager.createQuery("select count(*) from question q where q.quizHistory.createdBy.id=?1 and q.userAnswer=null and quizHistory.finishedAt is null ", Long.class).setParameter(1, userId).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }


    public QuizHistory finish(Integer userId) {

        final EntityManager em = entityManager;
        final List<Question> resultList = em.createQuery("from question q where q.quizHistory.createdBy.id=?1 and q.quizHistory.finishedAt is null", Question.class)
                .setParameter(1, userId).getResultList();

        if (resultList.isEmpty()) return null;

        final QuizHistory quizHistory = resultList.get(0).getQuizHistory();
        int score = 0;

        em.getTransaction().begin();

        for (Question question : resultList) {
            em.refresh(question);
            final Variant variant = variants(question.getId()).stream().filter(Variant::isCorrect).findAny().get();
            String correctAnswer = variant.getTerm();
            final String userAnswer = question.getUserAnswer();
            System.out.println(userAnswer);
            final Integer cardId = question.getCard().getId();

            if (question.getUserAnswer() != null) {
                if (question.getQuizType().equals(QuizType.TEST)) {
                    if (userAnswer.equals(String.valueOf(variant.getId()))) {
                        ++score;
                        question.setCorrect(true);
                        updateUserProgress(6, userId, cardId);
                    } else updateUserProgress(-3, userId, cardId);

                } else if (question.getQuizType().equals(QuizType.TRUE_FALSE)) {
                    if (checkUserAnswerForTrueOrFalse(question.getId(), userAnswer)) {
                        ++score;
                        question.setCorrect(true);
                    } else updateUserProgress(-3, userId, cardId);

                } else {
                    if (correctAnswer.equals(userAnswer)) {
                        ++score;
                        question.setCorrect(true);
                        updateUserProgress(6, userId, cardId);
                    } else updateUserProgress(-3, userId, cardId);

                }
            } else updateUserProgress(-3, userId, cardId);
        }

        quizHistory.setFinishedAt(LocalDateTime.now());
        quizHistory.setCorrectAnswerCount(score);

        quizHistory.setPercentage((short) (quizHistory.getCorrectAnswerCount() * 100 / quizHistory.getTotalQuestionCount()));

        em.getTransaction().commit();

        return quizHistory;
    }

    private void updateUserProgress(int score, Integer userId, Integer cardId) {
        entityManager.createQuery("update user_progress  up set up.score=up.score+?1 where up.user.id=?2 and up.card.id=?3").setParameter(1, score).setParameter(2, userId).setParameter(3, cardId).executeUpdate();
    }

    private boolean checkUserAnswerForTrueOrFalse(Integer questionId, String userAnswer) {
        final int size = variants(questionId).size();
        return (size == 1 && userAnswer.equals("true")) || (size == 2 && userAnswer.equals("false"));
    }

    public List<Variant> variants(Integer questionId) {
        return entityManager.createQuery("from variant v where v.question.id=?1", Variant.class).setParameter(1, questionId).getResultList();
    }

    public List<Question> getQuestions(Integer quizHistoryId) {
        return entityManager.createQuery("from question  q where q.quizHistory.id=?1", Question.class).setParameter(1, quizHistoryId).getResultList();
    }

    public String getTerm(Integer questionId) {
        return entityManager.createQuery("from variant v where v.question.id=?1 and v.isCorrect", Variant.class).setParameter(1, questionId).getSingleResult().getTerm();
    }

    public Variant getVariantById(Integer variantId) {
        return entityManager.createQuery("from variant v where v.id=?1", Variant.class).setParameter(1, variantId).getSingleResult();
    }

    public void refresh(Object entity) {
        entityManager.refresh(entity);
    }

    public boolean doesUserHaveAccessToThisModule(Integer moduleId, Integer userId) {

        final Long own = entityManager.createQuery("select count(*) from module  m where m.id=?1 and m.createdBy.id=?2", Long.class).setParameter(1, moduleId).setParameter(2, userId).getSingleResult();

//        entityManager.createQuery("select count(*) from Class  c where c.classUser.")
        return own > 0;
    }

    public Long numberOfQuestions(Integer moduleId) {

        final Long singleResult = entityManager.createQuery("select count(*) from card c where c.module.id=?1 and c.deleted=0", Long.class).setParameter(1, moduleId).getSingleResult();

        return singleResult;

    }

    public List<QuizHistory> getQuizHistories(Integer userId) {
        return entityManager.createQuery(
                "from quiz_history  qh where qh.createdBy.id=?1 and finishedAt is not null order by qh.correctAnswerCount desc",
                        QuizHistory.class)
                .setParameter(1, userId)
                .getResultList();
    }
}
