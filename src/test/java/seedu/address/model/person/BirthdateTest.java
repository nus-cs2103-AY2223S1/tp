package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdateTest {
    @Test
    public void isValidBirthdate() {
        // null birthdate
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // Totally incorrect format
        assertFalse(Birthdate.isValidBirthdate(""));
        assertFalse(Birthdate.isValidBirthdate(" "));
        assertFalse(Birthdate.isValidBirthdate("abcdef"));
        assertFalse(Birthdate.isValidBirthdate("abc def"));

        // Slightly incorrect format
        assertFalse(Birthdate.isValidBirthdate("2020-31-12"));
        assertFalse(Birthdate.isValidBirthdate("12-12-2020"));

        // Correct format
        assertTrue(Birthdate.isValidBirthdate("2020-12-31"));
        assertTrue(Birthdate.isValidBirthdate("1999-1-1"));
    }
}
