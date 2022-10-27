package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("1234567")); // missing e
        assertFalse(Email.isValidEmail("e123456")); // missing 1 digit

        // invalid parts
        assertFalse(Email.isValidEmail("f1234567")); // different starting letter
        assertFalse(Email.isValidEmail("@1234567")); // symbol as starting letter
        assertFalse(Email.isValidEmail("e0!234567")); // symbol in digits
        assertFalse(Email.isValidEmail("e 01234567")); // space between letter and digits
        assertFalse(Email.isValidEmail("e0123 4567")); // space between digits

        // valid email
        assertTrue(Email.isValidEmail("e1234567")); // all different numbers
        assertTrue(Email.isValidEmail("E1234567")); // upper case letter
        assertTrue(Email.isValidEmail("e0000000")); // all same numbers
    }
}
