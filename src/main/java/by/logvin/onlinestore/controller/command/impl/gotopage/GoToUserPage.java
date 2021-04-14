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

import java.io.IOException;

public class GoToUserPage implements Command {
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
        requestDispatcher.forward(request, response);

    }
}
