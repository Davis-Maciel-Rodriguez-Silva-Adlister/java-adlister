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
                    <input type="text" name="changeEmailTo" id="changeEmailTo" placeholder="Change Email To">
                    <input type="submit" id="changeEmail" class="btn btn-primary btn-block" name="changeEmail" />
                </form>
                </div>
                <div class="col-md-4">
                <form method="POST" action="/profile">
                    <label for="changePasswordTo">Change Password</label><br>
                    <input type="password" name="changePasswordTo" id="changePasswordTo" placeholder="Change Password To">
                    <input type="password" name="confirmPasswordTo" id="confirmPasswordTo" placeholder="Confirm Password To">
                    <input type="submit" id="changePassword" class="btn btn-primary btn-block" name="changePassword" />
                </form>
                </div>
            </div>
        <h2>Your Ads</h2>
        <a href="/ads/create"><button type="button" class="btn-block btn btn-primary" id="newAd" name="newAd">Create Ad</button></a>
        <div>
            <c:forEach var="ad" items="${ads}">
                <div>
                    <h2>${ad.title}</h2>
                    <p>${ad.description}</p>
                    <a href="/ads/edit-ad?adId=${ad.id}"><button type="submit" class="btn btn-primary btn-bloc" name="adId=${ad.id}">Edit Ad</button></a>
                </div>
            </c:forEach>
        </div>
    </div>

</body>
</html>
