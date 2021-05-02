package by.logvin.onlinestore.dao.exception;

/**
 * The class DAOException need for throwing exception in DAO
 * @author bylogvin
 * @see java.lang.Exception
 */
public class DAOException extends Exception{
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
