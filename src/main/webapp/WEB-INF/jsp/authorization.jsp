<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 13.03.2021
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.authorization.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="local.authorization.sign_up" var="sign_up"/>
<fmt:message bundle="${loc}" key="local.authorization.successful_registration" var="successful_registration"/>
<fmt:message bundle="${loc}" key="local.authorization.wrong_email_or_password" var="wrong_email_or_password"/>
<fmt:message bundle="${loc}" key="local.authorization.email" var="email"/>
<fmt:message bundle="${loc}" key="local.authorization.password" var="password"/>
<fmt:message bundle="${loc}" key="local.authorization.please_log_in" var="please_log_in"/>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Authorization</title>
    <link rel="stylesheet" href="css/authorization.css" type="text/css">
</head>
<body class="text-center">


<c:import url="temp/header.jsp"/>


<form class="form-signin">
    <img class="mb-4" src="img/bylogvin_logotype.png" alt="logotype bylogvin" width="200" height="200">
    <h1 class="h3 mb-3 font-weight-normal">${please_log_in}</h1>

    <c:if test="${param.get('message')=='wrong'}">
        <div class="red-text">
            <c:out value="${wrong_email_or_password}"/>
        </div>
    </c:if>

    <c:if test="${param.get('message')=='correct'}">
        <div class="green-text">
            <c:out value="${successful_registration}"/>
        </div>
    </c:if>

    <label for="inputEmail" class="sr-only">${email}</label>
    <input type="text" name="email" value="" id="inputEmail" class="form-control" placeholder="${email}" autofocus="">
    <label for="inputPassword" class="sr-only">${password}</label>
    <input type="password" name="password" value="" id="inputPassword" class="form-control" placeholder="${password}">
    <button class="btn btn-lg btn-primary btn-block" name="command" value="sign_in" type="submit">${sign_in}</button>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><a class="reg"
                                                                      href="Controller?command=go_to_registration_page">${sign_up}</a>
    </button>

</form>

<c:import url="temp/footer.jsp"/>

</body>
</html>
