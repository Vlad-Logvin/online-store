package by.logvin.onlinestore.dao.impl.sqlrequest;


public final class BasketSQLRequest {

    public static final String SELECT_BASKET_BY_USER_ID =
            "SELECT * " +
                    "FROM baskets b " +
                    "LEFT JOIN m2m_products_baskets pb " +
                    "ON b.b_id=pb.pb_basket_id " +
                    "WHERE b.b_user_id=?";
    public static final String ADD_PRODUCT_TO_BASKET =
            "INSERT INTO m2m_products_baskets(pb_basket_id, pb_product_id) " +
                    "VALUES(?, ?)";
    public static final String REMOVE_PRODUCT_FROM_BASKET =
            "DELETE FROM m2m_products_baskets pb " +
                    "WHERE pb.pb_basket_id=? AND pb.pb_product_id=?";
    public static final String DELETE_BASKET_BY_USER_ID =
            "DELETE FROM baskets b " +
                    "WHERE b.b_user_id=?";
    public static final String INSERT_BASKET =
            "INSERT INTO baskets(b_user_id) " +
                    "VALUES(?)";

    private BasketSQLRequest() {
    }
}
