<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 05.04.2021
  Time: 3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Card form</title>
    <link rel="stylesheet" href="css/cardForm.css" type="text/css"/>
</head>
<body>
<c:import url="temp/header.jsp"/>
<form class="container margin-top-1em" action="Controller" method="post">
    <div class="row g-3">

        <div class="col-12">
            <c:if test="${requestScope.action=='addCard'}">
                <h4 class="mb-3">Добавление карточки</h4>
            </c:if>
            <c:if test="${requestScope.action=='editCard'}">
                <h4 class="mb-3">Изменение карточки</h4>
            </c:if>
            <div class="needs-validation">
                <div class="row g-3">
                    <div class="col-12">
                        <label for="cardCardholder" class="form-label">Cardholder</label>
                        <input type="text" class="form-control" id="cardCardholder" value="${requestScope.card.cardholder}"
                               name="cardCardholder" required>

                    </div>

                    <div class="col-12">
                        <label for="cardNumber" class="form-label">Номер карты</label>
                        <input type="number" class="form-control" id="cardNumber"
                               value="${requestScope.card.number}"
                               name="cardNumber"  minlength="16" maxlength="16" required>

                    </div>

                    <div class="col-sm-6">
                        <label for="cardValidityPeriod" class="form-label">Валидационный период</label>
                        <input type="number" class="form-control" id="cardValidityPeriod" name="cardValidityPeriod"
                               value="${requestScope.card.validityPeriod}" maxlength="4" minlength="4" required>
                    </div>

                    <div class="col-sm-6">
                        <label for="cardAuthenticationCode" class="form-label">CVV</label>
                        <input type="number" class="form-control" id="cardAuthenticationCode" name="cardAuthenticationCode"
                               value="${requestScope.card.authenticationCode}" maxlength="3" minlength="3" required>
                    </div>

                </div>


                <hr class="my-4">
                <c:if test="${requestScope.action=='addCard'}">
                    <input type="hidden" name="command" value="add_card">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Добавить карту</button>
                </c:if>
                <c:if test="${requestScope.action=='editCard'}">
                    <input type="hidden" name="command" value="edit_card">
                    <input type="hidden" name="cardID" value="${requestScope.card.id}">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Изменить карту</button>
                </c:if>
            </div>
        </div>
    </div>
</form>
<c:import url="temp/footer.jsp"/>
</body>
</html>
