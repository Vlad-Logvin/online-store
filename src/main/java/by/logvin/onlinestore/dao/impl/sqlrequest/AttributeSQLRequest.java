package by.logvin.onlinestore.dao.impl.sqlrequest;


public final class AttributeSQLRequest {
    public static final String INSERT_ATTRIBUTE =
            "INSERT INTO attributes(a_name, a_value, a_product_id) " +
                    "VALUES(?,?,?)";
    public static final String SELECT_ATTRIBUTES_BY_PRODUCT_ID =
            "SELECT * " +
                    "FROM attributes a " +
                    "WHERE a.a_product_id=?";
    public static final String DELETE_ATTRIBUTES_BY_PRODUCT_ID =
            "DELETE FROM attributes " +
                    "WHERE a_product_id=?";


    private AttributeSQLRequest() {
    }
}
