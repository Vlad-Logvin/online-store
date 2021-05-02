package by.logvin.onlinestore.dao.impl.sqlrequest;

/**
 * The class OrderSQLRequest contains order requests
 * @author bylogvin
 */
public final class OrderSQLRequest {
    public static final String SELECT_ALL_ORDERS_BY_USER_ID =
            "SELECT * " +
                    "FROM orders o " +
                    "LEFT JOIN m2m_orders_products op " +
                    "ON op.op_order_id=o.o_id " +
                    "WHERE o.o_user_id=?";
    public static final String INSERT_ORDER =
            "INSERT INTO orders(o_user_id, o_grand_total, o_card_id, o_date_of_purchase) " +
                    "VALUES(?,?,?,?)";
    public static final String INSERT_PRODUCT_TO_ORDER =
            "INSERT INTO m2m_orders_products(op_order_id, op_product_id, op_quantity) " +
                    "VALUES(?,?,?)";

    public static final String SELECT_LAST_ORDER_ID =
            "SELECT o_id " +
                    "FROM orders " +
                    "WHERE o_date_of_purchase = (SELECT MAX(o_date_of_purchase) FROM orders WHERE o_user_id=?)";
    public static final String SELECT_ALL_ORDERS =
            "SELECT * " +
                    "FROM orders o " +
                    "LEFT JOIN m2m_orders_products op " +
                    "ON op.op_order_id=o.o_id";

    private OrderSQLRequest() {
    }
}
