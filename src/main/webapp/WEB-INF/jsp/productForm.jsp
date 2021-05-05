<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 04.04.2021
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.product.product_adding" var="product_adding"/>
<fmt:message bundle="${loc}" key="local.product.product_attributes" var="product_attributes"/>
<fmt:message bundle="${loc}" key="local.product.product_category" var="product_category"/>
<fmt:message bundle="${loc}" key="local.product.product_description" var="product_description"/>
<fmt:message bundle="${loc}" key="local.product.product_edit" var="product_edit"/>
<fmt:message bundle="${loc}" key="local.product.product_editing" var="product_editing"/>
<fmt:message bundle="${loc}" key="local.product.product_name" var="product_name"/>
<fmt:message bundle="${loc}" key="local.product.product_ordering" var="product_ordering"/>
<fmt:message bundle="${loc}" key="local.product.product_photo_url" var="product_photo_url"/>
<fmt:message bundle="${loc}" key="local.product.product_price" var="product_price"/>
<fmt:message bundle="${loc}" key="local.product.product_quantity" var="product_quantity"/>
<fmt:message bundle="${loc}" key="local.product.choose_photo_url" var="choose_photo_url"/>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Product form</title>
    <link rel="stylesheet" href="css/productForm.css" type="text/css"/>
</head>
<body>
<c:import url="temp/header.jsp"/>
<form class="container margin-top-1em" action="Controller" method="post">
    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-Заголовок margin-bottom-3em"><span class="text-muted">${choose_photo_url}: </span></h2>
            <label for="productPhotoURL" class="form-label">${product_photo_url}</label>
            <input type="text" class="form-control margin-bottom-3em" id="productPhotoURL"
                   value="${requestScope.product.photoURL}"
                   name="productPhotoURL">
        </div>
        <div class="col-md-5">

            <c:if test="${requestScope.product.photoURL==null}">
                <img src="img/Banana_man1.png" width="300" height="300"/>
            </c:if>
            <c:if test="${requestScope.product.photoURL!=null}">
                <img src="${requestScope.product.photoURL}"  width="300" height="300"/>
            </c:if>

        </div>
    </div>

    <div class="row g-3">

        <div class="col-12">
            <c:if test="${requestScope.action=='addProduct'}">
                <h4 class="mb-3">${product_adding}</h4>
            </c:if>
            <c:if test="${requestScope.action=='editProduct'}">
                <h4 class="mb-3">${product_editing}</h4>
            </c:if>
            <div class="needs-validation">
                <div class="row g-3">
                    <div class="col-12">
                        <label for="productName" class="form-label">${product_name}</label>
                        <input type="text" class="form-control" id="productName" value="${requestScope.product.name}"
                               name="productName">

                    </div>

                    <div class="col-12">
                        <label for="productDescription" class="form-label">${product_description}</label>
                        <input type="text" class="form-control" id="productDescription"
                               value="${requestScope.product.description}"
                               name="productDescription">

                    </div>

                    <div class="col-sm-6">
                        <label for="productPrice" class="form-label">${product_price}</label>
                        <input type="text" class="form-control" id="productPrice" name="productPrice"
                               value="${requestScope.product.price}">
                    </div>

                    <div class="col-sm-6">
                        <label for="productQuantity" class="form-label">${product_quantity}</label>
                        <input type="number" class="form-control" id="productQuantity" name="productQuantity"
                               min="0" value="${requestScope.product.quantity}">
                    </div>


                    <div class="col-12">
                        <label for="productCategory" class="form-label">${product_category}</label>
                        <select class="form-select" id="productCategory" name="productCategory"
                                value="${requestScope.product.category.name}">

                            <c:forEach var="categories" items="${requestScope.categories}">
                                <option>${categories.name}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <div class="col-12">
                        <label for="productAttributes" class="form-label">${product_attributes}</label>
                        <input type="text" class="form-control" id="productAttributes" name="productAttributes"
                               value="${requestScope.attributes}">

                    </div>
                </div>


                <hr class="my-4">
                <c:if test="${requestScope.action=='addProduct'}">
                    <input type="hidden" name="command" value="add_product">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">${product_ordering}</button>
                </c:if>
                <c:if test="${requestScope.action=='editProduct'}">
                    <input type="hidden" name="command" value="edit_product">
                    <input type="hidden" name="productID" value="${requestScope.product.id}">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">${product_edit}</button>
                </c:if>
            </div>
        </div>
    </div>
</form>
<c:import url="temp/footer.jsp"/>
</body>
</html>
