package by.logvin.onlinestore.dao.impl.sqlrequest;

// FIXME: 11.04.2021
public final class UserSQLRequest {
    public static final String insertUser =
            "INSERT INTO users(u_email, u_password, u_first_name, u_last_name, u_date_of_birth, u_access, u_role) " +
                    "VALUES (?, ?, ?, ?, ?, 1, 2)";

    public static final String selectUserWithLoginAndPassword =
            "SELECT * " +
                    "FROM users u " +
                    "JOIN user_accesses ua " +
                    "ON ua.ua_id=u.u_access " +
                    "JOIN user_roles ur " +
                    "ON ur.ur_id=u.u_role " +
                    "WHERE u.u_email=? AND u.u_password=?";
    public static String updateUser =
            "UPDATE users u " +
                    "SET u.u_email=?, u.u_password=?, u.u_first_name, u.u_last_name, u.u_date_of_birth " +
                    "WHERE u.u_id=?";


    private UserSQLRequest() {
    }
}
