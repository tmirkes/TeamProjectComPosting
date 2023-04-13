package java.persistence;

import com.amgenz.entity.RecipeInstruction;
import java.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type recipe instruction dao test.
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

        dao = new GenericDao(RecipeInstruction.class);
    }

    /**
     * Verifies gets all recipe's ingredients success.
     */
    @Test
    void getAllRecipesIngredientsSuccess() {
        List<RecipeInstruction> recipes = dao.getAll();
        assertEquals(14, recipes.size());
    }

    /**
     * Verifies a recipe ingredients is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        RecipeInstruction retrievedRecipeInstruction = (RecipeInstruction) dao.getById(7);
        assertNotNull(retrievedRecipeInstruction);
        assertEquals("Sprinkle with parsley and enjoy!", retrievedRecipeInstruction.getInstruction());
    }
    /**
     * Verify successful insert of a recipe ingredients
     */
    @Test
    void insertSuccess() {

        RecipeInstruction newRecipeInstruction = new RecipeInstruction(null, "Enjoy!", 5);
        int id = dao.insert(newRecipeInstruction);
        assertNotEquals(0,id);
        RecipeInstruction insertedRecipeInstruction = (RecipeInstruction) dao.getById(id);
        assertEquals(newRecipeInstruction, insertedRecipeInstruction);
    }

    /**
     * Verify successful delete of recipe ingredients
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }

    @Test
    void updateSuccess() {
        int newInstructionOrder = 3;
        RecipeInstruction updateRecipeInstruction = (RecipeInstruction) dao.getById(1);
        updateRecipeInstruction.setInstructionOrder(newInstructionOrder);
        dao.saveOrUpdate(updateRecipeInstruction);
        RecipeInstruction retrievedRecipeInstruction = (RecipeInstruction) dao.getById(1);
        assertEquals(updateRecipeInstruction, retrievedRecipeInstruction);
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<RecipeInstruction> recipes = dao.getByPropertyEqualString("instruction", "Sprinkle with parsley and enjoy!");
        assertEquals(1, recipes.size());
        assertEquals(7, recipes.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<RecipeInstruction> recipes = dao.getByPropertyLikeString("instruction", "Add");
        assertEquals(4, recipes.size());
    }
}