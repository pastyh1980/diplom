<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cards.css" />
    <spring:url value="/cards/newreportcard" var="newReportCardUrl" />
    <spring:url value="/cards/newvisitingcard" var="newVisitingCardUrl" />
    <script type="text/javascript">
        function addReportCard() {
            document.getElementById('popup-parent').style.display = 'block';
            document.forms.newReportCardForm.action = '${newReportCardUrl}';
        }

        function addVisitingCard() {
            document.getElementById('popup-parent').style.display = 'block';
            document.forms.newReportCardForm.action = '${newVisitingCardUrl}';
        }
    </script>
    <title>Card list</title>
</head>
<body>
<div id="reportCards">
    <p>Табели успеваемости</p>
    <table>
        <c:forEach items="${cardMap.keySet()}" var="card" varStatus="iter">
            <tr>
                <spring:url value="/cards/editreportcard?month=${cardMap[card][0]}&year=${cardMap[card][1]}" var="editUrl" />
                <td><p>${card}</p></td>
                <td><a href="${editUrl}"} />Редактировать</td>
            </tr>
        </c:forEach>
    </table>

    <p onclick="addReportCard()">Добавить табель</p>
</div>

<div id="visitingCards">
    <p>Табели посещаемости</p>
    <table>
        <c:forEach items="${visitingCardMap.keySet()}" var="card" varStatus="iter">
            <tr>
                <spring:url value="/cards/editvisitingcard?month=${visitingCardMap[card][0] - 1}&year=${visitingCardMap[card][1]}" var="editUrl" />
                <td><p>${card}</p></td>
                <td><a href="${editUrl}"} />Редактировать</td>
            </tr>
        </c:forEach>
    </table>

    <p onclick="addVisitingCard()">Добавить табель</p>
</div>

    <div id="popup-parent">
        <div id="popup">
            <p onclick="document.getElementById('popup-parent').style.display='none'">Закрыть</p>
            <form:form method="post" modelAttribute="newReportCardForm" action="${newReportCardUrl}" name="newReportCardForm">
                <spring:bind path="month">
                    <form:select path="month">
                        <form:options items="${months}" />
                    </form:select>
                </spring:bind>

                <spring:bind path="year">
                    <form:input path="year" />
                </spring:bind>

                <button type="submit">Создать</button>
            </form:form>
        </div>
    </div>
</body>
</html>
