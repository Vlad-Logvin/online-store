package by.logvin.onlinestore.dao.impl.sqlrequest;

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


    private CategorySQLRequest() {
    }
}
