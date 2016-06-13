<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/tcal.css" />
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/tcal.js"></script>
    <title>Родительское собрание</title>
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />
    <spring:url value="/meet/savemeeting" var="saveUrl" />

    <p class="title-text">Сведения о родительском собрании</p>

    <div class="meet-edit">
        <form:form method="post" modelAttribute="meetingForm" action="${saveUrl}" name="meetingForm">
            <form:hidden path="id" />
            <table>
                <tr>
                    <spring:bind path="theme">
                        <td><label>Тема собрания</label></td>
                        <td>
                            <form:input path="theme" id="theme" cssErrorClass="err"/>
                            <form:errors path="theme" />
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="date">
                        <td><label>Дата собрания</label></td>
                        <td>
                            <form:input path="date" id="date" cssClass="tcal" cssErrorClass="err"/>
                            <form:errors path="date" />
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="time">
                        <td><label>Время собрания</label></td>
                        <td>
                            <form:input path="time" id="time" cssErrorClass="err"/>
                            <form:errors path="time" />
                        </td>
                    </spring:bind>
                </tr>
            </table>

            <button type="submit" id="meetsave" class="btn">Сохранить</button>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
