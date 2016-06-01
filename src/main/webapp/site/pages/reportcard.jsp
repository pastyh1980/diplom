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
    <p>Табель за ${reportCardForm.month} ${reportCardForm.year} года</p>

    <spring:url value="/cards/savereportcard" var="saveCardUrl" />

    <form:form modelAttribute="reportCardForm" method="post" action="${saveCardUrl}" name="reportCardForm">
        <table>
            <thead>
                <tr>
                    <th>Студента/дисциплина</th>
                    <c:forEach items="${disciplines}" var="discipline">
                        <th>${discipline.disciplineName}</th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td><p>${student.family} ${student.name} ${student.otchestvo}</p></td>
                        <c:forEach items="${disciplines}" var="discipline">
                            <td>
                                <form:hidden path="rows[${student.id}]['${discipline.disciplineName}'].id" />
                                <form:select path="rows[${student.id}]['${discipline.disciplineName}'].rate">
                                    <form:option value="5" />
                                    <form:option value="4" />
                                    <form:option value="3" />
                                    <form:option value="2" />
                                    <form:option value="н/а" />
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
