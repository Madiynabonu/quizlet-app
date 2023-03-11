<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 18/02/23
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> All Modules Page</title>

    <jsp:include page="/utils/header.jsp"/>
    <%--    <jsp:include page="/fragments/css.jsp"/>--%>
</head>
<body>
<h5 class="card-title m-3"> All your modules </h5>
<p>

</p>
<hr>
<div class="row">
    <c:if test="${modules.isEmpty()}">
        <%
            request.setAttribute("message1" , "OOPPSS");
            request.setAttribute("message2" , "You don have any modules Yet((");
            request.setAttribute("message3" , " addModule");
            request.setAttribute("url" , "/addModule");
            request.getRequestDispatcher("/utils/error.jsp").forward(request , response);
        %>
    </c:if>
    <c:if test="${!modules.isEmpty()}">
        <c:forEach items="${modules}" var="module">
            <div class="col-lg-4 col-md-5 col-sm-8" style="margin-top: 5px">
                <div class="card" style="height: 200px; width: 200px">
                    <div class="card-header">
                            ${module.getName()}
                    </div>
                    <div class="card-body">
                        <p class="card-text">${module.getDescription()}</p>
                        <a href="/getModule?mid=${module.getId()}" type="button"
                           class="btn btn-outline-info">${module.getName()}</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
<%--<jsp:include page="/fragments/js.jsp"/>--%>
</body>
</html>
