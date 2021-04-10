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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.setAttribute("url", "Controller?command=go_to_show_product_page&productID" + request.getParameter("productID"));

        Integer productID = Integer.parseInt(request.getParameter("productID"));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ProductService productService = serviceProvider.getProductService();

        Product product = null;
        try {
            product = productService.takeByProductID(productID);
            request.setAttribute("product", product);
        } catch (ServiceException e) {
            //log
        }

        logger.info("Go to show product page");
        logger.info(product);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/showProduct.jsp");
        requestDispatcher.forward(request, response);
    }
}
