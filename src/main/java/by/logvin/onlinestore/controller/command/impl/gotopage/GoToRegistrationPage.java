package by.logvin.onlinestore.controller.command.impl.gotopage;

import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class GoToRegistrationPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(Message.ATTRIBUTE_URL, GoToPage.REDIRECT_REGISTRATION_PAGE);

        RequestDispatcher dispatcher = request.getRequestDispatcher(GoToPage.FORWARD_REGISTRATION_PAGE);
        dispatcher.forward(request, response);
    }
}
