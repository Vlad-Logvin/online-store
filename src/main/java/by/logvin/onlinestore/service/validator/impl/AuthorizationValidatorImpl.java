package by.logvin.onlinestore.service.validator.impl;

import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.service.validator.AuthorizationValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AuthorizationValidatorImpl implements AuthorizationValidator {

    private final static String EMAIL_REGEX = "([.[^@\\s]]+)@([.[^@\\s]]+)\\.([a-z]+)";
    private final static String PASSWORD_REGEX = "[0-9a-zA-Z_]{5,30}";
    private final static String FIRST_NAME_REGEX = "[a-zA-Zа-яА-Я-ёЁ]{1,100}";
    private final static String LAST_NAME_REGEX = "[a-zA-Zа-яА-Я-ёЁ]{1,100}";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd") {{
        setLenient(false);
    }};
    private static final String EMPTY = "";

    @Override
    public boolean validate(String email, String password) {
        if (email == null || password == null) {
            return false;
        }
        return email.matches(EMAIL_REGEX) && password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean validate(RegistrationInfo info) {
        String email = info.getEmail();
        String password = info.getPassword();
        String firstName = info.getFirstName();
        String lastName = info.getLastName();
        String dateOfBirth = info.getDateOfBirth();
        if (EMPTY.equals(dateOfBirth)) {
            info.setDateOfBirth(null);
        }
        if (email == null || password == null || firstName == null || lastName == null) {
            return false;
        }
        return email.matches(EMAIL_REGEX) &&
                password.matches(PASSWORD_REGEX) &&
                firstName.matches(FIRST_NAME_REGEX) &&
                lastName.matches(LAST_NAME_REGEX) &&
                (dateOfBirth == null || EMPTY.equals(dateOfBirth) || isDateOfBirthValid(dateOfBirth));
    }


    private boolean isDateOfBirthValid(String dateOfBirth) {
        try {
            DATE_FORMAT.parse(dateOfBirth);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
