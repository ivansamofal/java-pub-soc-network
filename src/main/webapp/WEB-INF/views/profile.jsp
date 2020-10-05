<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Search</title>
    <script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>
    <script src="/socialnetwork/resources/js/scripts.js"></script>
</head>
<body>
    <c:if test="${user != null}">
        <p>
            <c:out value="${user.username}"/>
            <c:if test="${userAge != null}">
              , <span><c:out value="${userAge}"/> years</span>
            </c:if>
        <p>
    </c:if>

    <c:if test="${user != null && user.id != currentUser.id}">
        <c:if test="${!hasLike}">
            <button class="profile-like" data-id="<c:out value="${user.id}"/>">Like</button>
        </c:if>
        <c:if test="${hasLike}">
            <button class="profile-like" data-id="<c:out value="${user.id}"/>">UnLike</button>
        </c:if>

        <c:if test="${!hasFriendship}">
            <button class="profile-friend" data-id="<c:out value="${user.id}"/>">Add to friends</button>
        </c:if>

        <c:if test="${hasFriendship}">
            <button class="profile-friend" data-id="<c:out value="${user.id}"/>">Remove from friends</button>
        </c:if>
    </c:if>

</body>
</html>
