package by.logvin.onlinestore.dao.impl.sqlrequest;

/**
 * The class CardSQLRequest contains card requests
 * @author bylogvin
 */
public final class CardSQLRequest {
    public static final String SELECT_CARD_BY_ID =
            "SELECT * " +
                    "FROM cards c " +
                    "WHERE c.c_id=?";

    public static final String SELECT_CARDS_BY_USER_ID =
            "SELECT * " +
                    "FROM cards c " +
                    "WHERE c.c_user_id=?";
    public static final String INSERT_CARD =
            "INSERT INTO cards(c_user_id, c_number, c_validity_period, c_authentication_code, c_cardholder) " +
                    "VALUES(?,?,?,?,?)";
    public static final String DELETE_CARD =
            "DELETE FROM cards c " +
                    "WHERE c.c_id=?";
    public static final String UPDATE_CARD =
            "UPDATE cards c " +
                    "SET c.c_number=?, c_validity_period=?, c_authentication_code=?, c_cardholder=? " +
                    "WHERE c.c_id=?";


    private CardSQLRequest() {
    }
}
