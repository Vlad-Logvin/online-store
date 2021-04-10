package by.logvin.onlinestore.dao.impl.sqlrequest;

// FIXME: 11.04.2021
public final class OrderSQLRequest {


    public static final String selectAllOrdersByUserID =
            "SELECT * " +
                    "FROM orders o " +
                    "LEFT JOIN m2m_orders_products op " +
                    "ON op.op_order_id=o.o_id " +
                    "WHERE o.o_user_id=?";
    public static final String insertOrder =
            "INSERT INTO orders(o_user_id, o_grand_total, o_card_id, o_date_of_purchase) " +
                    "VALUES(?,?,?,?)";
    public static final String insertProductToOrder =
            "INSERT INTO m2m_orders_products(op_order_id, op_product_id, op_quantity) " +
                    "VALUES(?,?,?)";

    public static final String selectLastOrderID =
            "SELECT o_id " +
                    "FROM orders " +
                    "WHERE o_date_of_purchase = (SELECT MAX(o_date_of_purchase) FROM orders WHERE o_user_id=?)";
    public static final String selectAllOrders =
            "SELECT * " +
                    "FROM orders o " +
                    "LEFT JOIN m2m_orders_products op " +
                    "ON op.op_order_id=o.o_id";

    private OrderSQLRequest() {
    }
}
