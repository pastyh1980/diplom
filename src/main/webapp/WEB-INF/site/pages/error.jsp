<%--
  Created by IntelliJ IDEA.
  User: pasty
  Date: 14.04.2016
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 403</title>
</head>
<body>
<c:choose>
    <c:when test="${empty username}">
        <h2>You do not have permission to access this page!</h2>
    </c:when>
    <c:otherwise>
        <h2>Username : ${username} <br/>
            You do not have permission to access this page!</h2>
    </c:otherwise>
</c:choose>
</body>
</html>
