<%--
  Created by IntelliJ IDEA.
  User: madina
  Date: 2/13/23
  Time: 10:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/utils/header.jsp"/>
<html>
<head>
    <title>Update card</title>
</head>
<body>
<div class="container">
    <p class="wl-p">Edit card</p>
    <form method="post" action="/editcard">
        <ul class="add-book">
            <input type="hidden" name="cardId" value="${card.getId()}">
            <input type="hidden" name="moduleId" value="${card.getModule().getId()}">
            <li class="b-inform-items">
                <div class="bi-item-title">Term</div>
                <input type="text" class="bi-item-input" name="term" value="${card.getTitle()}" required>
            </li>

            <li class="b-inform-items">
                <div class="bi-item-title">Definition</div>
                <input type="text" class="bi-item-input" name="description" value="${card.getDescription()}" required>
            </li>

<%--            <li class="b-inform-items">--%>
<%--                <div class="bi-item-title">Add a picture(optional)</div>--%>
<%--                <div class="bi-upl-file">--%>
<%--                    <div class="bi-upl-file-left">--%>
<%--                        <label for="bi-image" class="bi-upl-file-label">Choose file</label>--%>
<%--                        <input type="file" name="image" id="bi-image" aria-label="file example"--%>
<%--                               class="bi-upl-file-input clicking1">--%>
<%--                    </div>--%>
<%--                    <div class="bi-upl-file-right clicking1out">No File Chosen</div>--%>
<%--                </div>--%>
<%--            </li>--%>
        </ul>

        <div class="add-book save-btn" style="left: 30px">
            <button type="submit">Save</button>
        </div>
    </form>
</div>


</body>
</html>
