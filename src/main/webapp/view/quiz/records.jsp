<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.Duration" %>
<%@ page import="com.nooglers.utils.ApplicationUtils" %>

<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/21/23
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/utils/header.jsp" %>
<html>
<head>
    <title>Records</title>
</head>
<body>

<div class="container m-2">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Total Questions:</th>
            <th scope="col">Correct Answers:</th>
            <th scope="col">Started at:</th>
            <th scope="col">Finished at:</th>
            <th scope="col">Total time(minutes):</th>
            <th scope="col">Percentage:</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:set var="i" value="${1}"/>

        <c:forEach items="${quizHistories}" var="quizHistory">
            <%--        <jsp:useBean id="ApplicationUtils" scope="application" type="com.nooglers.utils.ApplicationUtils"/>--%>

            <tr>
                <th scope="row">${i}</th>
                <td>${quizHistory.getTotalQuestionCount()}</td>
                <td>${quizHistory.getCorrectAnswerCount()}</td>
                <td>${ApplicationUtils.DATE_TIME_FORMATTER.format(quizHistory.getStartedAt())}</td>
                <td>${ApplicationUtils.DATE_TIME_FORMATTER.format(quizHistory.getFinishedAt())}</td>
                <td>${Duration.ofMinutes(ChronoUnit.MINUTES.between(quizHistory.getStartedAt() , quizHistory.getFinishedAt())).toMinutes()}</td>
                <td> ${quizHistory.getPercentage()}%</td>
                <td><a href="/fullrecord?qhid=${quizHistory.getId()}" class="btn-success">More</a></td>
            </tr>
            <c:set var="i" value="${i+1}"/>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
