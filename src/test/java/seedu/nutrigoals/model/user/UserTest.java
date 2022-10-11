package seedu.nutrigoals.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.testutil.UserBuilder;

public class UserTest {

    @Test
    public void constructor_nullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new User(null, null, null, null));
    }

    @Test
    public void equals() {
        User userA = new UserBuilder().build();
        User userB = new UserBuilder().withHeight("123").build();

        assertTrue(userA.equals(userA));
        assertFalse(userA.equals(userB));
    }
}
