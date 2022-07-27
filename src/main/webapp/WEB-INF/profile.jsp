<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
            <div>
                <h2>Change/Update Profile Information</h2>
                <div class="col-md-4">
                <form method="POST" action="/profile">
                    <label for="changeUsernameTo">Current Username: ${sessionScope.user.username}</label><br>
                    <input type="text" name="changeUsernameTo" id="changeUsernameTo" placeholder="Change Username To">
                    <input type="submit" id="changeUsername" class="btn btn-primary btn-block" name="changeUsername" />
                </form>
                </div>
                <div class="col-md-4">
                <form method="POST" action="/profile">
                    <label for="changeEmailTo">Current Email: ${sessionScope.user.email}</label>
                    <input type="text" id="changeEmailTo" placeholder="Change Email To">
                    <input type="submit" id="ChangeEmail" class="btn btn-primary btn-block" name="changeEmail" />
                </form>
                </div>
                <div class="col-md-4">
                <form method="POST" action="/profile">
                    <label for="changePasswordTo">Change Password</label><br>
                    <input type="password" id="changePasswordTo" placeholder="Change Password To">
                    <input type="submit" id="changePassword" class="btn btn-primary btn-block" name="changePassword" />
                </form>
                </div>
            </div>
        <div>
            <c:forEach var="ad" items="${ads}">
                <div>
                    <h2>${ad.title}</h2>
                    <p>${ad.description}</p>
                    <button type="submit" class="btn btn-primary btn-bloc" name="adId=${ad.id}">Delete</button>
                    <button type="submit" class="btn btn-primary btn-bloc" name="adId=${ad.id}">Update Ad</button>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>
