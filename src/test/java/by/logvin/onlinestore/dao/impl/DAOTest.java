package by.logvin.onlinestore.dao.impl;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({SQLFavouriteDAOTest.class, SQLCategoryDAOTest.class})
public class DAOTest {
}
