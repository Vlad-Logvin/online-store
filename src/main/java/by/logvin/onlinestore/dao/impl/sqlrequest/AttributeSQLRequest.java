package by.logvin.onlinestore.dao.impl.sqlrequest;

// FIXME: 11.04.2021
public final class AttributeSQLRequest {
    public static final String insertAttribute =
            "INSERT INTO attributes(a_name, a_value, a_product_id) " +
                    "VALUES(?,?,?)";
    public static final String selectAttributesByProductID =
            "SELECT * " +
                    "FROM attributes a " +
                    "WHERE a.a_product_id=?";
    public static final String deleteAttributesByProductID =
            "DELETE FROM attributes " +
                    "WHERE a_product_id=?";


    private AttributeSQLRequest() {
    }
}
