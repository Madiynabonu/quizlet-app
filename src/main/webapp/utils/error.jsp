<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/17/23
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<html>
<head>
    <title>Error</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:700" rel="stylesheet">
    <style>
        h1 {
            font-size: 80px;
            font-weight: 800;
            text-align: center;
            font-family: 'Roboto', sans-serif;
        }

        h2 {
            font-size: 25px;
            text-align: center;
            font-family: 'Roboto', sans-serif;
            margin-top: -40px;
        }

        p {
            text-align: center;
            font-family: 'Roboto', sans-serif;
            font-size: 12px;
        }

        .container {
            width: 300px;
            margin: 0 auto;
            margin-top: 15%;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>${message1}</h1>
    <p>${message2} Go to  <a href="${url}">${message3}</a></p>
</div>
</body>
</html>
