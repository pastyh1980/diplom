<%--
  Created by IntelliJ IDEA.
  User: pasty
  Date: 07.05.2016
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stud details</title>
</head>
<body>
    <div>
        Фамилия студента: ${studForm.studFamily}
    </div>

    <div>
        Имя студента: ${studForm.studName}
    </div>

    <div>
        Отчество студента: ${studForm.studOtchestvo}
    </div>

    <div>
        Дата рождения студента: ${studForm.studBorn}
    </div>

    <div>
        Телефон студента: ${studForm.studPhone}
    </div>
</body>
</html>
