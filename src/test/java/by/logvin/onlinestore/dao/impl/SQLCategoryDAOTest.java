package by.logvin.onlinestore.dao.impl;


import by.logvin.onlinestore.bean.Category;
import by.logvin.onlinestore.dao.CategoryDAO;
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

public class SQLCategoryDAOTest {
    private static final CategoryDAO categoryDAO = new SQLCategoryDAO();

    private static Stream<Arguments> categories() {
        return Stream.of(
                Arguments.of(new Category(0, "testCategory")),
                Arguments.of(new Category(0, "")),
                Arguments.of(new Category(0, null))
        );
    }


    @BeforeAll
    public static void connectionInitializing() throws ConnectionPoolException {
        ConnectionPool.getInstance().initPoolData();
    }

    @ParameterizedTest
    @MethodSource("categories")
    void categoryDAOMethodsTest(Category category) {
        try {
            Assert.assertTrue(categoryDAO.addCategory(category.getName()));
            Category actualCategory = categoryDAO.takeCategory(category.getName());
            Assert.assertEquals(category.getName(), actualCategory.getName());
            Assert.assertTrue(categoryDAO.deleteCategory(actualCategory.getId()));
        }catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

    @AfterAll
    public static void connectionClosing() throws ConnectionPoolException {
        ConnectionPool.getInstance().closePoolData();
    }
}