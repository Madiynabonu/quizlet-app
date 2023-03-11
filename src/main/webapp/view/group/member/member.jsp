<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/utils/header.jsp" %>
<html>
<head>
    <title>Class</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(90deg, #3e32a8, #e310c7);
        }

        body > tr, th, a, td, button {
            color: white;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container col-lg10 col-md-8 col-sm-6" style="margin-top: 30px;margin-left: auto">
    <form class="row g-3" method="post" action="/group/member?groupId=${groupId}">
        <div class="col-auto">
            <label for="inputSearch" class="visually-hidden">Enter username</label>
            <input type="search" class="form-control" id="inputSearch" placeholder="Username" name="username">
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-secondary mb-3">Search</button>
            <small><i> found ${users.size()}</i></small>
        </div>
    </form>
    <table class="table" style="margin: auto;text-align: center;justify-content: center">
        <thead>
        <tr>
            <th scope="col">T/r</th>
            <th scope="col">Username</th>
            <th scope="col">Action</th>

        </tr>
        </thead>
        <tbody>
        <c:set var="i" value="${1}"/>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${i}</td>
                <td>${user.getUsername()}</td>
                <td>
                    <a href="/group/member/add?groupId=${groupId}&userId=${user.getId()}" class="btn btn-warning">
                        Add
                    </a>
                </td>
            </tr>
            <c:set var="i" value="${i+1}"/>
        </c:forEach>
        </tbody>
    </table>
</div>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"--%>
<%--        crossorigin="anonymous"></script>--%>
<%--<%@ include file="/fragments/js.jsp" %>--%>
</body>
</html>