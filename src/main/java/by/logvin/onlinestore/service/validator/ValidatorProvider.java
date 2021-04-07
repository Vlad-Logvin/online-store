package by.logvin.onlinestore.service.validator;

import by.logvin.onlinestore.service.validator.impl.AuthorizationValidatorImpl;

public class ValidatorProvider {
    private final static ValidatorProvider instance = new ValidatorProvider();

    private final AuthorizationValidator authorizationValidator = new AuthorizationValidatorImpl();

    private ValidatorProvider(){

    }

    public static ValidatorProvider getInstance() {
        return instance;
    }

    public AuthorizationValidator getAuthorizationValidator() {
        return authorizationValidator;
    }
}
