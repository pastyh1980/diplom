<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/cards.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
    <script type="text/javascript">
        function showFirstPage() {
            var page1 = document.getElementsByClassName("str1");
            for(var k = 0; k < page1.length; k++){
                page1[k].style.display = "table-cell";
            }

            var page2 = document.getElementsByClassName("str2");
            for(var i = 0; i < page2.length; i++){
                page2[i].style.display = "none"; // depending on what you're doing
            }

            var page3 = document.getElementsByClassName("str3");
            for(var j = 0; j < page3.length; j++){
                page3[j].style.display = "none"; // depending on what you're doing
            }
        }

        function showSecondPage() {
            var page1 = document.getElementsByClassName("str1");
            for(var k = 0; k < page1.length; k++){
                //page2[i].style.visibility = "hidden"; // or
                page1[k].style.display = "none"; // depending on what you're doing
            }

            var page2 = document.getElementsByClassName("str2");
            for(var i = 0; i < page2.length; i++){
                //page2[i].style.visibility = "hidden"; // or
                page2[i].style.display = "table-cell"; // depending on what you're doing
            }

            var page3 = document.getElementsByClassName("str3");
            for(var j = 0; j < page3.length; j++){
                //page2[i].style.visibility = "hidden"; // or
                page3[j].style.display = "none"; // depending on what you're doing
            }
        }

        function showThirdPage() {
            var page2 = document.getElementsByClassName("str2");
            for(var i = 0; i < page2.length; i++){
                //page2[i].style.visibility = "hidden"; // or
                page2[i].style.display = "none"; // depending on what you're doing
            }

            var page3 = document.getElementsByClassName("str3");
            for(var j = 0; j < page3.length; j++){
                //page2[i].style.visibility = "hidden"; // or
                page3[j].style.display = "table-cell"; // depending on what you're doing
            }

            var page1 = document.getElementsByClassName("str1");
            for(var k = 0; k < page1.length; k++){
                //page2[i].style.visibility = "hidden"; // or
                page1[k].style.display = "none"; // depending on what you're doing
            }
        }
    </script>
    <title>Посещаемость</title>
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />
    <p class="title-text">Табель за ${visitingCardForm.month} ${visitingCardForm.year} года</p>

    <spring:url value="/cards/savevisitingcard" var="saveCardUrl" />

    <form:form modelAttribute="visitingCardForm" method="post" action="${saveCardUrl}" name="visitingCardForm">

        <table class="vis_card">
            <thead>
            <tr>
                <th>Студент/дата</th>
                <c:forEach items="${calendarList1}" var="calendar">
                    <th class="str1" style="display: table-cell;"><p>${calendar}</p></th>
                </c:forEach>
                <c:forEach items="${calendarList2}" var="calendar">
                    <th class="str2" style="display: none;"><p>${calendar}</p></th>
                </c:forEach>
                <c:forEach items="${calendarList3}" var="calendar">
                    <th class="str3" style="display: none;"><p>${calendar}</p></th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${studentList}" var="student">
                <tr>
                    <td><p>${student.family} ${student.name} ${student.otchestvo}</p></td>
                    <c:forEach items="${calendarList1}" var="calendar">
                        <td class="str1" style="display: table-cell;">
                            <form:hidden path="rows[${student.id}][${calendar}].id" />
                            <form:input path="rows[${student.id}][${calendar}].hour" cssClass="rate" />
                            <form:select path="rows[${student.id}][${calendar}].missType" cssClass="rate">
                                <form:options items="${missTypes}" />
                            </form:select>
                        </td>
                    </c:forEach>
                    <c:forEach items="${calendarList2}" var="calendar">
                        <td class="str2" style="display: none;">
                            <form:hidden path="rows[${student.id}][${calendar}].id" />
                            <form:input path="rows[${student.id}][${calendar}].hour" cssClass="rate" />
                            <form:select path="rows[${student.id}][${calendar}].missType" cssClass="rate">
                                <form:options items="${missTypes}" />
                            </form:select>
                        </td>
                    </c:forEach>
                    <c:forEach items="${calendarList3}" var="calendar">
                        <td class="str3" style="display: none;">
                            <form:hidden path="rows[${student.id}][${calendar}].id" />
                            <form:input path="rows[${student.id}][${calendar}].hour" cssClass="rate" />
                            <form:select path="rows[${student.id}][${calendar}].missType" cssClass="rate">
                                <form:options items="${missTypes}" />
                            </form:select>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <p class="btn one" onclick="showFirstPage()">Страница 1</p>
        <p class="btn two" onclick="showSecondPage()">Страница 2</p>
        <p class="btn three" onclick="showThirdPage()">Страница 3</p>
        <button id="save_visiting_card" type="submit" class="btn">Сохранить</button>
        <form:hidden path="month" />
        <form:hidden path="year" />
    </form:form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
