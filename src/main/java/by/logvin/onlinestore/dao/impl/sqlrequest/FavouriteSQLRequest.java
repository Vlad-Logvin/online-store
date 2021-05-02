package by.logvin.onlinestore.dao.impl.sqlrequest;

/**
 * The class FavouriteSQLRequest contains favourite requests
 * @author bylogvin
 */
public final class FavouriteSQLRequest {
    public static final String SELECT_FAVOURITE_BY_USER_ID =
            "SELECT * " +
                    "FROM favourites f " +
                    "LEFT JOIN m2m_favourites_products fp " +
                    "ON f.f_id=fp.fp_favourite_id " +
                    "WHERE f.f_user_id=?";
    public static final String INSERT_FAVOURITE =
            "INSERT INTO favourites(f_user_id) " +
                    "VALUES(?)";
    public static final String ADD_PRODUCT_TO_FAVOURITE =
            "INSERT INTO m2m_favourites_products(fp_favourite_id, fp_product_id) " +
                    "VALUES(?, ?)";
    public static final String REMOVE_PRODUCT_FROM_FAVOURITE =
            "DELETE FROM m2m_favourites_products fp " +
                    "WHERE fp.fp_favourite_id=? AND fp.fp_product_id=?";
    public static final String DELETE_FAVOURITE_BY_USER_ID =
            "DELETE FROM favourites f " +
                    "WHERE f.f_user_id=?";


    private FavouriteSQLRequest() {
    }
}
