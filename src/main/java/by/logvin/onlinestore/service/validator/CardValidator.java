package by.logvin.onlinestore.service.validator;

/**
 * The interface CardValidator provides method to validate cards
 *
 * @author bylogvin
 */
public interface CardValidator {
    /**
     * The method validate provides card validation
     *
     * @param number             long card number
     * @param validityPeriod     int card validity period
     * @param authenticationCode int card authentication code
     * @return boolean true if the data is valid
     */
    boolean validate(long number, int validityPeriod, int authenticationCode);
}
