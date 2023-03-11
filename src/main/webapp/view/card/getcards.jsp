<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Card list</title>
    <jsp:include page="/utils/header.jsp"/>
    <style>
        .btn-link:hover {
            color: #20c997;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="books_list">
            <c:forEach items="${cards}" var="card">
                <div class="card">
                    <div class="card-body">
                        <form method="post">
                            <img src="/download?filename=${card.getDocument().getGeneratedFileName()}" width="180"
                                 height="280" class="card-img-top" loading="lazy"/>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">${card.getTitle()}</li>
                                <li class="list-group-item">${card.getDescription()}</li>
                            </ul>
                            <button type="submit" class="btn btn-link" href="/editcard" name="button1"
                                    value="update/${card.getId()}"><i>Edit</i></button>
                            <button type="submit" class="btn btn-link" href="/deletecard" name="button2"
                                    value="delete/${card.getId()}"><i>Delete</i></button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <a class="btn btn-info" style="color:black; margin-left: 93%; background-color: lawngreen" href="/cardmain">Back</a>
</div>

</script>
</body>
</html>
