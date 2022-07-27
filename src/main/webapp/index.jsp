<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Welcome to my site!" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Welcome to the Adlister!</h1>
        <div class="btn-group">
            <a href="/login"><div class="btn btn-primary btn-block">Login</div></a>
            <a href="/ads"><div class="btn btn-primary btn-block">See all ads</div></a>
        </div>
<%--        <ul>--%>
<%--        <c:forEach var="category" items="${categories}">--%>
<%--            <li>${category}</li>--%>
<%--        </c:forEach>--%>
<%--        </ul>--%>
    </div>
</body>
</html>
