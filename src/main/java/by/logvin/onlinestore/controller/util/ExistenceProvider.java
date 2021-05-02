package by.logvin.onlinestore.controller.util;

import by.logvin.onlinestore.controller.util.impl.SessionExistenceImpl;
import by.logvin.onlinestore.controller.util.impl.UserExistenceImpl;

/**
 * The ExistenceProviderClass contains existence instances
 *
 * @author bylogvin
 */
public class ExistenceProvider {
    /**
     * {@link ExistenceProvider} instance
     */
    private final static ExistenceProvider instance = new ExistenceProvider();
    private ExistenceProvider(){}

    /**
     *
     * @return {@link ExistenceProvider} instance
     */
    public static ExistenceProvider getInstance() {
        return instance;
    }

    /**
     * {@link SessionExistence} instance
     */
    private final SessionExistence sessionExistence = new SessionExistenceImpl();

    public SessionExistence getSessionExistence() {
        return sessionExistence;
    }

    /**
     * {@link UserExistence} instance
     */
    private final UserExistence userExistence = new UserExistenceImpl();

    public UserExistence getUserExistence() {
        return userExistence;
    }
}
