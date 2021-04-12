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
                <h1 class="font-weight-light">Пользовательская страница</h1>
                <p class="lead text-muted">${user.firstName} ${user.lastName}</p>
                <p class="lead text-muted">Email: ${user.email}</p>
                <p class="lead text-muted">Дата рождения:
                    <c:if test="${user.dateOfBirth==null}">
                        Не указано
                    </c:if>
                    <c:if test="${user.dateOfBirth!=null}">
                        ${user.dateOfBirth}
                    </c:if>
                </p>
                <p class="lead text-muted">Role: ${user.userDetails.role}</p>
            </div>
        </div>
    </section>

    <c:set var="cards" value="${user.userDetails.cards}"/>

    <c:if test="${cards==null}">
        <div class="text-center container text-muted margin-top-3em">
            <h3>
                У вас не добавлены карты!
            </h3>
        </div>
    </c:if>

    <c:if test="${cards!=null}">
        <c:forEach var="card" items="${cards}">
            <div class="col-lg-4 container bg-light margin-top-3em">
                <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                    <div class="col p-4 d-flex flex-column position-static">
                        <h3 class="mb-0">Банковская карточка</h3>
                        <p class="card-text mb-auto">Cardholder: ${card.cardholder}</p>
                        <p class="card-text mb-auto">Номер карты: ${card.number}</p>
                        <p class="card-text mb-auto">Валидационный
                            период: ${card.validityPeriod}</p>

                        <div class="btn-group">
                            <form action="Controller" method="get">
                                <input type="hidden" name="command" value="go_to_edit_card_form_page">
                                <input type="hidden" name="cardID" value="${card.id}">
                                <button class="btn btn-sm btn-outline-secondary" type="submit">Редактировать</button>
                            </form>
                            <form action="Controller" method="post">
                                <input type="hidden" name="command" value="delete_card">
                                <input type="hidden" name="cardID" value="${card.id}">
                                <button class="btn btn-sm btn-outline-secondary" type="submit">Удалить карту</button>
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
