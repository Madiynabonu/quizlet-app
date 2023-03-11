<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/13/23
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/utils/header.jsp"/>

<html>
<head>
    <title>Home</title>

</head>
<body>
<%--<%--%>
<%--    response.sendRedirect("/home");--%>
<%--%>--%>


<div class="container">
    <div class="row"></div>
    <div class="books_list m-3">
        <c:forEach items="${modules}" var="module">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${module.getName()}</h5>
                <a href="/getModule?mid=${module.getId()}" class="btn btn-primary">More</a>
            </div>
        </div>
        </c:forEach>


        <%--    </div>--%>
        <%--</div>--%>

</body>
</html>
