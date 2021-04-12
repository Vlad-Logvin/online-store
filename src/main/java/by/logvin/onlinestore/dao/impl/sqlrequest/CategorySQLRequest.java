package by.logvin.onlinestore.dao.impl.sqlrequest;

// FIXME: 11.04.2021
public final class CategorySQLRequest {
    public static final String insertCategory =
            "INSERT INTO categories(c_name) " +
                    "VALUES (?)";
    public static final String deleteCategory =
            "DELETE FROM categories c " +
                    "WHERE c.c_id=?";
    public static final String selectCategoryByID =
            "SELECT * " +
                    "FROM categories c " +
                    "WHERE c.c_id=?";
    public static final String selectAllCategories =
            "SELECT * " +
                    "FROM categories";
    public static final String selectCategoryByName =
            "SELECT * " +
                    "FROM categories c " +
                    "WHERE c.c_name=?";

    private CategorySQLRequest() {
    }
}
