<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 04.04.2021
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.user.user_page" var="user_page"/>
<fmt:message bundle="${loc}" key="local.registration.email" var="email"/>
<fmt:message bundle="${loc}" key="local.registration.date" var="date"/>
<fmt:message bundle="${loc}" key="local.registration.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.registration.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.registration.date" var="date"/>
<fmt:message bundle="${loc}" key="local.registration.not_necessary" var="not_necessary"/>
<fmt:message bundle="${loc}" key="local.registration.password" var="password"/>
<fmt:message bundle="${loc}" key="local.user.change_user_data" var="change_user_data"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User form</title>
    <link rel="stylesheet" href="css/editUserForm.css" type="text/css"/>
</head>
<body>

<c:import url="temp/header.jsp"/>


<div class="container">
    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="img/bylogvin_logotype.png" alt="bylogvin logotype" width="72"
             height="72">
        <h1 class="h2">${user_page}</h1>
        <div class="row g-3">
        <c:set var="user" value="${sessionScope.user}"/>
            <div>
                <form class="needs-validation" novalidate="">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label">${first_name}</label>
                            <input type="text" class="form-control" id="firstName" placeholder="" name="firstName"
                                   value="${user.firstName}" required>
                        </div>

                        <div class="col-sm-6">
                            <label for="lastName" class="form-label">${last_name}</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" name="lastName"
                                   value="${user.lastName}" required>
                        </div>


                        <div class="col-12">
                            <label for="email" class="form-label">${email}</label>
                            <input type="email" class="form-control" id="email" name="email" value="${user.email}"
                                   placeholder="you@example.com" required>
                        </div>

                        <div class="col-12">
                            <label for="password" class="form-label">${password}</label>
                            <input type="password" class="form-control" id="password" name="password" value="${user.password}"
                                   placeholder="" required>
                        </div>


                        <div class="col-12">
                            <label for="zip" class="form-label">${date}
                                <span class="text-muted">${not_necessary}</span></label>
                            <input type="date" class="form-control" id="zip" name="dateOfBirth" value="${user.dateOfBirth}" placeholder="">
                        </div>
                    </div>

                    <hr class="my-4">

                    <input type="hidden" name="userID" value="${user.id}">
                    <button class="btn btn-primary btn-lg btn-block" type="submit" name="command"
                            value="edit_profile">${change_user_data}</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="temp/footer.jsp"/>
</body>
</html>
