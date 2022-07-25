<%--
  Created by IntelliJ IDEA.
  User: alexrodriguez
  Date: 7/25/22
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="Ad" value="Ad" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
        <div class="container">
            <div class="ad-title">
                <c: >
                <h2></h2>
                </c:>
            </div>
        </div>

</body>
</html>
