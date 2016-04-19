<%--
  Created by IntelliJ IDEA.
  User: pasty
  Date: 13.04.2016
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body onload="document.loginForm.username.focus();">
<h1>Spring Security Login Form (Database Authentication)</h1>

<div id="login-box">

    <h2>Login with Username and Password</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <c:url value='/j_spring_security_check' var="loginUrl" />
    <form name='loginForm'
          action="${loginUrl}" method='POST'>

        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='j_username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='j_password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="submit" /></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

    </form>
</div>
</body>
</html>
