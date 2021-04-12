package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.service.ProductService;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import by.logvin.onlinestore.service.util.impl.AttributeParserImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddProduct implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirectURL = "Controller?command=go_to_main_page&message=product_added";

        try {
            String name = request.getParameter("productName");
            double price = Double.parseDouble(request.getParameter("productPrice"));
            String description = request.getParameter("productDescription");
            int quantity = Integer.parseInt(request.getParameter("productQuantity"));
            String photoURL = request.getParameter("productPhotoURL");
            Category category = ServiceProvider.getInstance().getCategoryService().getCategory(request.getParameter("productCategory"));
            String attributes = request.getParameter("productAttributes");

            ProductService productService = ServiceProvider.getInstance().getProductService();
            boolean isAdd = productService.add(name, price, description, quantity, photoURL, category.getId(), new AttributeParserImpl().parse(attributes));
            if (!isAdd) {
                redirectURL = "Controller?command=go_to_main_page&message=error_product_adding";
            }
        } catch (NumberFormatException | ServiceException e) {
            //log
            redirectURL = "Controller?command=go_to_main_page&message=error_product_adding";
        }
        response.sendRedirect(redirectURL);
    }
}