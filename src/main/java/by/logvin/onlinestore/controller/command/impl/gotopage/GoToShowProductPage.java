package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.service.ProductService;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GoToShowProductPage implements Command {
    private final static Logger logger = Logger.getLogger(GoToShowProductPage.class);

    // FIXME: 12.04.2021
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = 0;
        try {
            productID = Integer.parseInt(request.getParameter("productID"));
        } catch (NumberFormatException e){
            //log
            response.sendRedirect("Controller?command=go_to_main_page&message=error_product_id");
        }

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ProductService productService = serviceProvider.getProductService();

        Product product = null;
        try {
            product = productService.takeByProductID(productID);
            request.setAttribute("product", product);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/showProduct.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            //log
            response.sendRedirect("Controller?command=go_to_main_page&message=server_error");
        }

    }
}
