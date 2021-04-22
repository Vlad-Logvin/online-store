package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GoToAddCardFormPage implements Command {

    private static final Logger logger = Logger.getLogger(GoToAddCardFormPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)) {
            return;
        }

        request.setAttribute(Message.ATTRIBUTE_ACTION, Message.ATTRIBUTE_ADD_CARD);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_CARD_FORM_PAGE);
        logger.info("Forward to card form page");
        requestDispatcher.forward(request, response);

    }
}