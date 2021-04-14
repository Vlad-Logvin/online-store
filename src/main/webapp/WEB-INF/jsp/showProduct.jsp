<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 03.04.2021
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
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
            <form action="Controller" method="post">
                <input type="hidden" name="productID" value="${product.id}"/>
                <c:if test="${sessionScope.user.userDetails.basket.isProductContains(product)}">
                    <button class="btn btn-outline-secondary enter" type="submit" name="command"
                            value="remove_from_basket">Удалить из корзины
                    </button>
                </c:if>
                <c:if test="${!sessionScope.user.userDetails.basket.isProductContains(product)}">
                    <button class="btn btn-outline-secondary enter" type="submit" name="command"
                            value="add_to_basket">Положить в корзину
                    </button>
                </c:if>

                <c:if test="${sessionScope.user.userDetails.favourite.isProductContains(product)}">
                    <button class="btn btn-outline-secondary enter" type="submit" name="command"
                            value="remove_from_favourite">Убрать из избранных
                    </button>
                </c:if>
                <c:if test="${!sessionScope.user.userDetails.favourite.isProductContains(product)}">
                    <button class="btn btn-outline-secondary enter" type="submit" name="command"
                            value="add_to_favourite">Добавить в избранные
                    </button>
                </c:if>
                <c:if test="${sessionScope.user.userDetails.role=='admin'}">
                    <button class="btn btn-outline-secondary enter" type="submit" name="command"
                            value="edit_product">Редактировать
                    </button>
                    <button class="btn btn-outline-secondary enter" type="submit" name="command"
                            value="get_more">Дозаказать
                    </button>
                    <button class="btn btn-outline-secondary enter" type="submit" name="command"
                            value="delete_product">Удалить
                    </button>
                </c:if>

            </form>
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

<section>
    <div class="container text-center bg-light">

        <div class="blog-post text-center">
            <h2 class="blog-post-title">Характеристики</h2>
        </div>
        <c:if test="${product.attributes==null}">
            <p>
                Характеристик нет!
            </p>
        </c:if>
        <c:if test="${product.attributes!=null}">
            <c:forEach var="attribute" items="${product.attributes}">
                <p>
                        ${attribute.name}: ${attribute.value}
                </p>
            </c:forEach>
        </c:if>
    </div>
</section>

<c:import url="temp/footer.jsp"/>
</body>
</html>
