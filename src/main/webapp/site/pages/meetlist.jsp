<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
    <title>Родительские собрания</title>
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />

    <p class="title-text">Список родительских собраний</p>

    <table class="meetlist">
        <thead>
            <tr>
                <th>ID</th>
                <th>Тема собрания</th>
                <th>Дата и время</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${parentMeetingsList}" var="meeting">
                <tr>
                    <td>${meeting.id}</td>
                    <td>${meeting.theme}</td>
                    <td>${meeting.date} ${meeting.time}</td>
                    <td><a href="/meet/${meeting.id}/edit" class="btn">Редактировать</a></td>
                    <td><a href="/meet/${meeting.id}/report" class="btn">Отчет</a></td>
                    <td><a href="/meet/${meeting.id}/delete" class="btn">Удалить</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div id="add_meeting">
        <a href="/meet/newmeet" class="btn">Новое собрание</a>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
