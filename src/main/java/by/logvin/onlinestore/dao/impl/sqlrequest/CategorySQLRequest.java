package by.logvin.onlinestore.dao.impl.sqlrequest;

/**
 * The class CategorySQLRequest contains category requests
 * @author bylogvin
 */
public final class CategorySQLRequest {
    public static final String INSERT_CATEGORY =
            "INSERT INTO categories(c_name) " +
                    "VALUES (?)";
    public static final String DELETE_CATEGORY =
            "DELETE FROM categories c " +
                    "WHERE c.c_id=?";
    public static final String SELECT_CATEGORY_BY_ID =
            "SELECT * " +
                    "FROM categories c " +
                    "WHERE c.c_id=?";
    public static final String SELECT_ALL_CATEGORIES =
            "SELECT * " +
                    "FROM categories";
    public static final String SELECT_CATEGORY_BY_NAME =
            "SELECT * " +
                    "FROM categories c " +
                    "WHERE c.c_name=?";

    private CategorySQLRequest() {
    }
}
