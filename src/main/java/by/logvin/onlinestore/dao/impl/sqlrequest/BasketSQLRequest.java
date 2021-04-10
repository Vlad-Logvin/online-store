package by.logvin.onlinestore.dao.impl.sqlrequest;

// FIXME: 11.04.2021
public final class BasketSQLRequest {


    public static final String selectBasketByUserID =
            "SELECT * " +
                    "FROM baskets b " +
                    "LEFT JOIN m2m_products_baskets pb " +
                    "ON b.b_id=pb.pb_basket_id " +
                    "WHERE b.b_user_id=?";
    public static final String addProductToBasket =
            "INSERT INTO m2m_products_baskets(pb_basket_id, pb_product_id) " +
                    "VALUES(?, ?)";
    public static final String removeProductFromBasket =
            "DELETE FROM m2m_products_baskets pb " +
            "WHERE pb.pb_basket_id=? AND pb.pb_product_id=?";
    public static final String deleteBasketByUserID =
            "DELETE FROM baskets b " +
                    "WHERE b.b_user_id=?";
    public static final String insertBasket =
            "INSERT INTO baskets(b_user_id) " +
                    "VALUES(?)";

    private BasketSQLRequest(){}
}
