<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
</head>
<body onload="document.loginForm.j_username.focus();">
<div class="wrap">

    <jsp:include page="header.jsp" />

    <div id="login-box">

        <p>Пожалуйста, авторизуйтесь!</p>

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
                    <td>Логин:</td>
                    <td><input type='text' name='j_username'></td>
                </tr>
                <tr>
                    <td>Пароль:</td>
                    <td><input type='password' name='j_password' /></td>
                </tr>
                <tr>
                    <td colspan='2' align="center"><input name="submit" type="submit" value="Войти" class="btn" /></td>
                </tr>
            </table>

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />

        </form>
    </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
