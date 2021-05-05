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
<fmt:message bundle="${loc}" key="local.card.card_number" var="card_number"/>
<fmt:message bundle="${loc}" key="local.card.cardholder" var="cardholder"/>
<fmt:message bundle="${loc}" key="local.card.bank_card" var="bank_card"/>
<fmt:message bundle="${loc}" key="local.card.validity_period" var="validity_period"/>
<fmt:message bundle="${loc}" key="local.card.delete_card" var="delete_card"/>
<fmt:message bundle="${loc}" key="local.card.no_card" var="no_card"/>
<fmt:message bundle="${loc}" key="local.card.add_card" var="add_card"/>
<fmt:message bundle="${loc}" key="local.card.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.header.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.user.user_role" var="user_role"/>
<fmt:message bundle="${loc}" key="local.user.change_user_data" var="change_user_data"/>
<fmt:message bundle="${loc}" key="local.user.no_date" var="no_date"/>
<fmt:message bundle="${loc}" key="local.user.user_page" var="user_page"/>
<fmt:message bundle="${loc}" key="local.registration.email" var="email"/>
<fmt:message bundle="${loc}" key="local.registration.date" var="date"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>User form</title>
    <link rel="stylesheet" href="css/user.css" type="text/css"/>
</head>
<body>
<c:import url="temp/header.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<main>
    <section class="py-5 text-center container bg-light margin-top-3em">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="font-weight-light">${user_page}</h1>
                <p><c:if test="${requestScope.message!=null}">
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
                </c:if></p>
                <p class="lead text-muted">${user.firstName} ${user.lastName}</p>
                <p class="lead text-muted">${email}: ${user.email}</p>
                <p class="lead text-muted">${date}:
                    <c:if test="${user.dateOfBirth==null}">
                        ${no_date}
                    </c:if>
                    <c:if test="${user.dateOfBirth!=null}">
                        ${user.dateOfBirth}
                    </c:if>
                </p>
                <p class="lead text-muted">${user_role}: ${user.userDetails.role}</p>

                <div class="text-center justify-content-between align-items-center">
                    <div class="btn-group-lg">


                        <form action="Controller" method="get">
                            <input type="hidden" name="command" value="go_to_edit_user_form_page">
                            <input type="submit" class="full-width btn btn-sm btn-outline-secondary" value="${change_user_data}">
                        </form>


                        <form action="Controller" method="get">
                            <input type="hidden" name="command" value="go_to_add_card_form_page">
                            <input type="submit" class="full-width btn btn-sm btn-outline-secondary" value="${add_card}">
                        </form>

                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="logout">
                            <input type="submit" class="full-width btn btn-sm btn-outline-secondary" value="${logout}">
                        </form>


                    </div>
                </div>
                <p>
                    <c:if test="${requestScope.message!=null}">
                        <fmt:message bundle="${loc}" key="${requestScope.message}" var="message"/>
                        <c:out value="${message}"/>
                    </c:if>
                </p>
            </div>
        </div>
    </section>

    <c:set var="cards" value="${user.userDetails.cards}"/>

    <c:if test="${cards==null}">
        <div class="text-center container text-muted margin-top-3em">
            <h3>
                ${no_card}
            </h3>
        </div>
    </c:if>

    <c:if test="${cards!=null}">
        <c:forEach var="card" items="${cards}">
            <div class="col-lg-4 container bg-light margin-top-3em">
                <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
                        <h3 class="mb-0">${bank_card}</h3>
                        <p class="card-text mb-auto">${cardholder}: ${card.cardholder}</p>
                        <p class="card-text mb-auto">${card_number}: ${card.number}</p>
                        <p class="card-text mb-auto">${validity_period}: ${card.validityPeriod}</p>

                        <div class="btn-group">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="go_to_edit_card_form_page">
                                <input type="hidden" name="cardID" value="${card.id}">
                                <button class="btn btn-sm btn-outline-secondary" type="submit">${edit}</button>
                            </form>
                            <form action="Controller" method="post">
                                <input type="hidden" name="command" value="delete_card">
                                <input type="hidden" name="cardID" value="${card.id}">
                                <button class="btn btn-sm btn-outline-secondary" type="submit">${delete_card}</button>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>

</main>
<c:import url="temp/footer.jsp"/>
</body>
</html>
