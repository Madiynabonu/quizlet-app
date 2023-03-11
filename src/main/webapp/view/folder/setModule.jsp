<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/22/23
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/utils/header.jsp" %>
<html>
<head>
    <title>Set Module</title>
</head>
<body>


<div class="container">
    <a href="/folder/getfolder?fid=${folderId}" class="btn btn-success m-3">Back To Folder</a>
    <div class="row">
        <div class="books_list">
            <c:forEach items="${dtos}" var="dto">
                <div class="card">
                    <div class="card-body">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">${dto.module().getName()}</li>
                        </ul>

                        <c:if test="${dto.isIncluded()}">
                            <a class="btn btn-danger"
                               href="/unsetModule?mid=${dto.module().getId()}&fid=${folderId}&next=/setModule?fid=${folderId}"><i>Remove</i></a>
                        </c:if>

                        <c:if test="${!dto.isIncluded()}">
                            <a class="btn btn-primary"
                               href="/appendModule?mid=${dto.module().getId()}&fid=${folderId}"><i>Add</i></a>
                        </c:if>

                        <!--
                        dto.isIncluded();
                        dto.module();
            -->


                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


</body>
</html>
