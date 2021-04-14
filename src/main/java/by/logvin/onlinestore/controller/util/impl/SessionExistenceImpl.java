package by.logvin.onlinestore.controller.util.impl;

import by.logvin.onlinestore.controller.message.GoToPage;
import by.logvin.onlinestore.controller.message.Message;
import by.logvin.onlinestore.controller.util.SessionExistence;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SessionExistenceImpl implements SessionExistence {
    @Override
    public boolean isSessionExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
            session.setAttribute(Message.MESSAGE, Message.SESSION_HAS_TIMED_OUT);
            response.sendRedirect(GoToPage.REDIRECT_AUTHORIZATION_PAGE);
            return false;
        }
        return true;
    }
}
