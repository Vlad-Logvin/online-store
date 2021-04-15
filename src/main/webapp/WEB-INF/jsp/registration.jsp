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
<fmt:message bundle="${loc}" key="local.registration.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.registration.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.registration.sign_up" var="sign_up"/>
<fmt:message bundle="${loc}" key="local.registration.date" var="date"/>
<fmt:message bundle="${loc}" key="local.registration.not_necessary" var="not_necessary"/>
<fmt:message bundle="${loc}" key="local.registration.password" var="password"/>


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
        <h1 class="h2">${registration}</h1>
        <div class="row g-3">

            <div>
                <form class="needs-validation" novalidate="">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="firstName" class="form-label">${first_name}</label>
                            <input type="text" class="form-control" id="firstName" placeholder="" name="firstName"
                                   value="" required>
                        </div>

                        <div class="col-sm-6">
                            <label for="lastName" class="form-label">${last_name}</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" name="lastName"
                                   value="" required>
                        </div>


                        <div class="col-12">
                            <label for="email" class="form-label">${email}</label>
                            <input type="email" class="form-control" id="email" name="email" value=""
                                   placeholder="you@example.com" required>
                        </div>

                        <div class="col-12">
                            <label for="password" class="form-label">${password}</label>
                            <input type="password" class="form-control" id="password" name="password" value=""
                                   placeholder="" required>
                        </div>


                        <div class="col-12">
                            <label for="zip" class="form-label">${date}
                                <span class="text-muted">${not_necessary}</span></label>
                            <input type="date" class="form-control" id="zip" name="dateOfBirth" value="" placeholder="">
                        </div>
                    </div>

                    <hr class="my-4">

                    <button class="btn btn-primary btn-lg btn-block" type="submit" name="command"
                            value="sign_up">${sign_up}</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="temp/footer.jsp"/>
    <script async="" src="https://mc.yandex.ru/metrika/tag.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.bundle.min.js"></script>
    <script src="/css/examples/checkout-form-validation.js"></script>
    <!-- Global site tag (gtag.js) - Google Analytics -->
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
