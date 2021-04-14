package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;
import java.io.IOException;

public class GoToAuthorizationPage implements Command {

    private static final Logger logger = Logger.getLogger(GoToAuthorizationPage.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(Message.ATTRIBUTE_URL, GoToPage.REDIRECT_AUTHORIZATION_PAGE);

        String sessionMessage = (String) session.getAttribute(Message.MESSAGE);
        if (sessionMessage != null) {
            request.setAttribute(Message.MESSAGE, sessionMessage);
            session.removeAttribute(Message.MESSAGE);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GoToPage.FORWARD_AUTHORIZATION_PAGE);
        requestDispatcher.forward(request, response);
    }
}
