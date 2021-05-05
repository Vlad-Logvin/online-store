<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 11.04.2021
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.user.users" var="users"/>
<fmt:message bundle="${loc}" key="local.registration.email" var="email"/>
<fmt:message bundle="${loc}" key="local.registration.date" var="date"/>
<fmt:message bundle="${loc}" key="local.registration.date" var="date"/>
<fmt:message bundle="${loc}" key="local.user.give_access" var="give_access"/>
<fmt:message bundle="${loc}" key="local.user.remove_access" var="remove_access"/>
<fmt:message bundle="${loc}" key="local.user.give_admin" var="give_admin"/>
<fmt:message bundle="${loc}" key="local.user.remove_admin" var="remove_admin"/>
<fmt:message bundle="${loc}" key="local.user.user_role" var="user_role"/>
<fmt:message bundle="${loc}" key="local.user.access" var="access"/>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <title>Users</title>
    <link rel="stylesheet" href="css/showUser.css" type="text/css">
</head>
<body>

<c:import url="temp/header.jsp"/>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="font-weight-light">${users}</h1>
                <p class="lead text-muted">${bylogvin_description}
                </p>
                <p>
                    <c:if test="${requestScope.message!=null}">
                        <fmt:message bundle="${loc}" key="${requestScope.message}" var="message"/>
                    <c:if test="${requestScope.message.contains('local.error')}">
                <div class="red-text">
                        ${message}
                </div>
                </c:if>
                <c:if test="${requestScope.message.contains('local.correct')}">
                    <div class="green-text">
                            ${message}
                    </div>
                </c:if>
                </c:if>
                </p>

            </div>

        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 g-3">

                <c:forEach var="user" items="${requestScope.users}">
                    <c:if test="${user.id!=sessionScope.user.id && user.id!=2}">
                        <div class="col">
                            <div class="card shadow-sm">
                                <h5 class="font-weight-light text-center">${user.firstName} ${user.lastName}</h5>

                                <div class="card-body max-height-div">
                                    <p class="card-text max-height-p">${email}: ${user.email}</p>
                                    <p class="card-text max-height-p">${date}: ${user.dateOfBirth}</p>
                                    <p class="card-text max-height-p">${user_role}: ${user.userDetails.role}</p>
                                    <p class="card-text max-height-p">${access}: ${user.userDetails.access}</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group-lg">
                                            <c:if test="${user.userDetails.role=='admin'}">
                                                <form action="Controller" method="post">
                                                    <input type="hidden" name="command" value="manage_role"/>
                                                    <input type="hidden" name="role" value="2"/>
                                                    <input type="hidden" name="userID" value="${user.id}"/>
                                                    <input type="submit"
                                                           class="full-width btn btn-sm btn-outline-secondary"
                                                           value="${remove_admin}"/>
                                                </form>
                                            </c:if>
                                            <c:if test="${user.userDetails.role=='user'}">
                                                <form action="Controller" method="post">
                                                    <input type="hidden" name="command" value="manage_role"/>
                                                    <input type="hidden" name="role" value="1"/>
                                                    <input type="hidden" name="userID" value="${user.id}"/>
                                                    <input type="submit"
                                                           class="full-width btn btn-sm btn-outline-secondary"
                                                           value="${give_admin}"/>
                                                </form>
                                            </c:if>
                                            <c:if test="${user.userDetails.access}">
                                                <form action="Controller" method="post">
                                                    <input type="hidden" name="command" value="manage_access"/>
                                                    <input type="hidden" name="access" value="2"/>
                                                    <input type="hidden" name="userID" value="${user.id}"/>
                                                    <input type="submit"
                                                           class="full-width btn btn-sm btn-outline-secondary"
                                                           value="${remove_access}"/>
                                                </form>
                                            </c:if>
                                            <c:if test="${!user.userDetails.access}">
                                                <form action="Controller" method="post">
                                                    <input type="hidden" name="command" value="manage_access"/>
                                                    <input type="hidden" name="access" value="1"/>
                                                    <input type="hidden" name="userID" value="${user.id}"/>
                                                    <input type="submit"
                                                           class="full-width btn btn-sm btn-outline-secondary"
                                                           value="${give_access}"/>
                                                </form>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>

</main>

<c:import url="temp/footer.jsp"/>
</body>
</html>
