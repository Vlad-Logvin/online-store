<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 04.04.2021
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.footer.privacy_policy" var="privacy_policy"/>
<fmt:message bundle="${loc}" key="local.footer.support" var="support"/>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>footer</title>
</head>
<body>
<footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">© 2021 bylogvin</p>
    <ul class="list-inline">
        <li class="list-inline-item"><a href="#">${privacy_policy}</a></li>
        <li class="list-inline-item"><a href="#">${support}</a></li>
    </ul>
</footer>
</body>
</html>
