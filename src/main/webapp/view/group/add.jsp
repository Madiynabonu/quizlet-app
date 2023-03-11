<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/utils/header.jsp"%>
<html>
<head>
    <title>Class add page</title>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">--%>
</head>
<body>
<%--

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
    Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
--%>

<div class="container col-lg-10 col-md-8 col-sm-6">
    <form method="post" action="/group/add" style="width: 60%;margin-left: 20%;margin-top: 5%">
        <div class="mb-3">
            <label for="exampleInputName" class="form-label">Enter class name</label>
            <input type="text" class="form-control" id="exampleInputName" aria-describedby="emailHelp" name="classname"
                   required>
        </div>
        <div class="mb-3">
            <label for="exampleInputSchoolName" class="form-label">School name</label>
            <input type="text" class="form-control" id="exampleInputSchoolName" name="schoolname" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control" id="description" name="description" placeholder="Optional">
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" name="updatepermission" class="form-check-input" id="exampleCheckUpdate">
            <label class="form-check-label" for="exampleCheckUpdate">Permission to update</label>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" name="invitepermission" class="form-check-input" id="exampleCheckInvite">
            <label class="form-check-label" for="exampleCheckInvite">Permission to invite</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"--%>
<%--        crossorigin="anonymous"></script>--%>
</body>
</html>
<%--<%@ include file="/fragments/js.jsp" %>--%>
