<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/13/23
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Edit Folder Page</title>
    <jsp:include page="/utils/header.jsp"/>
    <%--    <jsp:include page="/fragments/css.jsp"/>--%>


</head>
<body style="background-color: #EFFDFD">
<div class="row">
    <div class="col-md-10 offset-1">
        <h2>Edit Folder </h2>
        <form method="post">
            <input type="hidden" name="folderId" value="${folder.getId()}">
            <input type="hidden" name="folder" value="${folder}">
            <input type="hidden" name="userId" value="${userId}">
            <div style="margin-right: 100px">
                <h6>Title</h6>
                <input type="text" class="form-control no-border" id="name" name="title" value="${folder.getTitle()}"
                       placeholder="Enter a title ">
                <h6>Description</h6>
                <input type="hidden" name="userId" value="${folder.getCreatedBy()}">
                <p></p>
                <input type="text" class="form-control no-border" id="description" name="description"
                       value="${folder.getDescription()}"
                       placeholder="Add description">
            </div>
            <p></p>


            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-outline-success" type="submit">Edit Module</button>
            </div>
        </form>
    </div>
</div>
<%--<jsp:include page="/fragments/js.jsp"/>--%>
</body>
</html>