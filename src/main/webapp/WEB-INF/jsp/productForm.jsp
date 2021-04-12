<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 04.04.2021
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <h2 class="featurette-Заголовок margin-bottom-3em"><span class="text-muted">Выберите фотографию: </span></h2>
            <label for="productPhotoURL" class="form-label">Ссылка на фото</label>
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
                <h4 class="mb-3">Добавление продукта</h4>
            </c:if>
            <c:if test="${requestScope.action=='editProduct'}">
                <h4 class="mb-3">Изменение продукта</h4>
            </c:if>
            <div class="needs-validation">
                <div class="row g-3">
                    <div class="col-12">
                        <label for="productName" class="form-label">Название продукта</label>
                        <input type="text" class="form-control" id="productName" value="${requestScope.product.name}"
                               name="productName">

                    </div>

                    <div class="col-12">
                        <label for="productDescription" class="form-label">Описание продукта</label>
                        <input type="text" class="form-control" id="productDescription"
                               value="${requestScope.product.description}"
                               name="productDescription">

                    </div>

                    <div class="col-sm-6">
                        <label for="productPrice" class="form-label">Цена</label>
                        <input type="text" class="form-control" id="productPrice" name="productPrice"
                               value="${requestScope.product.price}">
                    </div>

                    <div class="col-sm-6">
                        <label for="productQuantity" class="form-label">Количество</label>
                        <input type="number" class="form-control" id="productQuantity" name="productQuantity"
                               value="${requestScope.product.quantity}">
                    </div>


                    <div class="col-12">
                        <label for="productCategory" class="form-label">Категория</label>
                        <select class="form-select" id="productCategory" name="productCategory"
                                value="${requestScope.product.category.name}">

                            <c:forEach var="categories" items="${requestScope.categories}">
                                <option>${categories.name}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <div class="col-12">
                        <label for="productAttributes" class="form-label">Атрибуты</label>
                        <input type="text" class="form-control" id="productAttributes" name="productAttributes"
                               value="${requestScope.attributes}">

                    </div>
                </div>


                <hr class="my-4">
                <c:if test="${requestScope.action=='addProduct'}">
                    <input type="hidden" name="command" value="add_product">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Заказать продукт</button>
                </c:if>
                <c:if test="${requestScope.action=='editProduct'}">
                    <input type="hidden" name="command" value="edit_product">
                    <input type="hidden" name="productID" value="${requestScope.product.id}">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Изменить продукт</button>
                </c:if>
            </div>
        </div>
    </div>
</form>
<c:import url="temp/footer.jsp"/>
</body>
</html>
