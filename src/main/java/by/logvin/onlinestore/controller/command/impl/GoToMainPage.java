package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.service.ProductService;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToMainPage implements Command {
    private static final Logger logger = Logger.getLogger(GoToMainPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("url", "Controller?command=go_to_main_page");

        String forwardURL = "/WEB-INF/jsp/main.jsp";

        List<Product> products = null;
        try {
            products = ServiceProvider.getInstance().getProductService().take(9);
        } catch (ServiceException e) {
            logger.info("ServiceException", e);
            forwardURL = "error.jsp";
        }

        logger.info("product1" + products.get(0));

        request.setAttribute("products", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
        dispatcher.forward(request, response);
    }
}
