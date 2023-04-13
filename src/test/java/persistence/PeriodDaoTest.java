package persistence;

import entity.Period;
import persistence.GenericDao;
import test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type period dao test.
 */
class PeriodDaoTest {
    GenericDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(Period.class);
    }

    /**
     * Verifies gets all period's success.
     */
    @Test
    void getAllPeriodsSuccess() {
        List<Period> periods = dao.getAll();
        assertEquals(10, periods.size());
    }

    /**
     * Verifies a period is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        Period retrievedPeriod = (Period) dao.getById(7);
        assertNotNull(retrievedPeriod);
        assertEquals(6, retrievedPeriod.getFrequency());
        assertEquals("Month", retrievedPeriod.getTimeUnit());
    }
    /**
     * Verify successful insert of a period
     */
    @Test
    void insertSuccess() {

        Period newPeriod = new Period( 2, "year");
        int id = dao.addEntity(newPeriod);
        assertNotEquals(0,id);
        Period insertedPeriod = (Period) dao.getById(id);
        assertEquals(newPeriod, insertedPeriod);
    }

    /**
     * Verify successful delete of period
     */
    @Test
    void deleteSuccess() {
        dao.deleteEntity(dao.getById(1));
        assertNull(dao.getById(1));
    }

    /**
     * Verify successful update of period
     */
    @Test
    void updateSuccess() {
        int newFrequency = 3;
        Period updatePeriod = (Period) dao.getById(1);
        updatePeriod.setFrequency(newFrequency);
        dao.editEntity(updatePeriod);
        Period retrievedPeriod = (Period) dao.getById(1);
        assertEquals(updatePeriod, retrievedPeriod);
    }
}