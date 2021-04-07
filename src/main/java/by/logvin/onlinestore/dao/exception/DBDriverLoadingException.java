package by.logvin.onlinestore.dao.exception;

public class DBDriverLoadingException extends RuntimeException{
    public DBDriverLoadingException() {
        super();
    }

    public DBDriverLoadingException(String message) {
        super(message);
    }

    public DBDriverLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBDriverLoadingException(Throwable cause) {
        super(cause);
    }
}
