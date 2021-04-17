package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.controller.command.Command;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Logout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute(Message.ATTRIBUTE_USER);

        session.setAttribute(Message.MESSAGE, Message.LOGOUT);
        response.sendRedirect(GoToPage.REDIRECT_MAIN_PAGE);
    }
}
