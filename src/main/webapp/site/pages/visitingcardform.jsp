<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Табель за ${visitingCardForm.month} ${visitingCardForm.year} года</p>

<spring:url value="/cards/savevisitingcard" var="saveCardUrl" />

<form:form modelAttribute="visitingCardForm" method="post" action="${saveCardUrl}" name="visitingCardForm">

    <table>
        <thead>
        <tr>
            <th>Студент/дата</th>
            <c:forEach items="${calendarList}" var="calendar">
                <th>${calendar}</th>
            </c:forEach>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${studentList}" var="student">
            <tr>
                <td><p>${student.family} ${student.name} ${student.otchestvo}</p></td>
                <c:forEach items="${calendarList}" var="calendar">
                    <td>
                        <form:hidden path="rows[${student.id}][${calendar}].id" />
                        <form:input path="rows[${student.id}][${calendar}].hour" size="1" />
                        <form:select path="rows[${student.id}][${calendar}].missType">
                            <form:options items="${missTypes}" />
                        </form:select>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button type="submit">Сохранить</button>
    <form:hidden path="month" />
    <form:hidden path="year" />
</form:form>
</body>
</html>
