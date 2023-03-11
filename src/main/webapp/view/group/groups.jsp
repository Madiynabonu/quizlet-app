<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class</title>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">--%>
    <%--    <link rel="stylesheet" href="/css/bootstrap.min.css">--%>
    <%@ include file="/utils/header.jsp" %>
</head>
<body>

<div class="container" style="margin-top: 20px">
    <div class="col-lg-10 col-md-8 col-sm-6" style="margin-top: 30px">
        <table class="table" style="margin: auto;text-align: center;justify-content: center">
            <thead>
            <tr>
                <th scope="col">T/r</th>
                <th scope="col">School name</th>
                <th scope="col">Class name</th>
                <th scope="col">Members</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="i" value="${1}"/>
            <c:forEach items="${groups}" var="group">
                <tr>
                    <td>${i}</td>
                    <td>${group.getSchoolName()}</td>
                    <td>${group.getName()}</td>
                    <td>
                        <a href="/group/member/remove?groupId=${group.getId()}">
                                ${group.getUsers().size()+1}
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-primary" href="/mygroup?gid=${group.getId()}"
                           role="button" aria-expanded="false">More</a>
                    </td>
                </tr>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
            </tbody>
        </table>
        <div style="margin-left: 74.5%;margin-top: 20px">
            <a class="btn btn-success" href="/group/add" style="margin-left: 5px">Create class</a>
        </div>
    </div>
</div>
</body>
</html>
