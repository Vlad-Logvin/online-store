package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
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
        String sessionMessage = (String) session.getAttribute(Message.MESSAGE);
        if (sessionMessage != null) {
            request.setAttribute(Message.MESSAGE, sessionMessage);
            session.removeAttribute(Message.MESSAGE);
        }
        try {
            Product product = ServiceProvider.getInstance().getProductService().takeByProductID(Integer.parseInt(request.getParameter(Message.ATTRIBUTE_PRODUCT_ID)));
            request.setAttribute(Message.ATTRIBUTE_PRODUCT, product);
            session.setAttribute(Message.ATTRIBUTE_URL, GoToPage.REDIRECT_SHOW_PRODUCT_PAGE + Message.PARAMETER_PRODUCT_ID + product.getId());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_SHOW_PRODUCT_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            session.setAttribute(Message.MESSAGE, Message.SERVICE_EXCEPTION);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
        } catch (NumberFormatException e) {
            session.setAttribute(Message.MESSAGE, Message.WRONG_PRODUCT_INPUT);
            response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
            return;
        }

    }
}
