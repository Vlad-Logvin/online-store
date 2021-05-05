<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 04.04.2021
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.main.bylogvin_description" var="bylogvin_description"/>
<fmt:message bundle="${loc}" key="local.product.show" var="show"/>
<fmt:message bundle="${loc}" key="local.product.add_to_basket" var="add_to_basket"/>
<fmt:message bundle="${loc}" key="local.product.remove_from_basket" var="remove_from_basket"/>
<fmt:message bundle="${loc}" key="local.product.add_to_favourite" var="add_to_favourite"/>
<fmt:message bundle="${loc}" key="local.product.remove_from_favourite" var="remove_from_favourite"/>
<fmt:message bundle="${loc}" key="local.product.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.product.no_product" var="no_product"/>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>

    <title>Category</title>

    <link rel="stylesheet" href="css/category.css" type="text/css"/>
</head>
<body>
<c:import url="temp/header.jsp"/>
<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="font-weight-light">${requestScope.category.name}</h1>
                <p class="lead text-muted">${bylogvin_description}</p>
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

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <c:if test="${requestScope.products==null}">
                    <h3>${no_product}</h3>
                </c:if>
                <c:forEach var="product" items="${requestScope.products}">
                    <div class="col">
                        <div class="card shadow-sm">
                            <h5 class="font-weight-light text-center">${product.name}</h5>
                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                                 xmlns="http://www.w3.org/2000/svg" aria-label="Placeholder: ${product.name}"
                                 preserveAspectRatio="xMidYMid slice" role="img" focusable="false"><title>
                                    ${product.name}</title>
                                <image width="100%" height="100%" href="${product.photoURL}"></image>
                            </svg>

                            <div class="card-body max-height-div">
                                <p class="card-text max-height-p">${product.description}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="full-width">
                                        <form action="Controller" method="get">
                                            <input type="hidden" name="command" value="go_to_show_product_page"/>
                                            <input type="hidden" name="productID" value="${product.id}"/>
                                            <input class="full-width btn btn-sm btn-outline-secondary" type="submit"
                                                   value="${show}"/>
                                        </form>
                                        <c:if test="${sessionScope.user.userDetails.basket.isProductContains(product)}">
                                            <form action="Controller" method="post">
                                                <input type="hidden" name="command" value="remove_from_basket"/>
                                                <input type="hidden" name="productID" value="${product.id}"/>
                                                <input type="submit" class="full-width btn btn-sm btn-outline-secondary"
                                                       value="${remove_from_basket}"/>
                                            </form>
                                        </c:if>
                                        <c:if test="${!sessionScope.user.userDetails.basket.isProductContains(product)}">
                                            <form action="Controller" method="post">
                                                <input type="hidden" name="command" value="add_to_basket"/>
                                                <input type="hidden" name="productID" value="${product.id}"/>
                                                <input type="submit" class="full-width btn btn-sm btn-outline-secondary"
                                                       value="${add_to_basket}"/>
                                            </form>
                                        </c:if>
                                        <c:if test="${!sessionScope.user.userDetails.favourite.isProductContains(product)}">
                                            <form action="Controller" method="post">
                                                <input type="hidden" name="command" value="add_to_favourite"/>
                                                <input type="hidden" name="productID" value="${product.id}"/>
                                                <input type="submit" class="full-width btn btn-sm btn-outline-secondary"
                                                       value="${add_to_favourite}"/>
                                            </form>
                                        </c:if>
                                        <c:if test="${sessionScope.user.userDetails.favourite.isProductContains(product)}">
                                            <form action="Controller" method="post">
                                                <input type="hidden" name="command" value="remove_from_favourite"/>
                                                <input type="hidden" name="productID" value="${product.id}"/>
                                                <input type="submit" class="full-width btn btn-sm btn-outline-secondary"
                                                       value="${remove_from_favourite}"/>
                                            </form>
                                        </c:if>
                                        <c:if test="${sessionScope.user.userDetails.role=='admin'}">
                                            <form action="Controller" method="get">
                                                <input type="hidden" name="command"
                                                       value="go_to_edit_product_form_page"/>
                                                <input type="hidden" name="productID" value="${product.id}"/>
                                                <input type="submit" class="full-width btn btn-sm btn-outline-secondary"
                                                       value="${edit}"/>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
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
