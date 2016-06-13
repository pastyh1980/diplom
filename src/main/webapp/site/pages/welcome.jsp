<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />

    <sec:authorize access="hasAnyRole('STUDENT', 'ADMIN')">
        <c:url value="/j_spring_security_logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                User: ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()">Logout</a>
            </h2>
        </c:if>
    </sec:authorize>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
