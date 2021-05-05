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
<fmt:message bundle="${loc}" key="local.product.show" var="show"/>
<fmt:message bundle="${loc}" key="local.product.add_to_basket" var="add_to_basket"/>
<fmt:message bundle="${loc}" key="local.product.remove_from_basket" var="remove_from_basket"/>
<fmt:message bundle="${loc}" key="local.product.add_to_favourite" var="add_to_favourite"/>
<fmt:message bundle="${loc}" key="local.product.remove_from_favourite" var="remove_from_favourite"/>
<fmt:message bundle="${loc}" key="local.product.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.product.left" var="left"/>
<fmt:message bundle="${loc}" key="local.product.get_more" var="get_more"/>
<fmt:message bundle="${loc}" key="local.product.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.product.no_attributes" var="no_attributes"/>
<fmt:message bundle="${loc}" key="local.product.product_attributes" var="product_attributes"/>
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
            <div class="product-card-container-right-section simple full-width">
                <div class="price-block  js-price-block full-width">
                    <div class="price-block-left full-width">
                        <span class="_price full-width">${product.price}$</span>
                    </div>

                </div>
                <div>
                    <div class="price-block-left full-width">
                        <span class="_price text-muted full-width">${left}: ${product.quantity}</span>
                    </div>
                </div>
            </div>
            <form action="Controller" method="post" class="full-width">
                <input type="hidden" name="productID" value="${product.id}"/>
                <c:if test="${sessionScope.user.userDetails.basket.isProductContains(product)}">
                    <button class="btn btn-outline-secondary enter full-width" type="submit" name="command"
                            value="remove_from_basket">${remove_from_basket}
                    </button>
                </c:if>
                <c:if test="${!sessionScope.user.userDetails.basket.isProductContains(product)}">
                    <button class="btn btn-outline-secondary enter full-width" type="submit" name="command"
                            value="add_to_basket">${add_to_basket}
                    </button>
                </c:if>

                <c:if test="${sessionScope.user.userDetails.favourite.isProductContains(product)}">
                    <button class="btn btn-outline-secondary enter full-width" type="submit" name="command"
                            value="remove_from_favourite">${remove_from_favourite}
                    </button>
                </c:if>
                <c:if test="${!sessionScope.user.userDetails.favourite.isProductContains(product)}">
                    <button class="btn btn-outline-secondary enter full-width" type="submit" name="command"
                            value="add_to_favourite">${add_to_favourite}
                    </button>
                </c:if>
            </form>
            <c:if test="${sessionScope.user.userDetails.role=='admin'}">
                <form action="Controller" method="get">
                    <input type="hidden" name="productID" value="${product.id}"/>
                    <button class="btn btn-outline-secondary enter full-width" type="submit" name="command"
                            value="go_to_edit_product_form_page">${edit}
                    </button>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="productID" value="${product.id}"/>
                    <button class="btn btn-outline-secondary enter full-width" type="submit" name="command"
                            value="get_more">${get_more}
                    </button>
                    <input class="full-width" type="number" name="productQuantity" value="1" min="1">
                    <button class="btn btn-outline-secondary enter full-width" type="submit" name="command"
                            value="delete_product">${delete}
                    </button>
                </form>
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
            <h2 class="blog-post-title">${product_attributes}</h2>
        </div>
        <c:if test="${product.attributes==null}">
            <p>
                ${no_attributes}!
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
