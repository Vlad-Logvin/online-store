package by.logvin.onlinestore.dao.connection;

import java.util.ResourceBundle;

/**
 * The class DBResourceManager is need for get parameters from db.properties
 * @author bylogvin
 */
public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}