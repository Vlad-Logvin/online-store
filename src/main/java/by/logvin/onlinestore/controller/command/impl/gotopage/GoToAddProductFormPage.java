package by.logvin.onlinestore.controller.command.impl.gotopage;

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
import org.apache.log4j.Logger;

import java.io.IOException;

public class GoToAddProductFormPage implements Command {
    private final static Logger logger = Logger.getLogger(GoToAddProductFormPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)){
            return;
        }

        try {
            request.setAttribute(Message.ATTRIBUTE_ACTION, Message.ATTRIBUTE_ADD_PRODUCT);
            request.setAttribute(Message.ATTRIBUTE_CATEGORIES, ServiceProvider.getInstance().getCategoryService().getCategories());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_PRODUCT_FORM_PAGE);
            logger.info("Forward to product form page");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error with getting categories", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}