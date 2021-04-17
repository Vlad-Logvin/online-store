package by.logvin.onlinestore.dao.impl.sqlrequest;

public final class ProductSQLRequest {
    public static final String SELECT_N_PRODUCTS =
            "SELECT * " +
                    "FROM products p " +
                    "ORDER BY rand() " +
                    "LIMIT 0, ?";

    public static final String SELECT_ALL_PRODUCTS =
            "SELECT * " +
                    "FROM products p";
    public static final String SELECT_PRODUCT_BY_PRODUCT_ID =
            "SELECT * " +
                    "FROM products p " +
                    "WHERE p.p_id=?";
    public static final String SELECT_ONE_PRODUCT =
            "SELECT * " +
                    "FROM products p " +
                    "ORDER BY rand() " +
                    "LIMIT 0,1";

    public static final String INSERT_PRODUCT =
            "INSERT INTO products(p_name, p_price, p_quantity, p_description, p_category_id, p_photo_url) " +
                    "VALUES(?,?,?,?,?,?)";
    public static final String SELECT_PRODUCT_ID_BY_NAME_AND_CATEGORY =
            "SELECT p.p_id " +
                    "FROM products p " +
                    "WHERE p.p_name=? AND p.p_category_id=?";
    public static final String DELETE_PRODUCT_BY_ID =
            "DELETE FROM products " +
                    "WHERE p_id=?";
    public static final String UPDATE_PRODUCT_BY_ID =
            "UPDATE products p " +
                    "SET p.p_name=?, p.p_price=?, p.p_quantity=?, p.p_description=?, p.p_category_id=?, p.p_photo_url=? " +
                    "WHERE p.p_id=?";
    public static final String SELECT_PRODUCT_BY_CATEGORY_ID =
            "SELECT * " +
                    "FROM products p " +
                    "WHERE p.p_category_id=?";
    public static final String UPDATE_PRODUCT_QUANTITY_BY_ID =
            "UPDATE products p " +
                    "SET p.p_quantity=? " +
                    "WHERE p.p_id=?";

    public static final String UPDATE_INCREASE_PRODUCT_QUANTITY_BY_ID =
            "UPDATE products p " +
                    "SET p.p_quantity=p.p_quantity+? " +
                    "WHERE p.p_id=?";

    private ProductSQLRequest() {

    }
}
