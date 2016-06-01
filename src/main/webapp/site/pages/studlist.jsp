<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: pasty
  Date: 27.04.2016
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students List</title>
</head>
<body>
<h2>All students</h2>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Телефон</th>
        </tr>
    </thead>
    <c:forEach var="stud" items="${students}">
        <tr>
            <td>${stud.id}</td>
            <td>${stud.family}</td>
            <td>${stud.name}</td>
            <td>${stud.otchestvo}</td>
            <td>${stud.phone}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
