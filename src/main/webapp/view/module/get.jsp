<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.eclipse.tags.shaded.org.apache.xpath.operations.Mod" %>
<%@ page import="com.nooglers.domains.Module" %>
<%@ page import="com.nooglers.configs.ThreadSafeBeansContainer" %>
<%@ page import="com.nooglers.services.userprogress.UserProgressService" %>
<%@ page import="com.nooglers.domains.progress.UserProgress" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 15/02/23
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Module Page</title>
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .avatar {
            vertical-align: middle;
            width: 50px;
            height: 50px;
            border-radius: 50%;
        }

        .progress {
            text-decoration: underline;
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

    <jsp:include page="/utils/header.jsp"/>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body style="background-color: #EFFDFD">
<div class="container" style="width: 80% ; margin-left: 10%">


    <form method="post">
        <input type="hidden" name="moduleName" value="${moduleName}">
        <input type="hidden" name="moduleId" value="${moduleId}">
        <input type="hidden" name="module" value="${module}">
        <input type="hidden" name="userId" value="${userId}">

        <div style="background-color: #cff4fc ; border-radius: 4px">
            <div style="margin-left: 10%">
                <div style="margin-right: auto; padding-bottom: 20px; padding-top: 40px">
                    Your module <h2>${module.getName()}</h2>
                </div>
                <h4>Self-Study</h4>
                    <a href="/addcard?mid=${module.getId()}" class="btn btn-outline-info" style="text-decoration: none">
                        Add cards
                        <i class="fa-solid fa-credit-card"></i>
                    </a>

                <%
                    final Module module = ( Module ) request.getAttribute("module");
                    System.out.println(module);
                    module.getId();
                %>
                <a class="btn btn-outline-info" style="font-size:24px;text-decoration: none"
                   href="/test?mid=${module.getId()}">
                    Test <i style="font-size:24px" class="fa">&#xf15c;</i>
                </a>

            </div>
            <p></p>
        </div>
        <hr>
        <div class=" d-grid gap-2 d-md-flex justify-content-md-end">
            <button class="btn btn-light" type="button">
                <i class='fas fa-edit'></i>
                <a href="/editModule?id=${module.getId()}" style="color: black; text-decoration: none">
                    Edit Module
                </a>
            </button>
            <a class="btn btn-danger" href="/deleteModule?mid=${module.getId()}">
                <!--data-bs-toggle="modal" data-bs-target="#exampleModal">--->
                <i class="fa fa-trash"></i>
                Trash module
            </a>
        </div>
        <p></p>
        <h3>Your Learning Process</h3>


        <div class="container">
            <c:if test="${ !newAdded.isEmpty()}">
            <div class="accordion-item m-4">
                <h2 class="accordion-header" id="headingOne">
                    <button class="accordion-button btn btn-success-success" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne"
                            aria-expanded="false" aria-controls="collapseOne">
                        <span class="progress" style="color: red">Weak(${newAdded.size()})</span>
                    </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                     data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <c:forEach items="${newAdded}" var="l">
                            <span style="text-decoration: underline">${l.getCard().getTitle()} ⭐️ </span>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        </c:if>

        <c:if test="${ !inProgress.isEmpty()}">
        <div class="accordion-item m-4">
            <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button btn btn-success-success" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseTwo"
                        aria-expanded="false" aria-controls="collapseTwo">
                    <span class="progress" style="color: orange">Medium(${inProgress.size()})</span>
                </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                 data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    <c:forEach items="${inProgress}" var="l">
                        <span>${l.getCard().getTitle()} ⭐️ </span>
                    </c:forEach>
                </div>
            </div>
        </div>
        </c:if>


        <c:if test="${ !mastered.isEmpty()}">
        <div class="accordion-item-m4">
            <h2 class="accordion-header" id="headingThree">
                <button class="accordion-button btn btn-success-success" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseThree"
                        aria-expanded="false" aria-controls="collapseThree">
                    <span class="progress" style="color: green">Strong(${mastered.size()})</span>
                </button>
            </h2>
            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                 data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    <c:forEach items="${mastered}" var="l">
                        <span>${l.getCard().getTitle()} ⭐️ </span>
                    </c:forEach>

                </div>
            </div>
        </div>
        </c:if>
</div>

<div class="container">

    <div class="row"></div>
    <div class="books_list">

        <c:forEach items="${cards}" var="card">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${card.getTitle()}</h5>
                    <a class="btn btn-warning" href="/editcard?cardId=${card.getId()}">Update</a>
                    <a class="btn btn-danger" href="/deletecard?cardId=${card.getId()}">Delete</a>
                </div>
            </div>
        </c:forEach>

    </div>
</div>


<%--<form method="post" action="/deleteModule">--%>
<%--    <!-- Modal -->--%>
<%--    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"--%>
<%--         aria-hidden="true">--%>
<%--        <div class="modal-dialog">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <input type="hidden" name="moduleId" value="${module.getId()}">--%>
<%--                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    Are you sure to delete modal <b>${module.getName()}</b>--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                    <button type="submit" class="btn btn-warning">--%>
<%--                        Yes--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</form>--%>

<%--    <jsp:include page="/fragments/js.jsp"/>--%>
</div>
</body>
</html>
