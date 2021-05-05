<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 05.04.2021
  Time: 3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.card.card_number" var="card_number"/>
<fmt:message bundle="${loc}" key="local.card.cardholder" var="cardholder"/>
<fmt:message bundle="${loc}" key="local.card.card_adding" var="card_adding"/>
<fmt:message bundle="${loc}" key="local.card.add_card" var="add_card"/>
<fmt:message bundle="${loc}" key="local.card.bank_card" var="bank_card"/>
<fmt:message bundle="${loc}" key="local.card.validity_period" var="validity_period"/>
<fmt:message bundle="${loc}" key="local.card.card_editing" var="card_editing"/>
<fmt:message bundle="${loc}" key="local.card.edit_card" var="edit_card"/>
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
                <h4 class="mb-3">${card_adding}</h4>
            </c:if>
            <c:if test="${requestScope.action=='editCard'}">
                <h4 class="mb-3">${card_editing}</h4>
            </c:if>
            <div class="needs-validation">
                <div class="row g-3">
                    <div class="col-12">
                        <label for="cardCardholder" class="form-label">${cardholder}</label>
                        <input type="text" class="form-control" id="cardCardholder" value="${requestScope.card.cardholder}"
                               name="cardCardholder" required>

                    </div>

                    <div class="col-12">
                        <label for="cardNumber" class="form-label">${card_number}</label>
                        <input type="number" class="form-control" id="cardNumber"
                               value="${requestScope.card.number}"
                               name="cardNumber"  minlength="16" maxlength="16" required>

                    </div>

                    <div class="col-sm-6">
                        <label for="cardValidityPeriod" class="form-label">${validity_period}</label>
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
                    <button class="btn btn-primary btn-lg btn-block" type="submit">${add_card}</button>
                </c:if>
                <c:if test="${requestScope.action=='editCard'}">
                    <input type="hidden" name="command" value="edit_card">
                    <input type="hidden" name="cardID" value="${requestScope.card.id}">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">${edit_card}</button>
                </c:if>
            </div>
        </div>
    </div>
</form>
<c:import url="temp/footer.jsp"/>
</body>
</html>
