<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 22/02/23
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/utils/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container row">
    <c:forEach items="${modules}" var="module">
        <div class="col-lg-2 col-md-2 col-sm-3" style="margin-top: 5px">
            <div class="card" style="height: 200px; width: 200px">
                <div class="card-header">
                        ${module.getName()}
                </div>
                <div class="card-body">
                    <p class="card-text">${module.getDescription()}</p>
                    <a href="/class/module/add?groupId=${groupId}&moduleId=${module.getId()}"
                       class="btn btn-outline-info">Add</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
