package by.logvin.onlinestore.dao.impl.sqlrequest;

// FIXME: 11.04.2021
public final class ProductSQLRequest {
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
    public static final String selectOneProduct =
            "SELECT * " +
                    "FROM products p " +
                    "ORDER BY rand() " +
                    "LIMIT 0,1";

    public static final String insertProduct =
            "INSERT INTO products(p_name, p_price, p_quantity, p_description, p_category_id, p_photo_url) " +
                    "VALUES(?,?,?,?,?,?)";
    public static final String selectProductIDByNameAndCategory =
            "SELECT p.p_id " +
                    "FROM products p " +
                    "WHERE p.p_name=? AND p.p_category_id=?";
    public static final String deleteProductByID =
            "DELETE FROM products " +
                    "WHERE p_id=?";
    public static final String updateProductByID =
            "UPDATE products p " +
                    "SET p.p_name=?, p.p_price=?, p.p_quantity=?, p.p_description=?, p.p_category_id=?, p.p_photo_url=? " +
                    "WHERE p.p_id=?";
    public static final String selectProductByCategoryID =
            "SELECT * " +
                    "FROM products p " +
                    "WHERE p.p_category_id=?";


    private ProductSQLRequest() {

    }
}
