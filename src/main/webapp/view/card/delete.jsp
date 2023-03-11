<%@ page import="com.nooglers.domains.Card" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete card</title>

    <jsp:include page="/utils/header.jsp"/>
</head>
<body>
<div class="container">
    <p class="wl-p">Delete a card</p>
    <div class="addcategory">
        <div class="category-name" style="font-size: 30px"><i>Do you agree to delete a card
            <b>${card.getTitle()}</b>?</i></div>
        <form method="get" action="/deletecard">
            <input type="hidden" name="cardId" value="${card.getId()}">
            <%--            <input type="hidden" name="moduleId" value="${card.getModule().getId()}">--%>
            <%
                final Card card = ( Card ) request.getAttribute("card");
                System.out.println(card.getModule().getId());

            %>
            <input type="hidden" name="moduleId" value="${card.getModule().getId()}">
            <button type="submit" class="save-category-btn" onclick="alert('Card has been successfully deleted')">Yes,I
                agree
            </button>

        </form>
    </div>
</div>
</body>
</html>
