package by.logvin.onlinestore.dao.impl.sqlrequest;

public final class CardSQLRequest {
    public static final String selectCardByID =
            "SELECT * " +
                    "FROM cards c " +
                    "WHERE c.c_id=?";

    public static final String selectCardsByUserID =
            "SELECT * " +
                    "FROM cards c " +
                    "WHERE c.user_id=?";
    public static final String insertCard =
            "INSERT INTO cards(c_user_id, c_number, c_validity_period, c_authentication_code, c_cardholder) " +
                    "VALUES(?,?,?,?,?)";
    public static final String deleteCard =
            "DELETE FROM cards c " +
                    "WHERE c.c_id=?";
    public static final String updateCard =
            "UPDATE cards c " +
                    "SET c.c_number=?, c_validity_period=?, c_authentication_code=?, c_cardholder=? " +
                    "WHERE c.c_id=?";


    private CardSQLRequest() {
    }
}
