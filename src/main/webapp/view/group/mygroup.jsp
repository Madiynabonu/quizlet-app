<%@ page import="com.nooglers.domains.Class" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mygroup</title>
    <%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"--%>
    <%--          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">--%>
    <%--    <link rel="stylesheet" href="/css/bootstrap.min.css">--%>
    <%@ include file="/utils/header.jsp" %>
</head>
<body>


<div class="container" style="margin-top: 20px">

    <%--            <p>layer3</p>--%>
    <%--            <div class="collapse multi-collapse" id="multiCollapseExample1">--%>

    <p>
        <b>School Name: </b> ${group.getSchoolName()} <br/>
        <b>Group Name: </b> ${group.getName()} <br/>
        <b>Members: </b> ${group.getUsers().size()+1} <br/></p>
    <%if (!(boolean) request.getAttribute("isOwner")) {%>
    <div class="card card-body">
        <%Class group = (Class) request.getAttribute("group");%>
        <%if (group.isPermissionToUpdateSets()) {%>
        <a href="/group/update?groupId=${group.getId()}" class="btn btn-warning m-3">
            Update
        </a>
        <%}%>
        <%if (group.isPermissionToInvite()) {%>
        <a href="/group/member?groupId=${group.getId()}" class="btn btn-success m-3">
            Add member
        </a>
        <%}%>
        <%if (group.isPermissionToUpdateSets()) {%>
        <a href="/class/module/get?groupId=${group.getId()}" class="btn btn-success m-3">
            Add Set
        </a>
        <%}%>
    </div>
    <%} else {%>
    <div class="card card-body">
        <a href="/group/update?groupId=${group.getId()}" class="btn btn-warning m-3">
            Update
        </a>
        <a href="/group/delete?groupId=${group.getId()}" class="btn btn-danger m-3">
            Delete
        </a>
        <a href="/group/member?groupId=${group.getId()}" class="btn btn-success m-3">
            Add member
        </a>
        <a href="/class/module/get?groupId=${group.getId()}" class="btn btn-success m-3">
            Add Set
        </a>
    </div>
    <%}%>
    <div class="row ">
        <c:forEach items="${modules}" var="module">
            <div class="col-lg-2 col-md-2 col-sm-3" style="margin-top: 5px">
                <div class="card" style="height: 200px; width: 200px">
                    <div class="card-header">
                            ${module.getName()}
                    </div>
                    <div class="card-body">
                        <p class="card-text">${module.getDescription()}</p>
                        <a href="/getModule?mid=${module.getId()}"
                           class="btn btn-outline-info">${module.getName()}
                        </a>

                        <c:set value="${(isOwner or isUpdateAble)}" var="is"/>
                        <c:if test="${is}">
                            <a href="/class/module/remove?groupId=${groupId}&moduleId=${module.getId()}"
                               class="btn btn-success m-3">
                                Remove
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


</body>
</html>
<%--<%@ include file="/fragments/js.jsp" %>--%>