<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/13/23
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class delete</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="row container" style="width: 60%;margin: auto;align-items: center;justify-content: center;margin-top:10%">
    <div class="col-md-10 offset-1">
        <h1 class="text-center">Class delete Page</h1>
        <form method="post" action="/group/delete?groupId=${group.getId()}">
            <input type="hidden" name="id" value="${group.getId()}">
            <div class="alert alert-danger">
                <p>Are you sure delete user <i>${group.getName()}</i></p>
            </div>
            <a href="/group" class="btn btn-success">Back</a>
            <button type="submit" class="btn btn-primary">Yes</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
</body>
</html>
