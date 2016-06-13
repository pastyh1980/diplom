<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список студентов</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />
        <p class="title-text">Список студентов</p>

        <table class="studlist">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Телефон</th>
                    <th>Адрес</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <c:forEach var="stud" items="${students}">
                <tr>
                    <td>${stud.id}</td>
                    <td>${stud.family}</td>
                    <td>${stud.name}</td>
                    <td>${stud.otchestvo}</td>
                    <td>${stud.phone}</td>
                    <td>${stud.address}</td>
                    <td><a href="/stud/${stud.id}" class="btn">Подробнее</a></td>
                    <td><a href="/stud/${stud.id}/edit" class="btn">Редактировать</a></td>
                </tr>
            </c:forEach>
        </table>
        <div id="addstud">
            <a href="/stud/newstud" class="btn">Добавить студента</a>
        </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
