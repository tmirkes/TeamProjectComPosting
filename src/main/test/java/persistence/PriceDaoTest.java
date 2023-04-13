package java.persistence;

import com.amgenz.entity.RecipeIngredient;
import java.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type recipe ingredient dao test.
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

        dao = new GenericDao(RecipeIngredient.class);
    }

    /**
     * Verifies gets all recipe's ingredients success.
     */
    @Test
    void getAllRecipeIngredientsSuccess() {
        List<RecipeIngredient> recipes = dao.getAll();
        assertEquals(23, recipes.size());
    }

    /**
     * Verifies a recipe ingredient is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        RecipeIngredient retrievedRecipeIngredient = (RecipeIngredient) dao.getById(7);
        assertNotNull(retrievedRecipeIngredient);
        assertEquals("Parmesan", retrievedRecipeIngredient.getIngredient());
    }
    /**
     * Verify successful insert of a recipe ingredient
     */
    @Test
    void insertSuccess() {

        RecipeIngredient newRecipeIngredient = new RecipeIngredient(null, "Oregeno", "1", "cup");
        int id = dao.insert(newRecipeIngredient);
        assertNotEquals(0,id);
        RecipeIngredient insertedRecipeIngredient = (RecipeIngredient) dao.getById(id);
        assertEquals(newRecipeIngredient, insertedRecipeIngredient);
    }

    /**
     * Verify successful delete of recipe ingredient
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }

    /**
     * Update a recipe ingredient
     */
    @Test
    void updateSuccess() {
        String newIngredientAmount = "3";
        RecipeIngredient updateRecipeIngredient = (RecipeIngredient) dao.getById(1);
        updateRecipeIngredient.setIngredientAmount(newIngredientAmount);
        dao.saveOrUpdate(updateRecipeIngredient);
        RecipeIngredient retrievedRecipeIngredient = (RecipeIngredient) dao.getById(1);
        assertEquals(updateRecipeIngredient, retrievedRecipeIngredient);
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<RecipeIngredient> recipes = dao.getByPropertyEqualString("ingredient", "Milk");
        assertEquals(1, recipes.size());
        assertEquals(19, recipes.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<RecipeIngredient> recipes = dao.getByPropertyLikeString("ingredient", "f");
        assertEquals(2, recipes.size());
    }
}