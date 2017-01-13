import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.nure.fedorenko.SummaryTask4.db.DBManager;
import ua.nure.fedorenko.SummaryTask4.db.dao.factory.DAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.dao.generics.UserDAO;
import ua.nure.fedorenko.SummaryTask4.db.dao.mysql.MySqlDAOFactory;
import ua.nure.fedorenko.SummaryTask4.db.entity.User;
import ua.nure.fedorenko.SummaryTask4.exception.DBException;

import java.util.List;

/**
 * Created by Anastasia on 06.01.2017.
 */
public class DAOTest {

    private static UserDAO userDAO;

    @BeforeClass
    public static void setUp() throws Exception {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factories.MYSQL);
        userDAO = daoFactory.getUserDAO();
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = userDAO.getAll();
        Assert.assertNotNull(users);
        Assert.assertTrue(users.size() > 0);
    }
}
