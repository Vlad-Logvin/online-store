package by.logvin.onlinestore.dao.impl.sqlrequest;

public final class FavouriteSQLRequest {
    public static final String selectFavouriteByUserID =
            "SELECT * " +
                    "FROM favourites f " +
                    "LEFT JOIN m2m_favourites_products fp " +
                    "ON f.f_id=fp.fp_favourite_id " +
                    "WHERE f.f_id=?";
    public static final String insertFavourite =
            "INSERT INTO favourites(f_user_id) " +
                    "VALUES(?)";
    public static final String addProductToFavourite =
            "INSERT INTO m2m_favourites_products(fp_favourite_id, fp_product_id) " +
                    "VALUES(?, ?)";
    public static final String removeProductFromFavourite =
            "DELETE FROM m2m_favourites_products fp " +
                    "WHERE fp.fp_favourite_id=? AND fp.fp_product_id=?";
    public static final String deleteFavouriteByUserID =
            "DELETE FROM favourites f " +
                    "WHERE f.f_user_id=?";


    private FavouriteSQLRequest() {
    }
}
