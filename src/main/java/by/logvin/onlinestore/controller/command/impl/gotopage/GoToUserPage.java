package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.ExistenceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * The GoToUserPage class is responsible for going to user page
 *
 * @author bylogvin
 * @see by.logvin.onlinestore.controller.command.Command
 */
public class GoToUserPage implements Command {

    private final static Logger logger = Logger.getLogger(GoToUserPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!ExistenceProvider.getInstance().getUserExistence().isUserExist(request, response)){
            return;
        }
        HttpSession session = request.getSession(false);

        String sessionMessage = (String) session.getAttribute(Message.MESSAGE);
        if (sessionMessage != null) {
            request.setAttribute(Message.MESSAGE, sessionMessage);
            session.removeAttribute(Message.MESSAGE);
        }

        request.getSession(false).setAttribute(Message.ATTRIBUTE_URL, GoToPage.REDIRECT_USER_PAGE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_USER_PAGE);
        logger.info("Forward to user page");
        requestDispatcher.forward(request, response);

    }
}
