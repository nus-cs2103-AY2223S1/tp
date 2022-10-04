package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {
    @Test
    public void isValidGender() {
        // null birthdate
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // Incorrect
        assertFalse(Gender.isValidGender(""));
        assertFalse(Gender.isValidGender(" "));
        assertFalse(Gender.isValidGender("abcdef"));
        assertFalse(Gender.isValidGender("abc def"));
        assertFalse(Gender.isValidGender("Male"));
        assertFalse(Gender.isValidGender("Female"));
        assertFalse(Gender.isValidGender("OTHERS"));

        // Correct
        assertTrue(Gender.isValidGender("male"));
        assertTrue(Gender.isValidGender("female"));
        assertTrue(Gender.isValidGender("others"));
    }
}
