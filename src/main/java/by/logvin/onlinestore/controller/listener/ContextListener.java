package by.logvin.onlinestore.controller.listener;

import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * The ContextListener class provides actions when context initialized and destroyed
 * <br>
 * Response for initialize and close connection in connection pool
 *
 * @author bylogvin
 * @see jakarta.servlet.ServletContextListener
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Can't init connections");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().closePoolData();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Can't close connections");
        }
    }
}
