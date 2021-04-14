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

import java.io.IOException;

public class GoToEditProductFormPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!ExistenceProvider.getInstance().getUserExistence().isAdmin(request, response)){
            return;
        }

        try {
            String productID = request.getParameter(Message.ATTRIBUTE_PRODUCT_ID);
            request.setAttribute(Message.ATTRIBUTE_ACTION, Message.ATTRIBUTE_EDIT_PRODUCT);
            request.setAttribute(Message.ATTRIBUTE_PRODUCT, ServiceProvider.getInstance().getProductService().takeByProductID(Integer.valueOf(productID)));
            request.setAttribute(Message.ATTRIBUTE_CATEGORIES, ServiceProvider.getInstance().getCategoryService().getCategories());
            request.setAttribute(Message.ATTRIBUTE_ATTRIBUTES, ServiceProvider.getInstance().getAttributeService().getParsedAttributes(Integer.valueOf(productID)));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_PRODUCT_FORM_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e){
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_PRODUCT_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }

}