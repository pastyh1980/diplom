
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User form</title>
</head>
<body>

<c:choose>
    <c:when test="${userForm.id != 0}">
        <h2>Update user ${userForm.login}</h2>
    </c:when>
    <c:otherwise>
        <h2>Create a new user</h2>
    </c:otherwise>
</c:choose>

<spring:url value="/auth/reguser" var="saveUserUrl" />

<form:form method="post" modelAttribute="userForm" action="${saveUserUrl}">
    <form:hidden path="id" />
    <spring:bind path="login">
        <div>
            <label>Login</label>
            <form:input path="login" id="login" placeholder="login" />
            <form:errors path="login" />
        </div>
    </spring:bind>

    <spring:bind path="passwd">
        <div>
            <label>Password</label>
            <form:password path="passwd" id="passwd" placeholder="password" />
            <form:errors path="passwd" />
        </div>
    </spring:bind>

    <spring:bind path="confirmPasswd">
        <div>
            <label>Confirm password</label>
            <form:password path="confirmPasswd" id="passwd" placeholder="passwd" />
            <form:errors path="confirmPasswd" />
        </div>
    </spring:bind>

    <spring:bind path="type">
        <div>
            <label>Type</label>
            <form:select path="type" id="type" placeholder="user type">
                <form:options items="${userTypes}" />
            </form:select>
        </div>
    </spring:bind>

    <spring:bind path="enabled">
        <label>Enabled</label>
        <form:checkbox path="enabled" id="enabled"/>
    </spring:bind>

    <c:choose>
        <c:when test="${userForm.id != 0}">
            <button type="submit">Update user</button>
        </c:when>
        <c:otherwise>
            <button type="submit">Create user</button>
        </c:otherwise>
    </c:choose>
</form:form>

</body>
</html>