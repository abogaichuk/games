package app.games.db.dao;

import app.games.configuration.ApplicationConfig;
import app.games.configuration.HibernateConfig;
import app.games.db.model.Gamer;
import app.games.db.model.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author abogaichuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class, ApplicationConfig.class})
@WebAppConfiguration
@Transactional
public class GamerRepositoryTest {
    @Autowired
    private GamerDAO gamerDAO;
    private Gamer gamer;

    @Before
    public void createGamer() {
        gamer = new Gamer("test", "test");
        gamer.addOperation(createOperation(true, 1000, 0));
    }

    @Test
    public void shouldBeAbleToPersistUser() {
        assertEquals(0, gamerDAO.getAll().size());
        gamerDAO.addGamer(gamer);
        assertEquals(1, gamerDAO.getAll().size());
    }

    @Test
    public void shouldBeAbleToSelectById() {
        shouldBeAbleToPersistUser();
        Gamer testGamer = gamerDAO.getById(gamer.getAccount());
        assertNotNull(testGamer);
        assertEquals(gamer, testGamer);
        assertTrue(testGamer.getAccount() == gamer.getAccount());
    }

    @Test
    public void shouldBeAbleToPersistNewOperation() {
        shouldBeAbleToPersistUser();
        assertEquals(1, gamer.getOperations().size());
        gamerDAO.addOperation(createOperation(false, 100, 1000), gamer.getAccount());
        assertEquals(2, gamer.getOperations().size());
    }

    @Test
    public void shouldBeAbleGetLastTotalAccount() {
        shouldBeAbleToPersistNewOperation();
        assertEquals(2, gamer.getOperations().size());
        long balance = gamerDAO.getTotalBalance(gamer.getAccount());
        assertTrue(1000 - 100 == balance);
    }

    @Test
    public void shouldBeAbleToRemoveUser() {
        shouldBeAbleToPersistUser();
        gamerDAO.deleteGamer(gamer.getAccount());
        assertEquals(0, gamerDAO.getAll().size());
    }

    private Operation createOperation(boolean win, int value, long oldBalance) {
        Operation operation = new Operation();
        operation.setWin(win);
        operation.setValue(value);
        operation.setTransactionDate(new Date());
        if (win)
            operation.setBalance(oldBalance+value);
        else
            operation.setBalance(oldBalance-value);
        return operation;
    }
}
