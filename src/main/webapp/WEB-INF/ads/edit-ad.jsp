<%--
  Created by IntelliJ IDEA.
  User: theportableone
  Date: 7/28/22
  Time: 9:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${ad.title}" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <h1>Edit or Delete ${ad.title}</h1>
    <form action="/ads/edit-ad" method="post">
        <div class="container">
            <label for="title">Title:</label>
            <input type="text" name="title" id="title" value="${ad.title}">
            <label for="description">Description</label>
            <input type="text" name="description" id="description" value="${ad.description}">
        </div>
        <div class="btn-group">
                <input type="submit" class="btn btn-primary btn-block" name="edit" value="Submit Updates">
                <input type="submit" class="btn btn-primary btn-block" name="edit" value="Delete Ad">

        </div>
    </form>
</body>
</html>
