package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ChangeLocale implements Command {

    private static final Logger logger = Logger.getLogger(ChangeLocale.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(Message.ATTRIBUTE_LOCALE, request.getParameter(Message.ATTRIBUTE_LOCALE));

        String redirectURL = (String) session.getAttribute(Message.ATTRIBUTE_URL);
        if (redirectURL == null) {
            redirectURL = GoToPage.REDIRECT_MAIN_PAGE;
        }

        logger.info("Redirect to last page");
        response.sendRedirect(redirectURL);
    }
}