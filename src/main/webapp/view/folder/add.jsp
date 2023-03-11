<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Add Folder</title>
    <jsp:include page="/utils/header.jsp"/>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<h1 class="text-center" style="margin-top: 30px; margin-bottom: 30px">Create a New Folder</h1>


<form  class="row g-3" method="post">
    <div class="col-md-5 offset-1">
        <label for="validationServer01" class="form-label">Title</label>
        <input type="text" class="form-control is-valid" id="validationServer01" name="title"
               placeholder="Enter a title">
        <input type="hidden" name="userId" value="${userId}">

    </div>


    <div class="col-md-10 offset-1" style="margin-bottom: 10px">
        <label for="validationTextarea1" class="form-label">Description</label>
        <textarea class="form-control " rows="3" id="validationTextarea1" name="description"
                  placeholder="Enter a Description (optional)"></textarea>
        <div class="invalid-feedback">
            field
        </div>
    </div>

    <div class="col-12 offset-1">
        <button class="btn btn-primary" type="submit">Create Folder</button>
    </div>
</form>
<%--<jsp:include page="/utils/header.jsp"/>--%>
</body>
</html>