package by.logvin.onlinestore.controller.command.impl;

import by.logvin.onlinestore.controller.command.Command;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("user");

        String redirectURL = (String) session.getAttribute("url");

        if (redirectURL == null) {
            redirectURL = "Controller?command=go_to_main_page";
        }

        response.sendRedirect(redirectURL);
    }
}
