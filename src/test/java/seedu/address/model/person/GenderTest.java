package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null Gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // blank gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only

        // invalid gender
        assertFalse(Gender.isValidGender("Femalr"));
        assertFalse(Gender.isValidGender("12"));
        assertFalse(Gender.isValidGender("male123"));

        // valid gender
        assertTrue(Gender.isValidGender("Female"));
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("m"));
        assertTrue(Gender.isValidGender("male"));
    }
}
