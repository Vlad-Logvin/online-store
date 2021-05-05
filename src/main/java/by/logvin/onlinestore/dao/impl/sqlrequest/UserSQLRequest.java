package by.logvin.onlinestore.dao.impl.sqlrequest;

/**
 * The class UserSQLRequest contains user requests
 * @author bylogvin
 */
public final class UserSQLRequest {
    public static final String INSERT_USER =
            "INSERT INTO users(u_email, u_password, u_first_name, u_last_name, u_date_of_birth, u_access, u_role) " +
                    "VALUES (?, ?, ?, ?, ?, 1, 2)";

    public static final String SELECT_USER_WITH_LOGIN_AND_PASSWORD =
            "SELECT * " +
                    "FROM users u " +
                    "LEFT JOIN user_accesses ua " +
                    "ON ua.ua_id=u.u_access " +
                    "LEFT JOIN user_roles ur " +
                    "ON ur.ur_id=u.u_role " +
                    "WHERE u.u_email=? AND u.u_password=?";
    public static String UPDATE_USER =
            "UPDATE users u " +
                    "SET u.u_email=?, u.u_password=?, u.u_first_name=?, u.u_last_name=?, u.u_date_of_birth=? " +
                    "WHERE u.u_id=?";

    public static final String UPDATE_USER_ROLE =
            "UPDATE users u " +
                    "SET u.u_role=? " +
                    "WHERE u.u_id=?";

    public static final String UPDATE_USER_ACCESS =
            "UPDATE users u " +
                    "SET u_access=? " +
                    "WHERE u.u_id=?";
    public static final String SELECT_USER_EMAIL_AND_PASSWORD =
            "SELECT u.u_email, u.u_password " +
                    "FROM users u";


    private UserSQLRequest() {
    }
}
