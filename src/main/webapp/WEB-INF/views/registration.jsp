<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Search</title>
</head>
<body>
    <c:if test="${services.size() > 0}">
        <p>List of services:<p>
    </c:if>
    <table>
        <c:forEach var = "service" items="${services}">
            <tr>
                <td>Price: <c:out value="${service.price}"/><td>
                <td>Views Count: <c:out value = "${service.viewsCount}"/><td>
                <td>UserName: <c:out value = "${service.getUser().username}"/><td>
            </td>
        </c:forEach>
    </table>
</body>
</html>
