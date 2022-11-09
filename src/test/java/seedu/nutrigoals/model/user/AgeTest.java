package seedu.nutrigoals.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {

    @Test
    public void constructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Age("abc"));
    }

    @Test
    public void equals() {
        Age age = new Age("10");
        assertTrue(age.equals(age));
        assertFalse(age.equals(null));
        assertTrue(age.equals(new Age("10")));
    }

    @Test
    public void isValidAge() {
        assertTrue(Age.isValidAge("10"));
        assertFalse(Age.isValidAge("abc"));
        assertFalse(Age.isValidAge("-10"));
    }
}
