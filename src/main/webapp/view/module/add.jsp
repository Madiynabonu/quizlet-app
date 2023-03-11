<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/13/23
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <style>
        body {
            background-image: url(https://i.pinimg.com/564x/d8/ab/80/d8ab806a599f34b91cc08ebaa6b22d78.jpg);
        }

    </style>
    <title>Add Module Page</title>
<%--    <jsp:include page="/fragments/css.jsp"/>--%>
    <jsp:include page="/utils/header.jsp"/>


</head>
<body style="background: #EFFDFD;">


<%--<script>--%>
<%--    window.confirm('hello');--%>
<%--</script>--%>
<div class="container" style="width: 80% ; margin-left: 10%">
    <div style="margin-right: auto; padding-bottom: 40px; padding-top: 40px">
        <h3>Create a new learning Module</h3>
    </div>
    <form method="post" action="/addModule">
        <div style="margin-right: 100px">
            <input type="text" class="form-control no-border" id="name" name="name" required
            >
            <%--            <hr style="color: yellow ">--%>
            <h6>Name <span style="color: red"><sub>${invalidNameException}</sub></span></h6>

            <input type="hidden" name="userId"
                   value="<%=request.getSession().getAttribute("user_id")%>">
            <input type="hidden" name="fid"
                   value="${fid}">

            <input type="text" class="form-control no-border" id="description" name="description">
            <%--            <hr style="color: yellow">--%>
            <h6>Description</h6>
        </div>
        <p></p>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="radio" checked value="public" id="public">
            <label class="form-check-label" for="public">
                Public(default)
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="radio" value="private" id="private">
            <label class="form-check-label" for="private">
                Private
            </label>
        </div>
        <div class="d-grid gap-2 col-6 mx-auto">
            <%--    <button class="book-upload-btn"><a style="color:white" href="/books/update/${book.getId()}">Update</a>--%>

            <button class="btn btn-outline-primary" type="submit">
                Create Module
            </button>

        </div>
    </form>
</div>

<%--<jsp:include page="/fragments/js.jsp"/>--%>
</body>
</html>
