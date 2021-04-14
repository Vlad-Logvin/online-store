package by.logvin.onlinestore.controller.util;

import by.logvin.onlinestore.controller.util.impl.SessionExistenceImpl;
import by.logvin.onlinestore.controller.util.impl.UserExistenceImpl;

public class ExistenceProvider {
    private final static ExistenceProvider existenceProvider = new ExistenceProvider();
    private ExistenceProvider(){}

    public static ExistenceProvider getInstance() {
        return existenceProvider;
    }

    private final SessionExistence sessionExistence = new SessionExistenceImpl();

    public SessionExistence getSessionExistence() {
        return sessionExistence;
    }

    private final UserExistence userExistence = new UserExistenceImpl();

    public UserExistence getUserExistence() {
        return userExistence;
    }
}
