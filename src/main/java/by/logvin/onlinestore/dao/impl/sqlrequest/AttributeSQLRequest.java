package by.logvin.onlinestore.dao.impl.sqlrequest;

public final class AttributeSQLRequest {
    public static final String insertAttribute =
            "INSERT INTO attributes(a_name, a_value, a_product_id) " +
                    "VALUES(?,?,?)";
    public static final String selectAttributesByProductID =
            "SELECT * " +
                    "FROM attributes a " +
                    "WHERE a.product_id=?";
    public static final String deleteAttributesByProductID =
            "DELETE FROM attributes a" +
                    "WHERE a.a_product_id=?";


    private AttributeSQLRequest() {
    }
}
