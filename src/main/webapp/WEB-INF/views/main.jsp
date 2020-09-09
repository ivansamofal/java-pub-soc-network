<%--
  Created by IntelliJ IDEA.
  User: a18261712
  Date: 04.09.2020
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>--%>
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
