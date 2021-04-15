<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 04.04.2021
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.basket" var="basket"/>
<fmt:message bundle="${loc}" key="local.basket.no_products_in_basket" var="no_product_in_basket"/>
<fmt:message bundle="${loc}" key="local.product.show" var="show"/>
<fmt:message bundle="${loc}" key="local.product.add_to_favourite" var="add_to_favourite"/>
<fmt:message bundle="${loc}" key="local.product.remove_from_basket" var="remove_from_basket"/>
<fmt:message bundle="${loc}" key="local.product.remove_from_favourite" var="remove_from_favourite"/>
<fmt:message bundle="${loc}" key="local.product.edit" var="edit"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <title>Cart</title>
    <link rel="stylesheet" href="css/basket.css" type="text/css">
</head>
<body>
<c:import url="temp/header.jsp"/>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="font-weight-light">${basket}</h1>
                <p>
                    <c:if test="${requestScope.message!=null}">
                        <fmt:message bundle="${loc}" key="${requestScope.message}" var="message"/>
                        <c:out value="${message}"/>
                    </c:if>
                </p>
            </div>
        </div>
    </section>
    <form class="text-center container bg-light" action="Controller" method="get">
        <button class="btn btn-sm btn-outline-secondary full-width" type="submit" name="command"
                value="go_to_add_card_form_page">
            Добавить карту
        </button>
    </form>

    <form action="Controller" method="post" class="album py-5 bg-light">
        <c:if test="${sessionScope.user.userDetails.cards!=null}">
            <c:forEach var="card" items="${sessionScope.user.userDetails.cards}">
                <div class="col-lg-4 container bg-light margin-top-3em">
                    <input class="form-check-input" type="radio" name="cardID" value="${card.id}" checked required/>
                    <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <h3 class="mb-0">Банковская карточка</h3>
                            <p class="card-text mb-auto">Cardholder: ${card.cardholder}</p>
                            <p class="card-text mb-auto">Номер карты: ${card.number}</p>
                            <p class="card-text mb-auto">Валидационный
                                период: ${card.validityPeriod}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                <c:if test="${requestScope.products==null || requestScope.products.size()==0}">
                    ${no_product_in_basket}
                </c:if>

                <c:if test="${requestScope.products!=null && requestScope.products.size()!=0}">
                    <c:forEach var="product" items="${requestScope.products}">
                        <div class="col">
                            <div class="card shadow-sm">
                                <h5 class="font-weight-light text-center">${product.name}</h5>
                                <input type="hidden" name="productID" value="${product.id}"/>
                                <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                                     xmlns="http://www.w3.org/2000/svg" aria-label="Placeholder: ${product.name}"
                                     preserveAspectRatio="xMidYMid slice" role="img" focusable="false"><title>
                                        ${product.name}</title>
                                    <image width="100%" height="100%" href="${product.photoURL}"></image>
                                    <p class="text-center">Цена: ${product.price}$ </p>
                                    <p class="text-center">Количество: ${product.quantity} </p>
                                    <input type="number" max="${product.quantity}" min="0" class="full-width"
                                           name="productQuantity" value="0" required/>
                                </svg>

                                <div class="card-body max-height-div">
                                    <p class="card-text max-height-p">${product.description}</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="full-width">
                                            <button class="full-width btn btn-sm btn-outline-secondary" name="command"
                                                    value="remove_from_basket" type="submit">
                                                    ${remove_from_basket}
                                            </button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </c:if>
            </div>

            <c:if test="${requestScope.products!=null && requestScope.products.size()!=0}">
                <button class="text-center container bg-light margin-top-3em" type="submit" name="command" value="make_order">Сделать
                    заказ
                </button>
            </c:if>
        </div>
    </form>

</main>


<c:import url="temp/footer.jsp"/>
</body>
</html>
