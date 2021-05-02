package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.service.ServiceProvider;
import by.logvin.onlinestore.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The GoToCategoryPage class is responsible for going to category page
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class GoToCategoryPage implements Command {
    private static final Logger logger = Logger.getLogger(GoToCategoryPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(true);
        String sessionMessage = (String) session.getAttribute(Message.MESSAGE);
        if (sessionMessage != null) {
            request.setAttribute(Message.MESSAGE, sessionMessage);
            session.removeAttribute(Message.MESSAGE);
        }

        try {
            Category category = ServiceProvider.getInstance().getCategoryService().takeCategory(
                    Integer.parseInt(request.getParameter(Message.ATTRIBUTE_CATEGORY_ID))
            );
            request.setAttribute(Message.ATTRIBUTE_CATEGORY, category);
            request.setAttribute(Message.ATTRIBUTE_PRODUCTS, ServiceProvider.getInstance().getProductService().take(category));
            session.setAttribute(Message.ATTRIBUTE_URL, GoToPage.REDIRECT_CATEGORY_PAGE +
                    Message.PARAMETER_CATEGORY_ID +
                    request.getParameter(Message.ATTRIBUTE_CATEGORY_ID));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_CATEGORY_PAGE);
            logger.info("Forward to category page");
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error("Error while getting products or categories", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            logger.error("Error while parsing values from site, not correct input data", e);
            request.getSession(true).setAttribute(Message.MESSAGE, Message.WRONG_CATEGORY_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        }
    }
}
