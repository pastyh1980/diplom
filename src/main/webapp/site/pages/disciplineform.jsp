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

    <spring:url value="/discipline/save" var="saveUrl" />

    <form:form method="post" modelAttribute="discipline" action="${saveUrl}" name="discipline">
        <form:hidden path="id" />

        <spring:bind path="disciplineName">
            <div>
                <label>Название предмета</label>
                <form:input path="disciplineName" id="disciplineName" />
            </div>
        </spring:bind>

        <spring:bind path="discipline.prepod">
            <div>
                <label>Преподаватели</label>
                <form:input path="prepod" id="prepod" />
            </div>
        </spring:bind>

        <button type="submit">Сохранить</button>
    </form:form>

</body>
</html>
