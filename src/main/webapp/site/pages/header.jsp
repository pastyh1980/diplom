<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
	<div class="head"><img src="${pageContext.servletContext.contextPath}/img/title.jpg" /></div>
	<div class="menu">
		<div class="main">
			<p><a href="${pageContext.servletContext.contextPath}/welcome">Главная</a></p>
		</div>
		<div class="stud">
			<p><a href="${pageContext.servletContext.contextPath}/stud/liststud">Студенты</a></p>
		</div>
		<div class="cards">
			<p><a href="${pageContext.servletContext.contextPath}/cards/">Успеваемость и посещаемость</a></p>
		</div>
		<div class="events">
			<p><a href=#>Мероприятия</a></p>
			<div class="events-down">
				<p><a href="/meet/">Родительские собрания</a></p>
				<p><a href=#>Классные часы</a></p>
				<p><a href=#>Конкурсы, олимпиады</a></p>
				<p><a href=#>Индивидуальная работа</a></p>
			</div>
		</div>
		<div class="usr">
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name != null}">
					<p>Вы вошли как: <a href=#>${userName}</a></p>
				</c:when>
				<c:otherwise>
					<p><a href="${pageContext.servletContext.contextPath}/login">Войти</a></p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>