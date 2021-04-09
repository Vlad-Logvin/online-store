package by.logvin.onlinestore.service.validator;

import by.logvin.onlinestore.bean.RegistrationInfo;

public interface AuthorizationValidator {
    boolean validate(String email, String password);
    boolean validate(RegistrationInfo info);
}
