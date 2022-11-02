package seedu.nutrigoals.model.meal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("eggs*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("cheese hot dog with mustard and chilli")); // long name

        // valid name
        assertTrue(Name.isValidName("cookies")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("5 bananas")); // alphanumeric characters
        assertTrue(Name.isValidName("Fishcake")); // with capital letters
        assertTrue(Name.isValidName("carrot cake")); // not too long name
        assertTrue(Name.isValidName("Frog porridge and soy sauce")); // name with boundary length
    }
}
