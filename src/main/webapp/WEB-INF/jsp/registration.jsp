<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 03.04.2021
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.registration.email" var="email"/>


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body class="bg-light" background="img/main_back.png">

    <c:import url="temp/header.jsp"/>


<div class="container">
    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="img/bylogvin_logotype.png" alt="bylogvin logotype" width="72"
             height="72">
        <h1 class="h2">Регистрация</h1>
        <div class="row g-3">
            <c:if test="${param.get('message')!=null}">
                <font color="red">
                    <c:out value="Wrong registration! Check data!"/>
                </font>
            </c:if>

            <div>
                <form class="needs-validation" novalidate="">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label">Имя</label>
                            <input type="text" class="form-control" id="firstName" placeholder="" name="firstName" value="">
                        </div>

                        <div class="col-sm-6">
                            <label for="lastName" class="form-label">Фамилия</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" name="lastName" value="">
                        </div>


                        <div class="col-12">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="" placeholder="you@example.com">
                        </div>

                        <div class="col-12">
                            <label for="password" class="form-label">Пароль</label>
                            <input type="password" class="form-control" id="password" name="password" value="" placeholder="">
                        </div>


                        <div class="col-12">
                            <label for="zip" class="form-label">Дата рождения
                                <span class="text-muted">(необязательно)</span></label>
                            <input type="date" class="form-control" id="zip" name="dateOfBirth" value="" placeholder="">
                        </div>
                    </div>

                    <hr class="my-4">

                    <h4 class="mb-3">Карта <span class="text-muted">(необязательно)</span></h4>

                    <div class="row gy-3">
                        <div class="col-md-6">
                            <label for="cc-name" class="form-label">Имя на карте</label>
                            <input type="text" class="form-control" id="cc-name" name="cardholder" value="" placeholder="">
                            <small class="text-muted">Полное имя, как показано на карточке</small>
                        </div>

                        <div class="col-md-6">
                            <label for="cc-number" class="form-label">Номер кредитной карты</label>
                            <input type="text" class="form-control" id="cc-number" name="number" value="" placeholder="">
                        </div>

                        <div class="col-md-6">
                            <label for="cc-expiration" class="form-label">Срок действия</label>
                            <input type="text" class="form-control" id="cc-expiration" name="validityPeriod" value="" placeholder="">
                        </div>

                        <div class="col-md-6">
                            <label for="cc-cvv" class="form-label">CVV</label>
                            <input type="text" class="form-control" id="cc-cvv" name="authenticationCode" value="" placeholder="">
                        </div>
                    </div>

                    <hr class="my-4">

                    <button class="btn btn-primary btn-lg btn-block" type="submit" name="command" value="sign_up">Зарегистрироваться</button>
                </form>
            </div>
        </div>
</div>
    <c:import url="temp/footer.jsp"/>
<script async="" src="https://mc.yandex.ru/metrika/tag.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.bundle.min.js"></script>
<script src="/css/examples/checkout-form-validation.js"></script><!-- Global site tag (gtag.js) - Google Analytics -->
<script async="" src="https://www.googletagmanager.com/gtag/js?id=UA-179173139-1"
        style="display: none !important;"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'UA-179173139-1');
</script>
<!-- Yandex.Metrika counter -->
<script type="text/javascript"> (function (m, e, t, r, i, k, a) {
    m[i] = m[i] || function () {
        (m[i].a = m[i].a || []).push(arguments)
    };
    m[i].l = 1 * new Date();
    k = e.createElement(t), a = e.getElementsByTagName(t)[0], k.async = 1, k.src = r, a.parentNode.insertBefore(k, a)
})(window, document, "script", "https://mc.yandex.ru/metrika/tag.js", "ym");
ym(67718821, "init", {clickmap: true, trackLinks: true, accurateTrackBounce: true, webvisor: true}); </script>
<noscript>
    <div><img src="https://mc.yandex.ru/watch/67718821" style="position:absolute; left:-9999px;" alt=""/></div>
</noscript> <!-- /Yandex.Metrika counter -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.bundle.min.js"></script>

</body>
</html>