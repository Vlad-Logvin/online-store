package by.logvin.onlinestore.service.validator;

import by.logvin.onlinestore.bean.RegistrationInfo;

/**
 * The interface AuthorizationValidator provides method to validate authorization
 *
 * @author bylogvin
 */
public interface AuthorizationValidator {

    /**
     * The overload method validate provides signing in data validating
     *
     * @param email    {@link String} user email
     * @param password {@link String} user password
     * @return boolean true if the signing in user data is valid
     */
    boolean validate(String email, String password);

    /**
     * The overload method validate provides signing up data validation
     *
     * @param info {@link RegistrationInfo} user registration data
     * @return boolean true if the signing in user data is valid
     */
    boolean validate(RegistrationInfo info);
}
