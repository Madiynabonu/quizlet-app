<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/14/23
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/fragments/css.jsp" %>

<html>
<head>
    <title>FlashCard</title>
</head>

<style>
    body {
    background-color: #EFFDFD !important;
    }
</style>

<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid" style="color:rgba(255,226,144,0.17)">

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/home">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Your Library
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/listModule">Study Modules</a></li>
                        <li><a class="dropdown-item" href="/group">Class</a></li>
                        <li><a class="dropdown-item" href="/folder/folders">Folder</a></li>
                    </ul>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Create
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/addModule">Module</a></li>
                        <li><a class="dropdown-item" href="/group/add">Class</a></li>
                        <li><a class="dropdown-item" href="/folder/create">Folder</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/records">Records</a>
                </li>

                <div class="d-flex">
                    <%if ( session.getAttribute("user_id") != null ) {%>
                    <li class="nav-item">
                        <a class="btn btn-danger" aria-current="page" href="/logout">LogOut</a>
                    </li>
                    <%} else {%>
                    <li class="nav-item">
                        <a class="btn btn-success" aria-current="page" href="/login">LogIn</a>
                    </li>
                    <%}%>
                </div>
            </ul>
            <form class="d-flex" role="search" action="/search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>


<%--<ul>--%>
<%--    <li><a class="active" href="/home">Home</a></li>--%>
<%--    <li><a href="/library">Your library</a></li>--%>
<%--    <li><a href="/create">Create</a></li>--%>
<%--    <%if ( session.getAttribute("remember_me") != null ) {%>--%>
<%--    <li><a href="/logout">Create</a>LogOut</li>--%>
<%--    <%} else {%>--%>
<%--    <li><a href="/login">LogOut</a></li>--%>
<%--    <%}%>--%>

<%--    <li>--%>
<%--        <form action="/search" target="_blank">--%>
<%--            <input type="search"><br><br>--%>
<%--            <button>Search</button>--%>
<%--        </form>--%>
<%--    </li>--%>

<%--</ul>--%>

<%@ include file="/fragments/js.jsp" %>
</body>
</html>
