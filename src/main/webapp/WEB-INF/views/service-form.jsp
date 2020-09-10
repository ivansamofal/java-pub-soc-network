<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title><c:out value="${action == 'create' ? 'Create' : 'Update'}"/> Service</title>
</head>
<body>
    <ul>
        <li>Price: <c:out value="${service.price}"/></li>
        <li>Active: <c:out value="${service.active ? 'Yes' : 'No'}"/></li>
        <li>Views count: <c:out value="${service.viewsCount}"/></li>
        <li>User Name: <c:out value="${service.user.username}"/></li>
    </ul>
</body>
</html>
