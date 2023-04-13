package java.persistence;

import entity.Material;
import java.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.GenericDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type recipe dao test.
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
        assertEquals(3, materials.size());
    }

    /**
     * Verifies a recipe is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        Recipe retrievedRecipe = (Recipe) dao.getById(1);
        assertNotNull(retrievedRecipe);
        assertEquals("Homemade Chicken Alfredo", retrievedRecipe.getRecipeName());
    }
    /**
     * Verify successful insert of a recipe
     */
    @Test
    void insertSuccess() {

        Recipe newRecipe = new Recipe("Garlic Butter Baked Salmon", null, 448, 37,
                13, 28, 30, "Dinner", 4);
        int id = dao.insert(newRecipe);
        assertNotEquals(0,id);
        Recipe insertedRecipe = (Recipe) dao.getById(id);
        assertEquals(newRecipe, insertedRecipe);
    }

    /**
     * Verify successful insert of a user with a recipe including instructions and directions
     */
    @Test
    void insertWithIngredientsAndInstructionsSuccess() {

        Recipe newRecipe = new Recipe("Ham Roll Ups with Pickle", null, 134, 7, 2,
                11, 5, "Snack", 5);

        RecipeIngredient newRecipeIngredient1 = new RecipeIngredient(newRecipe, "Deli Ham", "10",
                "slices");

        RecipeIngredient newRecipeIngredient2 = new RecipeIngredient(newRecipe, "Cream Cheese", "8",
                "ounces");

        RecipeIngredient newRecipeIngredient3 = new RecipeIngredient(newRecipe, "Dill Pickle Spears",
                "10", null);

        RecipeInstruction newRecipeInstruction1 = new RecipeInstruction(newRecipe,
                "Evenly spread cream cheese on to each ham slice.", 1);

        RecipeInstruction newRecipeInstruction2 = new RecipeInstruction(newRecipe,
                "Place dill pickle spear at the end of each slice and roll up.", 2);

        RecipeInstruction newRecipeInstruction3 = new RecipeInstruction(newRecipe,
                "Using a sharp knife, cut each roll cross-wise into 1/2\" to 1\" pieces.", 3);

        newRecipe.addRecipeIngredients(newRecipeIngredient1);
        newRecipe.addRecipeIngredients(newRecipeIngredient2);
        newRecipe.addRecipeIngredients(newRecipeIngredient3);
        newRecipe.addRecipeInstruction(newRecipeInstruction1);
        newRecipe.addRecipeInstruction(newRecipeInstruction2);
        newRecipe.addRecipeInstruction(newRecipeInstruction3);

        int id = dao.insert(newRecipe);

        assertNotEquals(0,id);
        Recipe insertedRecipe = (Recipe) dao.getById(id);
        assertEquals(newRecipe, insertedRecipe);
        assertEquals(3, insertedRecipe.getIngredients().size());
        assertEquals(3, insertedRecipe.getInstructions().size());
    }

    /**
     * Verify successful delete of recipe
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }

    @Test
    void updateSuccess() {
        int newServing = 4;
        Recipe updateRecipe = (Recipe) dao.getById(1);
        updateRecipe.setServing(newServing);
        dao.saveOrUpdate(updateRecipe);
        Recipe retrievedRecipe = (Recipe) dao.getById(1);
        assertEquals(updateRecipe, retrievedRecipe);
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Recipe> recipes = dao.getByPropertyEqualString("type", "Breakfast");
        assertEquals(1, recipes.size());
        assertEquals(3, recipes.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Recipe> recipes = dao.getByPropertyLikeString("recipeName", "h");
        assertEquals(2, recipes.size());
    }
}