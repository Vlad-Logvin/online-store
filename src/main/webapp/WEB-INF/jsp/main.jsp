
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 14.03.2021
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;
    charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Online store</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
</head>
<body>

<c:import url="temp/header.jsp"/>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="font-weight-light">bylogvin</h1>
                <p class="lead text-muted">1ые на рынке по всему миру!!! Самый удобный интерфейс и вообще всё удобное!
                    Бери у нас!</p>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

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
                                    <div class="btn-group">
                                        <form action="Controller" method="get">
                                            <input type="hidden" name="command" value="go_to_show_product_page"/>
                                            <input type="hidden" name="productID" value="${product.id}"/>
                                            <input class="btn btn-sm btn-outline-secondary" type="submit"
                                                   value="Просмотреть"/>
                                        </form>
                                        <form action="Controller" method="post">
                                            <input type="hidden" name="command" value="add_to_basket"/>
                                            <input type="hidden" name="productID" value="${product.id}"/>
                                            <input type="submit" class="btn btn-sm btn-outline-secondary"
                                                   value="В корзину"/>
                                        </form>
                                        <form action="Controller" method="post">
                                            <input type="hidden" name="command" value="add_to_favourite"/>
                                            <input type="hidden" name="productID" value="${product.id}"/>
                                            <input type="submit" class="btn btn-sm btn-outline-secondary"
                                                   value="В избранное"/>
                                        </form>
                                        <c:if test="${sessionScope.user.userDetails.role=='admin'}">
                                            <form action="Controller" method="get">
                                                <input type="hidden" name="command"
                                                       value="go_to_edit_product_form_page"/>
                                                <input type="hidden" name="productID" value="${product.id}"/>
                                                <input type="submit" class="btn btn-sm btn-outline-secondary"
                                                       value="Редактировать"/>
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
