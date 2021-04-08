package by.logvin.onlinestore.service.validator;

public interface CardValidator {
    boolean validate(long number, int validityPeriod, int authenticationCode);
}
