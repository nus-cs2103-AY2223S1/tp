package seedu.nutrigoals.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.testutil.UserBuilder;

public class UserTest {

    @Test
    public void constructor_nullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new User(null, null, null, null, null));
    }

    @Test
    public void equals() {
        User userA = new UserBuilder().build();
        User userB = new UserBuilder().withHeight("123").build();

        assertTrue(userA.equals(userA));
        assertFalse(userA.equals(userB));
        assertFalse(userA.equals(null));
    }

    @Test
    public void testGetWeight() {
        User userA = new UserBuilder().build();
        assertEquals(userA.getWeight(), new Weight(UserBuilder.DEFAULT_WEIGHT));
    }

    @Test
    public void testGetIdealWeight() {
        User userA = new UserBuilder().build();
        assertEquals(userA.getIdealWeight(), new Weight(UserBuilder.DEFAULT_IDEAL));
    }

    @Test
    public void testGetHeight() {
        User userA = new UserBuilder().build();
        assertEquals(userA.getHeight(), new Height(UserBuilder.DEFAULT_HEIGHT));
    }

    @Test
    public void testGetGender() {
        User userA = new UserBuilder().build();
        assertEquals(userA.getGender(), new Gender(UserBuilder.DEFAULT_GENDER));
    }
}
