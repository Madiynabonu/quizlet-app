<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/13/23
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Edit Module Page</title>
    <jsp:include page="/utils/header.jsp"/>
<%--    <jsp:include page="/fragments/css.jsp"/>--%>


</head>
<body style="background-color: #EFFDFD">
<div class="row">
    <div class="col-md-10 offset-1">
        <h2>Edit Module </h2>
        <form method="post">
            <input type="hidden" name="moduleId" value="${module.getId()}">
            <input type="hidden" name="module" value="${module}">
            <input type="hidden" name="userId" value="${userId}">
            <div style="margin-right: 100px">
                <input type="text" class="form-control no-border" id="name" name="name" value="${module.getName()}"
                       placeholder="Enter a name, for example,'Biology and Photosynthesis and'">
                <h6>Name</h6>
                <input type="hidden" name="userId" value="${module.getCreatedBy()}">
                <p></p>
                <input type="text" class="form-control no-border" id="description" name="description"
                       value="${module.getDescription()}"
                       placeholder="Add description">
                <h6>Description</h6>
            </div>
            <p></p>
            <c:if test="${module.isPublic() == 'true'}">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="radio" value="public" id="public" checked>
                    <label class="form-check-label" for="public">
                        Public
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="radio" name="radio" value="private" id="private">
                    <label class="form-check-label" for="private">
                        Private
                    </label>
                </div>
            </c:if>
            <c:if test="${module.isPublic() != 'true'}">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="radio" value="public" id="public">
                    <label class="form-check-label" for="public">
                        Public
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="radio" name="radio" value="private" id="private" checked>
                    <label class="form-check-label" for="private">
                        Private
                    </label>
                </div>
            </c:if>

            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-outline-success" type="submit">Edit Module</button>
            </div>
        </form>
    </div>
</div>
<%--<jsp:include page="/fragments/js.jsp"/>--%>
</body>
</html>
