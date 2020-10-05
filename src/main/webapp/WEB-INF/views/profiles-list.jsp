<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Users Search</title>
</head>
<body>
    <c:if test="${users.size() > 0}">
        <p>List of users:<p>
    </c:if>
    <table>
        <c:forEach var = "user" items="${users}">
            <tr>
                <td>ID: <c:out value="${user.id}"/><td>
                <td>User Name: <c:out value = "${user.username}"/><td>
            </td>
        </c:forEach>
    </table>
</body>
</html>
