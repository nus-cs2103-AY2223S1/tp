package seedu.address.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters

        // long names
        assertTrue(Name.isValidName("123456789 123456789 1234")); // 24 chars
        assertFalse(Name.isValidName("123456789 123456789 12345")); // 25 chars invalid
    }

    @Test
    public void compareTo_sameCapitalisationAndSpelling_isEqual() {
        assertEquals(0, new Name("Aaron").compareTo(new Name("Aaron")));
        assertEquals(0, new Name("65aaron").compareTo(new Name("65aaron")));
    }

    @Test
    public void compareTo_differentCapitalisationSameSpelling_isNotEqual() {
        assertNotEquals(0, new Name("aaron").compareTo(new Name("Aaron")));
        assertNotEquals(0, new Name("A").compareTo(new Name("AA")));
    }
}
