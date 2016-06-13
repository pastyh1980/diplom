<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сведения</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />
    <div class="stud-info">
        <p class="title-text small">Сведения о студенте</p>
        <table>
            <tr><td>Фамилия студента:</td> <td>${studForm.studFamily}</td></tr>
            <tr><td>Имя студента:</td> <td>${studForm.studName}</td></tr>
            <tr><td>Отчество студента:</td> <td>${studForm.studOtchestvo}</td></tr>
            <tr><td>Дата рождения студента:</td> <td>${studForm.studBorn}</td></tr>
            <tr><td>Телефон студента:</td> <td>${studForm.studPhone}</td></tr>
            <tr><td>Адрес студента:</td> <td>${studForm.address}</td></tr>
            <tr><td>Условия проживания студента:</td> <td>${studForm.livingCondition}</td></tr>
            <tr><td>Статус семьи:</td> <td>${studForm.familyStatus}</td></tr>
            <c:if test="${studForm.post != null}">
                <tr><td>Должность студента:</td> <td>${studForm.post}</td></tr>
            </c:if>
        </table>
    </div>
    <div class="parent-info">
        <p class="title-text small">Сведения о родителях</p>
        <c:forEach items="${studForm.parents}" var="parent">
            <table>
                <tr><td>Тип родителя:</td> <td>${parent.parentType}</td></tr>
                <tr><td>Фамилия родителя:</td> <td>${parent.family}</td></tr>
                <tr><td>Имя родителя:</td> <td>${parent.name}</td></tr>
                <tr><td>Отчество родителя:</td> <td>${parent.otchestvo}</td></tr>
                <tr><td>Телефон родителя:</td> <td>${parent.phone}</td></tr>
                <tr><td>Место работы, должность родителя:</td> <td>${parent.work}</td></tr>
                <tr><td>Образование родителя:</td> <td>${parent.educationType}</td></tr>
            </table>
        </c:forEach>
    </div>
    <div class="orders-info">
        <p class="title-text small">Сведения о приказах</p>
        <c:forEach items="${studForm.orders}" var="order">
            <table>
                <tr><td>Приказ о ${order.orderType} № ${order.number} от ${order.date}</td></tr>
            </table>
        </c:forEach>
    </div>

    <div id="editstud">
        <a href="/stud/${studForm.id}/edit" class="btn">Редактировать</a>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
