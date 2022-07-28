<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here Are all the ads!</h1>

    <div class="form-control">
        <form method="post" action="/ads">
            <label for="search">Search:</label>
            <input type="search" id="search" name="search" placeholder="What would you like to see?">
            <input type="submit" value="Search">
        </form>
    </div>

    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <a href="/ads/view-ad?adName=${ad.title}"><h2>${ad.title}</h2></a>
            <p>${ad.description}</p>
        </div>
    </c:forEach>
</div>

</body>
</html>
