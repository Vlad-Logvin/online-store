package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GoToEditProductFormPage implements Command {

    private final static Logger logger = Logger.getLogger(GoToEditProductFormPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)) {
            return;
        }

        HttpSession session = request.getSession(false);

        try {
            Integer productID = Integer.valueOf(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID));
            Product product = ServiceProvider.getInstance().getProductService().takeByProductID(productID);
            if (product == null) {
                logger.info("Redirect to last page due to non-existing product");
                session.setAttribute(Message.MESSAGE, Message.ERROR_NO_PRODUCT_WITH_ID);
                response.sendRedirect((String) session.getAttribute(Message.ATTRIBUTE_URL));
                return;
            }
            request.setAttribute(Message.ATTRIBUTE_ACTION, Message.ATTRIBUTE_EDIT_PRODUCT);
            request.setAttribute(Message.ATTRIBUTE_PRODUCT, product);
            request.setAttribute(Message.ATTRIBUTE_CATEGORIES, ServiceProvider.getInstance().getCategoryService().takeCategories());
            request.setAttribute(Message.ATTRIBUTE_ATTRIBUTES, ServiceProvider.getInstance().getAttributeService().takeParsedAttributes(productID));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_PRODUCT_FORM_PAGE);
            logger.info("Forward to product form page");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while getting categories, or parsing attributes, or getting products", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            logger.error("Error while parsing to int product id", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_PRODUCT_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }

}