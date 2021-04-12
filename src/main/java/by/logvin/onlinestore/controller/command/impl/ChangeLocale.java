package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangeLocale implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("locale", request.getParameter("locale"));

        String redirectURL = (String) session.getAttribute("url");
        if (redirectURL == null) {
            redirectURL = "Controller?command=go_to_main_page";
        }

        response.sendRedirect(redirectURL);
    }
}