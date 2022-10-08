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
    public void constructor_invalidGenderType_throwsIllegalArgumentException() {
        String invalidGenderType = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGenderType));
    }

    @Test
    public void isValidGender() {
        // null gender type
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender name
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("^")); // only non-alphanumeric characters
        assertFalse(Gender.isValidGender("male*")); // contains non-alphanumeric characters
        assertFalse(Gender.isValidGender("12345")); // numbers only
        assertFalse(Gender.isValidGender("female25")); // alphanumeric characters
        assertFalse(Gender.isValidGender("alpha male")); // long names

        // valid gender name
        assertTrue(Gender.isValidGender("male")); // alphabets only
        assertTrue(Gender.isValidGender("FEMALE")); // with capital letters
    }
}
