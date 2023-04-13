package java.persistence;


import com.amgenz.entity.*;
import java.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */
class ServiceTest {
    GenericDao dao;
    GenericDao daoRecipe;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(User.class);
        daoRecipe = new GenericDao(Recipe.class);
    }

    /**
     * Verifies gets all user's success.
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAll();
        assertEquals(10, users.size());
    }

    /**
     * Verifies a user is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        User retrieveUser = (User) dao.getById(5);
        assertNotNull(retrieveUser);
        assertEquals("Ashley", retrieveUser.getFirstName());
    }
    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("Steve", "Flower", "stflow");
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = (User) dao.getById(id);
        assertEquals(newUser, insertedUser);
    }

    /**
     * Verify successful insert of a user with a recipe including instructions and directions
     */
    @Test
    void insertWithRecipeSuccess() {

        User newUser = new User("Sandra", "Bullex", "sandbull");

        Recipe newRecipe = new Recipe("Ham Roll Ups with Pickle", newUser, 134, 7, 2,
                    11, 5, "Snack", 5);

        newUser.addRecipe(newRecipe);

        int id = dao.insert(newUser);

        assertNotEquals(0,id);
        User insertedUser = (User) dao.getById(id);
        assertEquals(newUser, insertedUser);
        assertEquals(1, insertedUser.getRecipe().size());

    }

    /**
     * Verify successful delete of a user with a recipe including instructions and directions
     */
    @Test
    void deleteWithRecipeSuccess() {
        dao.delete(dao.getById(1));
        Recipe userRecipe = (Recipe) daoRecipe.getById(1);
        assertNull(userRecipe);
        assertNull(dao.getById(1));
    }


    /**
     * Verify successful delete of user
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    @Test
    void saveOrUpdateSuccess() {
        String newEmail = "halouis";
        User updateUser = (User) dao.getById(1);
        updateUser.setEmail(newEmail);
        dao.saveOrUpdate(updateUser);
        User retrievedUser = (User) dao.getById(1);
        assertEquals(updateUser, retrievedUser);
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyEqualString("lastName", "Smith");
        assertEquals(1, users.size());
        assertEquals(10, users.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLikeString("lastName", "w");
        assertEquals(4, users.size());
    }
}