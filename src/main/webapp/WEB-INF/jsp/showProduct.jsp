<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 03.04.2021
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Show product</title>
    <link rel="stylesheet" href="css/showProduct.css" type="text/css">
</head>
<body>
<c:import url="temp/header.jsp"/>
<c:set var="product" value="${requestScope.product}"/>
<section class="container margin-set bg-light text-center">
        <h1 class="display-4 font-weight-normal">${product.name}</h1>
        <p class="lead font-weight-normal">${product.description}</p>
    <div class="product-device shadow-sm d-none d-md-block"></div>
    <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
</section>
<section class="container text-center py-8">
<div class="product-card-container text-center">

    <div class="product-card-container-left">
        <h1 class="product-card-h1">${product.name}</h1>
        <img alt="${product.name}" title="${product.name}" src="${product.photoURL}">
    </div>
    <div class="product-card-container-right">
        <div class="product-card-container-right-section simple">
            <div class="price-block  js-price-block">
                <div class="price-block-left">
                    <span class="_price">${product.price}$</span>
                </div>
            </div>
        </div>
        <c:if test="${sessionScope.basket.}"
        <a class="btn btn-outline-secondary enter" href="#">Положить в корзину</a>
        <a class="btn btn-outline-secondary enter" href="#">Добавить в избранные</a>
        <c:if test="${sessionScope.user.role=='admin'}">
            <a class="btn btn-outline-secondary enter" href="#">Редактировать</a>
            <a class="btn btn-outline-secondary enter" href="#">Дозаказать</a>
            <a class="btn btn-outline-secondary enter" href="#">Удалить</a>
        </c:if>
    </div>
</div>
</section>

<c:import url="temp/footer.jsp"/>
</body>
</html>
