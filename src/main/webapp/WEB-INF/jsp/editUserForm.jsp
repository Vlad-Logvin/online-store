<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 04.04.2021
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h1 class="h2">Пользовательская страница</h1>
        <div class="row g-3">
        <c:set var="user" value="${sessionScope.user}"/>
            <div>
                <form class="needs-validation" novalidate="">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label">Имя</label>
                            <input type="text" class="form-control" id="firstName" placeholder="" name="firstName"
                                   value="${user.firstName}" required>
                        </div>

                        <div class="col-sm-6">
                            <label for="lastName" class="form-label">Фамилия</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" name="lastName"
                                   value="${user.lastName}" required>
                        </div>


                        <div class="col-12">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="${user.email}"
                                   placeholder="you@example.com" required>
                        </div>

                        <div class="col-12">
                            <label for="password" class="form-label">Пароль</label>
                            <input type="password" class="form-control" id="password" name="password" value="${user.password}"
                                   placeholder="" required>
                        </div>


                        <div class="col-12">
                            <label for="zip" class="form-label">Дата рождения
                                <span class="text-muted">(не обязательно)</span></label>
                            <input type="date" class="form-control" id="zip" name="dateOfBirth" value="${user.dateOfBirth}" placeholder="">
                        </div>
                    </div>

                    <hr class="my-4">

                    <input type="hidden" name="userID" value="${user.id}">
                    <button class="btn btn-primary btn-lg btn-block" type="submit" name="command"
                            value="edit_profile">Изменить</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="temp/footer.jsp"/>
</body>
</html>
