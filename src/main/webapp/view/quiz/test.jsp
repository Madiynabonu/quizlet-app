<%@ page import="com.nooglers.utils.ApplicationUtils" %>
<%@ page import="com.nooglers.domains.test.Question" %>
<%@ page import="com.nooglers.dto.SolveQuestionDto" %>
<%@ page import="com.nooglers.domains.test.Variant" %><%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/15/23
  Time: 1:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/fragments/css.jsp" %>
<%@ page errorPage="/utils/error.jsp" %>

<html>
<head>
    <title>Test</title>
    <%--    <link rel="stylesheet" href="/utils/css/bootstrap.min.css">--%>
    <style>
        .key {
            font-style: italic;
            text-decoration: underline;
            font-weight: bolder;
        }
    </style>
</head>
<body>


<div>

        <%final SolveQuestionDto question = ( SolveQuestionDto ) request.getAttribute("question");%>

    <div class="card" style="width: 50rem; margin: 50px auto; background-color: #eefdff">

        <span style="margin: 2px">${question.totalQuestionCount()-question.currentQuestionCount()+1}/${question.totalQuestionCount()}</span>

        <div>
            <a type="button" class="btn btn-danger" style="margin-left: 90%" href="/test/result">Finish</a>
        </div>

        <form method="post" action="/test">

            <input type="hidden" name="questionId" value="${question.id()}">
            <label>
                <%if ( question.quizType().equals("TRUE_FALSE") ) {%>
                <div class="card-body">
                    <%
                        System.out.println(question.variants());
                        if ( question.variants().size() == 1 ) {
                            final Variant answer = question.variants().stream().filter(Variant::isCorrect).findAny().get();
                    %>
                    <div class="card-title">
                        <p><span class="key">Term:   </span> <%=answer.getTerm()%>
                        </p>
                        <p><span class="key">Definition:   </span> ${question.definition()}
                        </p>
                        <div class="card-text">
                            <input type="radio" name="value" value="true">
                            True <input type="radio" name="value" value="false">False
                        </div>
                    </div>
                    <%
                    } else {
                        final Variant incorrectAnswer = question.variants().stream().filter(variant -> !variant.isCorrect()).findAny().get();
                        System.out.println("incorrect answer=" + incorrectAnswer);
                    %>
                    <div class="card-title">
                        <p><span class="key">Term:   </span> <%=incorrectAnswer.getTerm()%>
                        </p>
                        <p><span class="key">Definition:  </span> ${question.definition()}
                        </p>
                        <div class="card-text">
                            <input type="radio" name="value" value="true">
                            True <input type="radio" name="value" value="false">False
                        </div>
                    </div>

                    <%}%>
                </div>

                <%} else if ( question.quizType().equals("WRITING") ) {%>

                <div class="card-body">
                    <div class="card-title">
                        <p class="key">${question.definition()}
                    </div>
                    <div class="card-text">
                        <input type="text" placeholder="your answer" name="value">
                    </div>
                </div>


                <%} else if ( question.quizType().equals("TEST") ) {%>

                <div class="card-body">
                    <div class="card-title">
                        <p class="key">${question.definition()}
                        </p>
                    </div>
                    <%
                        for ( Variant variant : question.variants() ) {%>
                    <div class="card-text">
                        <input type="radio" name="value" value="<%=variant.getId()%>"> <%=variant.getTerm()%>
                    </div>
                    <%}%>
                </div>
                <%}%>

            </label>
            <div>
                <button href="/test" type="submit" class="btn btn-success m-3">Next</button>
            </div>

        </form>

    </div>


</body>
</html>
