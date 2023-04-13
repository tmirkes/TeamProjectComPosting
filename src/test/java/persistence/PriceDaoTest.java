package persistence;

import entity.Price;
import test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.GenericDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type price dao test.
 */
class PriceDaoTest {
    GenericDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(Price.class);
    }

    /**
     * Verifies gets all price's ingredients success.
     */
    @Test
    void getAllRecipeIngredientsSuccess() {
        List<Price> prices = dao.getAll();
        assertEquals(3, prices.size());
    }

    /**
     * Verifies a price is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        Price retrievedPrice = (Price) dao.getById(1);
        assertNotNull(retrievedPrice);
        assertEquals(1.99, retrievedPrice.getPerUnit());
        assertEquals("pound", retrievedPrice.getUnitType());
    }
    /**
     * Verify successful insert of a price
     */
    @Test
    void insertSuccess() {

        Price newPrice = new Price(1, "unit type example");
        int id = dao.addEntity(newPrice);
        assertNotEquals(0,id);
        Price insertedPrice = (Price) dao.getById(id);
        assertEquals(newPrice, insertedPrice);
    }

    /**
     * Verify successful delete of price
     */
    @Test
    void deleteSuccess() {
        dao.deleteEntity(dao.getById(1));
        assertNull(dao.getById(1));
    }

    /**
     * Update a price
     */
    @Test
    void updateSuccess() {
        double newPricePerUnit = 5.00;
        Price updatePrice = (Price) dao.getById(2);
        updatePrice.setPerUnit(newPricePerUnit);
        dao.editEntity(updatePrice);
        Price retrievedPrice = (Price) dao.getById(1);
        assertEquals(updatePrice, retrievedPrice);
    }
}