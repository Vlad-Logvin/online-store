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
<fmt:message bundle="${loc}" key="local.card.card_number" var="card_number"/>
<fmt:message bundle="${loc}" key="local.order" var="o_order"/>
<fmt:message bundle="${loc}" key="local.order.orders" var="orders"/>
<fmt:message bundle="${loc}" key="local.card.cardholder" var="cardholder"/>
<fmt:message bundle="${loc}" key="local.card.validity_period" var="validity_period"/>
<fmt:message bundle="${loc}" key="local.order.grand_total" var="grand_total"/>
<fmt:message bundle="${loc}" key="local.product.product_price" var="product_price"/>
<fmt:message bundle="${loc}" key="local.product.product_quantity" var="product_quantity"/>
<fmt:message bundle="${loc}" key="local.product.product_name" var="product_name"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <title>Orders log</title>
    <link rel="stylesheet" href="css/order.css" type="text/css">
</head>
<body>

<c:import url="temp/header.jsp"/>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="font-weight-light">${orders}</h1>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-1 g-3">

                <c:forEach var="order" items="${requestScope.orders}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <h5 class="mb-0 text-center"> ${o_order} ${order.id} (${order.dateOfPurchase})</h5>

                            <div class="card-body max-height-div">
                                <div class="mb-1 text-muted">${cardholder}: ${order.card.cardholder}</div>
                                <div class="mb-1 text-muted">${card_number}: ${order.card.number}</div>
                                <div class="mb-1 text-muted margin-bottom-3rem">${validity_period}: ${order.card.validityPeriod}</div>

                                <p class="card-text max-height-p margin-bottom-3rem mb-3">??????????
                                    ??????????????????: ${order.grandTotal}</p>

                                <p class="card-text">????????????????:</p>
                                <c:forEach var="product" items="${order.products}">
                                    <p class="card-text margin-bottom-2rem"><strong>${product_name}</strong>: ${product.name}.
                                        <strong>${product_price}</strong>: ${product.price}. <strong>${product_quantity}</strong>: ${product.quantity}</p>
                                </c:forEach>

                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>

</main>

<c:import url="temp/footer.jsp"/>
</body>
</html>
