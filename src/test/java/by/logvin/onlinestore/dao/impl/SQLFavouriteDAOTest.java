package by.logvin.onlinestore.dao.impl;

import by.logvin.onlinestore.bean.Favourite;
import by.logvin.onlinestore.bean.Product;
import by.logvin.onlinestore.bean.RegistrationInfo;
import by.logvin.onlinestore.bean.User;
import by.logvin.onlinestore.dao.FavouriteDAO;
import by.logvin.onlinestore.dao.ProductDAO;
import by.logvin.onlinestore.dao.UserDAO;
import by.logvin.onlinestore.dao.connection.ConnectionPool;
import by.logvin.onlinestore.dao.connection.ConnectionPoolException;
import by.logvin.onlinestore.dao.exception.DAOException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SQLFavouriteDAOTest {
    private static final RegistrationInfo regInfo = new RegistrationInfo("test",
            "test",
            "test",
            "test",
            null);

    private static User user = null;

    private static final UserDAO userDAO = new SQLUserDAO();
    private static final ProductDAO productDAO = new SQLProductDAO();
    private static final FavouriteDAO favouriteDAO = new SQLFavouriteDAO();

    @BeforeAll
    public static void userImplantation() throws DAOException {
        user = userDAO.signIn(regInfo.getEmail(), regInfo.getPassword());
        if (user == null) {
            userDAO.signUp(regInfo);
        }
        user = userDAO.signIn(regInfo.getEmail(), regInfo.getPassword());
    }

    @BeforeAll
    public static void connectionInitializing() throws ConnectionPoolException {
        ConnectionPool.getInstance().initPoolData();
    }

    private static Stream<Arguments> products() throws DAOException {
        return Stream.of(
                Arguments.of(productDAO.take()),
                Arguments.of(productDAO.take()),
                Arguments.of(productDAO.take())
        );
    }

    @ParameterizedTest
    @MethodSource("products")
    void favouriteDAOMethodsTest(Product product) throws DAOException {
        Assert.assertTrue(favouriteDAO.createFavourite(user.getId()));
        Favourite favourite = favouriteDAO.takeFavouriteByUserID(user.getId());
        Assert.assertTrue(favouriteDAO.addProduct(favourite.getId(), product.getId()));
        Assert.assertTrue(favouriteDAO.removeProduct(favourite.getId(), product.getId()));
        Assert.assertTrue(favouriteDAO.deleteFavouriteByUserID(user.getId()));
    }

    @AfterAll
    public static void connectionClosing() throws ConnectionPoolException {
        ConnectionPool.getInstance().closePoolData();
    }
}
