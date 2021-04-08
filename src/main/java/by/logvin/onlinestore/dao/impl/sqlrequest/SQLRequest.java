package by.logvin.onlinestore.dao.impl.sqlrequest;

public class SQLRequest {
    public static final String insertNewUser =
            "INSERT INTO users(u_email, u_password, u_first_name, u_last_name, u_date_of_birth, u_access, u_role) " +
                    "VALUES (?, ?, ?, ?, ?, 2, 1)";
    public static final String selectUserWithLoginAndPassword =
            "SELECT * " +
                    "FROM users u " +
                    "JOIN user_accesses ua " +
                    "ON ua.ua_id=u.u_access " +
                    "JOIN user_roles ur " +
                    "ON ur.ur_id=u.u_role " +
                    "WHERE u.u_email=? AND u.u_password=?";
    public static final String selectCardsByUserID =
            "SELECT * " +
                    "FROM cards c " +
                    "WHERE c.c_user_id=?";
    public static final String selectCategoryById =
            "SELECT * " +
                    "FROM categories c " +
                    "WHERE c.c_id = ?";
    public static final String selectAttributesByCategoryAndProductId =
            "SELECT * " +
                    "FROM attributes a " +
                    "WHERE a.a_product_id = ? AND a.a_category_id = ?";
    public static final String selectOneProduct =
            "SELECT * " +
                    "FROM products p " +
                    "ORDER BY rand() " +
                    "LIMIT 0,1";
    public static final String selectNProducts =
            "SELECT * " +
                    "FROM products p " +
                    "ORDER BY rand() " +
                    "LIMIT 0, ?";
    public static final String selectAllProducts =
            "SELECT * " +
                    "FROM products p";
    public static final String selectProductByProductID =
            "SELECT * " +
                    "FROM products p " +
                    "WHERE p.p_id=?";
}
