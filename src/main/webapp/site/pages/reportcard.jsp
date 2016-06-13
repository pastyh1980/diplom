<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cards.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
    <title>Успеваемость</title>
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />
    <p class="title-text">Табель за ${reportCardForm.month} ${reportCardForm.year} года</p>

    <spring:url value="/cards/savereportcard" var="saveCardUrl" />

    <form:form modelAttribute="reportCardForm" method="post" action="${saveCardUrl}" name="reportCardForm">
        <table class="rep_card">
            <thead>
                <tr>
                    <th>Студент/дисциплина</th>
                    <c:forEach items="${disciplines}" var="discipline">
                        <th class="vertical_text"><p class="vertical_text">${discipline.disciplineName}</p></th>
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
        <button id="save_report_card" type="submit" class="btn">Сохранить</button>
        <a id="generate_report_card" class="btn" href="/reports/reportcard?month=${reportCardForm.month.ordinal()}&year=${reportCardForm.year}">Генерация отчета</a>
        <form:hidden path="month" />
        <form:hidden path="year" />
    </form:form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
