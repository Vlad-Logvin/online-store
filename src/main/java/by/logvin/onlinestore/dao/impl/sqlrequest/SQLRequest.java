package by.logvin.onlinestore.dao.impl.sqlrequest;

public class SQLRequest {
    public static final String selectCardsByUserID =
            "SELECT * " +
                    "FROM cards c " +
                    "WHERE c.c_user_id=?";
    public static final String selectCategoryById =
            "SELECT * " +
                    "FROM categories c " +
                    "WHERE c.c_id = ?";

}
