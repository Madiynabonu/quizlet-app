<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/18/23
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<html>
<head>
    <title>Folders</title>
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>
        .avatar {
            vertical-align: middle;
            width: 50px;
            height: 50px;
            border-radius: 50%;
        }

        .btn {
            background-color: DodgerBlue; /* Blue background */
            border: none; /* Remove borders */
            color: white; /* White text */
            padding: 12px 16px; /* Some padding */
            font-size: 16px; /* Set a font size */
            cursor: pointer; /* Mouse pointer on hover */
        }

        /* Darker background on mouse-over */
        .btn:hover {
            background-color: RoyalBlue;
        }

        .button {
            padding: 15px 25px;
            font-size: 24px;
            text-align: center;
            cursor: pointer;
            outline: none;
            color: #fff;
            background-color: #04AA6D;
            border: none;
            border-radius: 15px;
            box-shadow: 0 9px #999;
        }

        .button:hover {
            background-color: paleturquoise
        }

        .button:active {
            background-color: powderblue;
            box-shadow: 0 5px #666;
            transform: translateY(4px);
        }
    </style>
    <%--    <jsp:include page="/fragments/css.jsp"/>--%>
    <jsp:include page="/utils/header.jsp"/>

</head>
<body style="background-color: #EFFDFD">
<div class="container" style="width: 80% ; margin-left: 10%">

    <form method="post" action="/folder/getfolder">
        <%--        <input type="hidden" name="folder" value="${folder}">--%>
        <%--        <input type="hidden" name="folderId" value="${folderId}">--%>
        <%--        <input type="hidden" name="description" value="${description}">--%>
        <%--        <input type="hidden" name="userId" value="${folder}">--%>

        <div style="background-color: #cff4fc ; border-radius: 4px">
            <div style="margin-left: 10%">
                <div style="margin-right: auto; padding-bottom: 20px; padding-top: 40px">
                    Your Folder <h2>${folder.getTitle()}</h2>
                </div>

                <%--                <button class="btn btn-outline-info" type="submit"><i style="font-size:24px" class="fa">&#xf15c;</i>--%>
                <%--                    <a href="/test" style="text-decoration: none">--%>
                <%--                        Test--%>
                <%--                    </a>--%>
                <%--                </button>--%>
            </div>
            <p></p>
        </div>
        <hr>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            </button>
            <button class="btn btn-light" type="button">
                <i class="fa fa-plus-square" style="font-size:18px;"></i>
                <a href="/setModule?fid=${folder.getId()}" style="color: black; text-decoration: none">
                    Add a Module
                </a>
            </button>
            <button class="btn btn-light" type="button">
                <i class='fas fa-edit'></i>
                <a href="/folder/update?id=${folder.getId()}" style="color: black; text-decoration: none">
                    Edit a Folder
                </a>
            </button>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                <i class="fa fa-trash"></i>
                Trash a Folder
            </button>
        </div>
        <p></p>
        <h1>Your All Modules</h1>



            <a class="btn btn-outline-info" href="/addModule?fid=${folder.getId()}" style="text-decoration: none">
                <i class="fa-solid fa-credit-card"></i>Create module
            </a>


        <div class="container">
            <div class="row">
                <div class="books_list">
                    <c:forEach items="${modules}" var="module">
                        <div class="card">
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${module.getName()}</li>
                                    <li class="list-group-item">${module.getDescription()}</li>
                                </ul>

                                <a class="btn btn-danger"
                                   href="/unsetModule?mid=${module.getId()}&fid=${folder.getId()}&next=/folder/getfolder?fid=${folder.getId()}"><i>Remove</i></a>
                                <a class="btn btn-primary"
                                   href="/getModule?mid=${module.getId()}"><i>More</i></a>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%--            <c:forEach items="${modules}" var="module">&ndash;%&gt;--%>
        <%--        <div class="col-lg-4 col-md-6 col-sm-12" style="margin-top: 5px">--%>
        <%--            <div class="card" style="height: 150px">--%>
        <%--                <div class="card-header">--%>
        <%--                    <b> ${module.getName()} </b>--%>
        <%--                </div>--%>
        <%--                <div class="card-body">--%>
        <%--                    <p class="card-text">${module.getName()}</p>--%>
        <%--                    <a href="/folder/getfolder?mid=${module.getId()}" type="button"--%>
        <%--                        &lt;%&ndash;                    <a href="/folder/update?id=${folder.getId()}" style="color: black; text-decoration: none">&ndash;%&gt;--%>

        <%--                       class="btn btn-outline-info">${module.getName()}</a>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        </c:forEach>

        <%----%>
</div>

<%--        <div class="row">--%>
<%--            <c:forEach items="${folders}" var="folder">--%>
<%--                <div class="col-lg-4 col-md-6 col-sm-12" style="margin-top: 5px">--%>
<%--                    <div class="card" style="height: 150px">--%>
<%--                        <div class="card-header">--%>
<%--                            <b> ${folder.getTitle()} </b>--%>
<%--                        </div>--%>
<%--                        <div class="card-body">--%>
<%--                            <p class="card-text">${folder.getDescription()}</p>--%>
<%--                            <a href="/getall" type="button"--%>
<%--                               class="btn btn-outline-info">${folder.getTitle()}</a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>

</form>
<!-- Button trigger modal -->

<form method="post" action="/folder/delete">
    <input type="hidden" name="folder" value="${folder}">
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <input type="hidden" name="folderId" value="${folder.getId()}">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Folder title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Are you sure to delete this Folder <b>${folder.getTitle()}</b>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-warning">
                        Yes
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>

<jsp:include page="/fragments/js.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>

</div>
</body>
</html>