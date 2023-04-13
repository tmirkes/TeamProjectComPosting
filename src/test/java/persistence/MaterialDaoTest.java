package persistence;

import entity.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.GenericDao;
import test.util.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type material dao test.
 */
class MaterialDaoTest {
    GenericDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(Material.class);
    }

    /**
     * Verifies gets all material's success.
     */
    @Test
    void getAllMaterialsSuccess() {
        List<Material> materials = dao.getAll();
        assertEquals(10, materials.size());
    }

    /**
     * Verifies a materials are returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        Material retrievedMaterial = (Material) dao.getById(1);
        assertNotNull(retrievedMaterial);
        assertEquals("Squishy leaves", retrievedMaterial.getName());
    }
    /**
     * Verify successful insert of a material
     */
    @Test
    void insertSuccess() {

        Material newMaterial = new Material("sample material", "sample material comment");
        int id = dao.addEntity(newMaterial);
        assertNotEquals(0,id);
        Material insertedMaterial = (Material) dao.getById(id);
        assertEquals(newMaterial, insertedMaterial);
    }

    /**
     * Verify successful delete of a material
     */
    @Test
    void deleteSuccess() {
        dao.deleteEntity(dao.getById(1));
        assertNull(dao.getById(1));
    }

    /**
     * Verify successful update of a material
     */
    @Test
    void updateSuccess() {
        String newComment = "Probably a little old and crunchy";
        Material updateMaterial = (Material) dao.getById(1);
        updateMaterial.setComments(newComment);
        dao.editEntity(updateMaterial);
        Material retrievedMaterial = (Material) dao.getById(1);
        assertEquals(updateMaterial, retrievedMaterial);
    }
}