package seedu.rc4hdb.model.resident.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Gender}.
 */
public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "c";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void constructor_validGender_constructGender() {
        assertTrue(new Gender("M") instanceof Gender);
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("^")); // only non-alphanumeric characters
        assertFalse(Gender.isValidGender("M*")); // contains non-alphanumeric characters
        assertFalse(Gender.isValidGender("a")); // only F or M

        // valid gender
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("F"));
        assertTrue(Gender.isValidGender("m"));
    }

}
