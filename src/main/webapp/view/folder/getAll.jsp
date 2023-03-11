<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/19/23
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<head>
    <title>List of folders</title>
    <jsp:include page="/utils/header.jsp"/>
    <%--    <jsp:include page="/fragments/css.jsp"/>--%>
</head>
<body>


<a href="/folder/create" class="btn btn-success m-4">Create Folder</a>

<div class="container m-5">
    <c:if test="${folders.isEmpty()}">
        <%
            request.setAttribute("message1" , "OOPPSS");
            request.setAttribute("message2" , "You don have any folders Yet (:");
            request.setAttribute("message3" , " create folders");
            request.setAttribute("url" , "/folder/create");
            request.getRequestDispatcher("/utils/error.jsp").forward(request , response);
        %>
    </c:if>

    <c:if test="${!folders.isEmpty()}">
        <h1>All Your Folders </h1>
        <div class="row">
            <c:forEach items="${folders}" var="folder">
                <div class="col-lg-4 col-md-6 col-sm-12" style="margin-top: 5px">
                    <div class="card" style="height: 150px">
                        <div class="card-header">
                            <b> ${folder.getTitle()} </b>
                        </div>
                        <div class="card-body">
                            <p class="card-text">${folder.getDescription()}</p>
                            <a href="/folder/getfolder?fid=${folder.getId()}" type="button"
                                <%--                    <a href="/folder/update?id=${folder.getId()}" style="color: black; text-decoration: none">--%>
                               class="btn btn-outline-info">${folder.getTitle()}</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

</div>

</body>
</html>
