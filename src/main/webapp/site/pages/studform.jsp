<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/tcal.css" />
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/tcal.js"></script>
    <script type="text/javascript">
        function addParent() {
            var form = document.forms.studForm;
            form.action = "/stud/addparent";
            form.submit();
        }

        function delParent(index) {
            var form = document.forms.studForm;
            form.action = "/stud/delparent/" + index;
            form.submit();
        }

        function addOrder() {
            var form = document.forms.studForm;
            form.action = "/stud/addorder";
            form.submit();
        }

        function delOrder(index) {
            var form = document.forms.studForm;
            form.action = "/stud/delorder/" + index;
            form.submit();
        }
        
        function addNewStud() {
            var form = document.forms.studForm;
            form.action = "/stud/addstud";
            form.submit();
        }

        function updateStud() {
            var form = document.forms.studForm;
            form.action = "/stud/updatestud";
            form.submit();
        }
    </script>
    <title>Stud form</title>
</head>
<body>
    <spring:url value="/stud/addstud" var="saveStudUrl"/>

    <form:form method="post" modelAttribute="studForm" action="${saveStudUrl}" name="studForm">
        <form:hidden path="id" />

        <spring:bind path="studFamily">
            <div>
                <label>Фамилия студента</label>
                <form:input path="studFamily" id="studFamily" />
                <form:errors path="studFamily" />
            </div>
        </spring:bind>

        <spring:bind path="studName">
            <div>
                <label>Имя студента</label>
                <form:input path="studName" id="studName" />
                <form:errors path="studName" />
            </div>
        </spring:bind>

        <spring:bind path="studOtchestvo">
            <div>
                <label>Отчество студента</label>
                <form:input path="studOtchestvo" id="studOtchestvo" />
                <form:errors path="studOtchestvo" />
            </div>
        </spring:bind>

        <spring:bind path="studBorn">
            <div>
                <label>Дата рождения студента</label>
                <form:input path="studBorn" id="studBorn" cssClass="tcal" />
                <form:errors path="studBorn" />
            </div>
        </spring:bind>

        <spring:bind path="studPhone">
            <div>
                <label>Телефон студента</label>
                <form:input path="studPhone" id="studPhone" />
                <form:errors path="studPhone" />
            </div>
        </spring:bind>

        <spring:bind path="livingCondition">
            <div>
                <label>Условия проживания</label>
                <form:select path="livingCondition" id="livingCondition">
                    <form:options items="${livingConditions}" />
                </form:select>
            </div>
        </spring:bind>

        <spring:bind path="familyStatus">
            <div>
                <label>Статус семьи</label>
                <form:select path="familyStatus" id="familyStatus">
                    <form:options items="${familyStatuses}" />
                </form:select>
            </div>
        </spring:bind>

        <spring:bind path="post">
            <div>
                <label>Должность в группе</label>
                <form:select path="post" id="post">
                    <form:options items="${posts}" />
                </form:select>
            </div>
        </spring:bind>


        <div class="parents" id="parents">
        <c:forEach items="${studForm.parents}" var="parent" varStatus="iter">

                <div class="parent">
                    <form:hidden path="parents[${iter.index}].id" />

                    <spring:bind path="parents[${iter.index}].parentType">
                        <div>
                            <label>Тип родителя</label>
                            <form:select path="parents[${iter.index}].parentType" id="parentType">
                                <form:options items="${parentTypes}" />
                            </form:select>
                        </div>
                    </spring:bind>

                    <spring:bind path="parents[${iter.index}].family">
                        <div>
                            <label>Фамилия родителя</label>
                            <form:input path="parents[${iter.index}].family" id="parentFamily" />
                            <form:errors path="parents[${iter.index}].family" />
                        </div>
                    </spring:bind>

                    <spring:bind path="parents[${iter.index}].name">
                        <div>
                            <label>Имя родителя</label>
                            <form:input path="parents[${iter.index}].name" id="parentName" />
                            <form:errors path="parents[${iter.index}].name" />
                        </div>
                    </spring:bind>

                    <spring:bind path="parents[${iter.index}].otchestvo">
                        <div>
                            <label>Отчество родителя</label>
                            <form:input path="parents[${iter.index}].otchestvo" id="parentOtchestvo" />
                            <form:errors path="parents[${iter.index}].otchestvo" />
                        </div>
                    </spring:bind>

                    <spring:bind path="parents[${iter.index}].phone">
                        <div>
                            <label>Телефон родителя</label>
                            <form:input path="parents[${iter.index}].phone" id="parentPhone" />
                            <form:errors path="parents[${iter.index}].phone" />
                        </div>
                    </spring:bind>

                    <spring:bind path="parents[${iter.index}].work">
                        <div>
                            <label>Должность и место работы родителя</label>
                            <form:input path="parents[${iter.index}].work" id="parentWork" />
                            <form:errors path="parents[${iter.index}].work" />
                        </div>
                    </spring:bind>

                    <spring:bind path="parents[${iter.index}].educationType">
                        <div>
                            <label>Образование родителя</label>
                            <form:select path="parents[${iter.index}].educationType" id="educationType">
                                <form:options items="${educationType}" />
                            </form:select>
                        </div>
                    </spring:bind>

                    <form:button id = "del" type="button" onclick="delParent(${iter.index})">Удалить</form:button>
                </div>

        </c:forEach>
        </div>
        <form:button id="add" type="button" onclick="addParent()">Добавить родителя</form:button>
        <div>
            <c:forEach items="${studForm.orders}" var="order" varStatus="iter">
                <form:hidden path="orders[${iter.index}].id" />

                <spring:bind path="orders[${iter.index}].orderType">
                    <div>
                        <label>Приказ о </label>
                        <form:select path="orders[${iter.index}].orderType" id="orderType">
                            <form:options items="${orderTypes}" />
                        </form:select>
                    </div>
                </spring:bind>

                <spring:bind path="orders[${iter.index}].number">
                    <div>
                        <label>№</label>
                        <form:input path="orders[${iter.index}].number" id="number" />
                        <form:errors path="orders[${iter.index}].number" />
                    </div>
                </spring:bind>

                <spring:bind path="orders[${iter.index}].date">
                    <div>
                        <label>Дата приказа: </label>
                        <form:input path="orders[${iter.index}].date" id="date" class="tcal" />
                        <form:errors path="orders[${iter.index}].date" />
                    </div>
                </spring:bind>

                <form:button id="delord" type="button" onclick="delOrder(${iter.index})">Удалить</form:button>
            </c:forEach>
        </div>
        <form:button id="addord" type="button" onclick="addOrder()">Добавить приказ</form:button>
        <c:choose>
            <c:when test="${studForm.id == 0}">
                <form:button id="sub" type="button" onclick="addNewStud()">Сохранить</form:button>
            </c:when>
            <c:otherwise>
                <form:button id="sub" type="button" onclick="updateStud()">Сохранить</form:button>
            </c:otherwise>
        </c:choose>

    </form:form>
</body>
</html>
