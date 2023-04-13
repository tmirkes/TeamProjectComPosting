package persistence;


import entity.Service;
import test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.GenericDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type service dao test.
 */
class ServiceTest {
    GenericDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(Service.class);
    }

    /**
     * Verifies gets all service's success.
     */
    @Test
    void getAllUsersSuccess() {
        List<Service> services = dao.getAll();
        assertEquals(8, services.size());
    }

    /**
     * Verifies a service is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        Service retrieveService = (Service) dao.getById(2);
        assertNotNull(retrieveService);
        assertEquals("Bin Pick-Up", retrieveService.getName());
        assertEquals("Your composting bin will be scheduled for pick-up", retrieveService.getDescription());
    }
    /**
     * Verify successful insert of a service
     */
    @Test
    void insertSuccess() {

        Service newService = new Service("Service sample", "Service sample description");
        int id = dao.addEntity(newService);
        assertNotEquals(0,id);
        Service insertedService = (Service) dao.getById(id);
        assertEquals(newService, insertedService);
    }

    /**
     * Verify successful delete of a service
     */
    @Test
    void deleteSuccess() {
        dao.deleteEntity(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verify successful update of a service
     */
    @Test
    void saveOrUpdateSuccess() {
        String newName = "Bin Delivery";
        Service updateService = (Service) dao.getById(1);
        updateService.setName(newName);
        dao.editEntity(updateService);
        Service retrievedService = (Service) dao.getById(1);
        assertEquals(updateService, retrievedService);
    }

}