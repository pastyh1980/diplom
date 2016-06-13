<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/tcal.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/main.css" />
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
    <title>Редактирование</title>
</head>
<body>
<div class="wrap">
    <jsp:include page="header.jsp" />
    <spring:url value="/stud/addstud" var="saveStudUrl"/>

    <form:form method="post" modelAttribute="studForm" action="${saveStudUrl}" name="studForm">
        <form:hidden path="id" />

        <div class="stud-edit">
            <p class="title-text small">Сведения о студенте</p>
            <table>
                <tr>
                    <spring:bind path="studFamily">
                        <td><label>Фамилия студента</label></td>
                        <td>
                            <form:input path="studFamily" id="studFamily" cssClass="fam" cssErrorClass="err"/>
                            <form:errors path="studFamily" />
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="studName">
                        <td><label>Имя студента</label></td>
                        <td>
                            <form:input path="studName" id="studName" cssClass="nam" cssErrorClass="err" />
                            <form:errors path="studName" />
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="studOtchestvo">
                        <td><label>Отчество студента</label></td>
                        <td>
                            <form:input path="studOtchestvo" id="studOtchestvo" cssClass="otch" cssErrorClass="err" />
                            <form:errors path="studOtchestvo" />
                            </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="studBorn">
                        <td><label>Дата рождения студента</label></td>
                        <td>
                            <form:input path="studBorn" id="studBorn" cssClass="tcal" cssErrorClass="err" />
                            <form:errors path="studBorn" />
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="studPhone">
                        <td><label>Телефон студента</label></td>
                        <td>
                            <form:input path="studPhone" id="studPhone" cssClass="phone" cssErrorClass="err" />
                            <form:errors path="studPhone" />
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="address">
                        <td><label>Адрес</label></td>
                        <td>
                            <form:textarea cols="50" rows="5" path="address" id="address" cssClass="addr" cssErrorClass="err" />
                            <form:errors path="address" />
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="livingCondition">
                        <td><label>Условия проживания</label></td>
                        <td>
                            <form:select path="livingCondition" id="livingCondition">
                                <form:options items="${livingConditions}" />
                            </form:select>
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="familyStatus">
                        <td><label>Статус семьи</label></td>
                        <td>
                            <form:select path="familyStatus" id="familyStatus">
                                <form:option value="" />
                                <form:options items="${familyStatuses}" />
                            </form:select>
                        </td>
                    </spring:bind>
                </tr>

                <tr>
                    <spring:bind path="post">
                        <td><label>Должность в группе</label></td>
                        <td>
                            <form:select path="post" id="post">
                                <form:option value="" />
                                <form:options items="${posts}" />
                            </form:select>
                        </td>
                    </spring:bind>
                </tr>
            </table>
        </div>

        <div class="parent-edit">
            <p class="title-text small">Сведенния о родителях</p>
            <c:forEach items="${studForm.parents}" var="parent" varStatus="iter">
                <form:hidden path="parents[${iter.index}].id" />

                <table>
                    <tr>
                        <spring:bind path="parents[${iter.index}].parentType">
                            <td><label>Тип родителя</label></td>
                            <td>
                                <form:select path="parents[${iter.index}].parentType" id="parentType">
                                    <form:options items="${parentTypes}" />
                                </form:select>
                            </td>
                        </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="parents[${iter.index}].family">
                            <td><label>Фамилия родителя</label></td>
                            <td>
                                <form:input path="parents[${iter.index}].family" id="parentFamily" cssClass="fam" cssErrorClass="err"/>
                                <form:errors path="parents[${iter.index}].family" />
                            </td>
                        </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="parents[${iter.index}].name">
                            <td><label>Имя родителя</label></td>
                            <td>
                                <form:input path="parents[${iter.index}].name" id="parentName" cssClass="nam" cssErrorClass="err" />
                                <form:errors path="parents[${iter.index}].name" />
                            </td>
                        </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="parents[${iter.index}].otchestvo">
                            <td><label>Отчество родителя</label></td>
                            <td>
                                <form:input path="parents[${iter.index}].otchestvo" id="parentOtchestvo" cssClass="otch" cssErrorClass="err" />
                                <form:errors path="parents[${iter.index}].otchestvo" />
                            </td>
                        </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="parents[${iter.index}].phone">
                            <td><label>Телефон родителя</label></td>
                            <td>
                                <form:input path="parents[${iter.index}].phone" id="parentPhone" cssClass="otch" cssErrorClass="err" />
                                <form:errors path="parents[${iter.index}].phone" />
                            </td>
                        </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="parents[${iter.index}].work">
                            <td><label>Должность и место работы родителя</label></td>
                            <td>
                                <form:textarea cols="50" rows="5" path="parents[${iter.index}].work" id="parentWork" cssClass="work" cssErrorClass="err" />
                                <form:errors path="parents[${iter.index}].work" />
                            </td>
                        </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="parents[${iter.index}].educationType">
                            <td><label>Образование родителя</label></td>
                            <td>
                                <form:select path="parents[${iter.index}].educationType" id="educationType">
                                    <form:options items="${educationType}" />
                                </form:select>
                            </td>
                        </spring:bind>
                    </tr>

                    <c:if test="${parent.id == 0}">
                        <tr>
                            <td></td>
                            <td><form:button id = "del" type="button" class="btn" onclick="delParent(${iter.index})">Удалить</form:button></td>
                        </tr>
                    </c:if>
                </table>
            </c:forEach>
        </div>
        <form:button id="add" type="button" class="btn mrg" onclick="addParent()">Добавить родителя</form:button>
        <div class="orders-edit">
            <p class="title-text small">Сведения о приказах</p>
            <c:forEach items="${studForm.orders}" var="order" varStatus="iter">
                <form:hidden path="orders[${iter.index}].id" />

                <table>
                    <tr>
                        <spring:bind path="orders[${iter.index}].orderType">
                            <td><label>Приказ о </label></td>
                            <td>
                                <form:select path="orders[${iter.index}].orderType" id="orderType">
                                    <form:options items="${orderTypes}" />
                                </form:select>
                            </td>
                        </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="orders[${iter.index}].number">
                            <td><label>Номер</label></td>
                            <td>
                                <form:input path="orders[${iter.index}].number" id="number" />
                                <form:errors path="orders[${iter.index}].number" />
                            </td>
                        </spring:bind>
                    </tr>

                    <tr>
                        <spring:bind path="orders[${iter.index}].date">
                            <td><label>Дата приказа: </label></td>
                            <td>
                                <form:input path="orders[${iter.index}].date" id="date" class="tcal" />
                                <form:errors path="orders[${iter.index}].date" />
                            </td>
                        </spring:bind>
                    </tr>

                    <c:if test="${order.id == 0}">
                        <tr>
                            <td></td>
                            <td><form:button id="delord" type="button" class="btn" onclick="delOrder(${iter.index})">Удалить</form:button></td>
                        </tr>
                    </c:if>
                </table>
            </c:forEach>
        </div>
        <form:button id="addord" type="button" class="btn mrg" onclick="addOrder()">Добавить приказ</form:button>
        <c:choose>
            <c:when test="${studForm.id == 0}">
                <form:button id="sub" type="button" class="btn mrg" onclick="addNewStud()">Сохранить</form:button>
            </c:when>
            <c:otherwise>
                <form:button id="sub" type="button" class="btn mrg" onclick="updateStud()">Сохранить</form:button>
            </c:otherwise>
        </c:choose>

    </form:form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
