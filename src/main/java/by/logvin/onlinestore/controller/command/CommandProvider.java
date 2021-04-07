package by.logvin.onlinestore.controller.command;

import by.logvin.onlinestore.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        commands.put(CommandName.LOGOUT, new Logout());
        commands.put(CommandName.GO_TO_CATEGORY_PAGE, new GoToCategoryPage());
        commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
        commands.put(CommandName.GO_TO_SHOW_PRODUCT_PAGE, new GoToShowProductPage());
    }


    public Command takeCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }
}
