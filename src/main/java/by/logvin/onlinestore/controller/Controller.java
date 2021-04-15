package by.logvin.onlinestore.controller;

import by.logvin.onlinestore.controller.command.Command;
import by.logvin.onlinestore.controller.command.CommandProvider;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;
import java.io.IOException;

public class Controller extends HttpServlet {
    private final static Logger logger = Logger.getLogger(Controller.class);
    private final static String COMMAND = "command";

    private static final long serialVersionUID = 1L;

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter(COMMAND);
        Command command = provider.takeCommand(name);
        command.execute(request, response);
    }
}
