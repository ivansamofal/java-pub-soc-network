<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${companies.size() > 0}">
        <p>List of companies:<p>
    </c:if>
    <table>
        <c:forEach var = "company" items="${companies}">
            <tr>
                <td>Name: <c:out value="${company.name}"/><td>
                <td>Type: <c:out value = "${company.company_type}"/><td>
            </td>
        </c:forEach>
    </table>
</body>
</html>
