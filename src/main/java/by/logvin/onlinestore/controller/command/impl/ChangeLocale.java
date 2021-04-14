package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(Message.ATTRIBUTE_LOCALE, request.getParameter(Message.ATTRIBUTE_LOCALE));

        String redirectURL = (String) session.getAttribute(Message.ATTRIBUTE_URL);
        if (redirectURL == null) {
            redirectURL = GoToPage.REDIRECT_MAIN_PAGE;
        }

        response.sendRedirect(redirectURL);
    }
}