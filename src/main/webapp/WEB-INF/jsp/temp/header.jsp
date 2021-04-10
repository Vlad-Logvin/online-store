<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 04.04.2021
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.header.smartphones_and_gadgets" var="smartphones_and_gadgets"/>
<fmt:message bundle="${loc}" key="local.header.home" var="home"/>
<fmt:message bundle="${loc}" key="local.header.smartphones" var="smartphones"/>
<fmt:message bundle="${loc}" key="local.header.gadgets.headphones" var="headphones"/>
<fmt:message bundle="${loc}" key="local.header.gadgets.powerbanks" var="powerbanks"/>
<fmt:message bundle="${loc}" key="local.header.gadgets.smartWatch" var="smartWatch"/>
<fmt:message bundle="${loc}" key="local.header.other" var="other"/>
<fmt:message bundle="${loc}" key="local.header.TV-sets" var="TVSets"/>
<fmt:message bundle="${loc}" key="local.header.ledTV" var="ledTV"/>
<fmt:message bundle="${loc}" key="local.header.oledTV" var="oledTV"/>
<fmt:message bundle="${loc}" key="local.header.smartTV" var="smartTV"/>
<fmt:message bundle="${loc}" key="local.header.ultraHDTV" var="ultraTV"/>
<fmt:message bundle="${loc}" key="local.header.laptops_and_computers" var="laptops_and_computers"/>
<fmt:message bundle="${loc}" key="local.header.computers" var="computers"/>
<fmt:message bundle="${loc}" key="local.header.laptops" var="laptops"/>
<fmt:message bundle="${loc}" key="local.header.settings" var="settings"/>
<fmt:message bundle="${loc}" key="local.authorization.sign_in" var="signIn"/>
<fmt:message bundle="${loc}" key="local.authorization.sign_up" var="signUp"/>
<fmt:message bundle="${loc}" key="local.authorization.logout" var="logout"/>
<fmt:message bundle="${loc}" key="local.header.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.header.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.header.favourites" var="favourites"/>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <title>header</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css"
          integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous"/>
    <link rel="stylesheet" href="css/header.css" type="text/css"/>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-xl">
            <a class="navbar-brand" href="Controller?command=go_to_main_page">bylogvin</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto mb-2 mb-lg-0">

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="Controller?command=go_to_main_page">
                            <i class="skip-pixels fas fa-home"></i>${home}</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                           data-toggle="dropdown" aria-expanded="false">
                            <i class="skip-pixels fas fa-mobile-alt"></i>${smartphones_and_gadgets}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=smartphone">${smartphones}</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=headphone">${headphones}</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=powerbank">${powerbanks}</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=smartWatch">${smartWatch}</a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=smartphonesAndGadgetsOther">${other}</a>
                            </li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                           data-toggle="dropdown" aria-expanded="false">
                            <i class="skip-pixels fas fa-tv"></i>${TVSets}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=ledTV">${ledTV}</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=oledTV">${oledTV}</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=smartTV">${smartTV}</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=ultraTV">${ultraTV}</a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=TV-setsOther">${other}</a>
                            </li>
                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button"
                           data-toggle="dropdown" aria-expanded="false">
                            <i class="skip-pixels fas fa-laptop"></i>${laptops_and_computers}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=laptop">${laptops}</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=computer">${computers}</a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=go_to_category_product_page&category=laptopsAndComputersOther">${other}</a>
                            </li>
                        </ul>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="Controller?command=go_to_basket_page"><i
                                class="skip-pixels fas fa-shopping-cart"></i>${basket}</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="Controller?command=go_to_favourite_page"><i
                                class="skip-pixels far fa-heart"></i>${favourites}</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown4" role="button"
                           data-toggle="dropdown" aria-expanded="false">
                            <i class="skip-pixels fas fa-cogs"></i>${settings}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:set var="user" value="${sessionScope.user}"/>
                            <c:if test="${user==null}">
                                <li><a class="dropdown-item"
                                       href="Controller?command=go_to_authorization_page">${signIn}</a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="Controller?command=go_to_registration_page">${signUp}</a>
                                </li>
                            </c:if>
                            <c:if test="${user!=null}">
                                <li>
                                    <a class="dropdown-item" href="Controller?command=go_to_orders_page">
                                            ${orders}
                                    </a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="Controller?command=go_to_user_page"><c:out value="${user.firstName}"/>
                                    <c:out value="${user.lastName}"/></a>
                                </li>
                                <li><a class="dropdown-item"
                                       href="Controller?command=logout">${logout}</a>
                                </li>
                            </c:if>

                        </ul>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown5" role="button"
                           data-toggle="dropdown" aria-expanded="false">
                            <img class="language-img" src="img/lang2.jpg" height="20" width="20">
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item"
                                   href="Controller?command=change_locale&locale=ru">Русский</a>
                            </li>
                            <li><a class="dropdown-item"
                                   href="Controller?command=change_locale&locale=en">English</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
        integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
        crossorigin="anonymous"></script>
</body>
</html>
