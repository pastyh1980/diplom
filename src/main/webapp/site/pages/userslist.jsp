<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: pasty
  Date: 17.04.2016
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users List</title>
</head>
<body>
    <h1>All users</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Password</th>
                <th>Type</th>
                <th>Enabled</th>
            </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.passwd}</td>
                <td>${user.type.name()}</td>
                <td>${user.enabled}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
