<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cards.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
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
    <title>Табели</title>
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />
    <div id="reportCards">
        <p class="title-text small">Табели успеваемости</p>
        <table>
            <c:forEach items="${cardMap.keySet()}" var="card" varStatus="iter">
                <tr>
                    <spring:url value="/cards/editreportcard?month=${cardMap[card][0]}&year=${cardMap[card][1]}" var="editUrl" />
                    <td><p>${card}</p></td>
                    <td><a href="${editUrl}" class="btn" />Редактировать</td>
                </tr>
            </c:forEach>
        </table>

        <p class="btn" onclick="addReportCard()">Добавить табель</p>
    </div>

    <div id="visitingCards">
        <p class="title-text small">Табели посещаемости</p>
        <table>
            <c:forEach items="${visitingCardMap.keySet()}" var="card" varStatus="iter">
                <tr>
                    <spring:url value="/cards/editvisitingcard?month=${visitingCardMap[card][0] - 1}&year=${visitingCardMap[card][1]}" var="editUrl" />
                    <td><p>${card}</p></td>
                    <td><a href="${editUrl}" class="btn" />Редактировать</td>
                </tr>
            </c:forEach>
        </table>

        <p class="btn" onclick="addVisitingCard()">Добавить табель</p>
    </div>

    <div class="block"></div>

    <div id="popup-parent">
        <div id="popup">
            <p onclick="document.getElementById('popup-parent').style.display='none'">Закрыть</p>
            <form:form method="post" modelAttribute="newReportCardForm" action="${newReportCardUrl}" name="newReportCardForm">
                <table>
                    <tr>
                    <spring:bind path="month">
                        <td>Месяц:</td>
                        <td>
                            <form:select path="month">
                                <form:options items="${months}" />
                            </form:select>
                        </td>
                    </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="year">
                            <td>Год:</td>
                            <td>
                                <form:input path="year" maxlength="4" />
                            </td>
                        </spring:bind>
                    </tr>
                </table>
                <button class="btn" type="submit">Создать</button>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
