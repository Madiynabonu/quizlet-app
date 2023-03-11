<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add card</title>
    <jsp:include page="/utils/header.jsp"/>
    <link rel="stylesheet" href="/utils/css/main.css">
</head>
<body>

<div class="container">
    <p class="wl-p">Add a new card</p>
    <form method="post">
        <ul class="add-book">
            <%--            <%request.setAttribute("moduleid",);%>--%>
            <input type="hidden" name="moduleid" value="${moduleid}">
            <input type="hidden" name="fid" value="${fid}">
            <li class="b-inform-items">
                <div class="bi-item-title">Term</div>
                <input type="text" class="bi-item-input" name="term" required>
                <span style="color: #fd7e14; padding: 0.5px;"><sub> <b><i>${invalidNameException}</i></b> </sub></span>
            </li>

            <li class="b-inform-items">
                <div class="bi-item-title">Definition</div>
                <input type="text" class="bi-item-input" name="description" required>
                <span style="color: #fd7e14; padding: 0.5px;"><sub> <b><i>${invalidDescriptionException}</i></b> </sub></span>
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
