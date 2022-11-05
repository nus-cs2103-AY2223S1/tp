package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
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
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("12345")); // numbers only
        assertFalse(Name.isValidName("2 Captain Jack Sparrow")); // standalone number
        assertFalse(Name.isValidName("Captain Jack 2 Sparrow")); // standalone number
        assertFalse(Name.isValidName("Captain Jack Sparrow 2")); // standalone number
        assertFalse(Name.isValidName("313%$#")); // inclusion of invalid characters
        assertFalse(Name.isValidName("314a!")); // inclusion of invalid characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("Capital Tan no2")); // with capital letters and valid alphanumeric sequence
        assertTrue(Name.isValidName("a2b"));
        assertTrue(Name.isValidName("a987b"));
        assertTrue(Name.isValidName("a98b76c543d2"));
        assertTrue(Name.isValidName("a2"));
        assertTrue(Name.isValidName("ab2"));
        assertTrue(Name.isValidName("2a"));
        assertTrue(Name.isValidName("2abc"));
        assertTrue(Name.isValidName("a2b a234b"));
    }

    @Test
    public void deepCopy_copyNotSameButEqual() {
        Name name = new Name("Capital Tan");
        Name deepCopy = name.deepCopy();
        assertNotSame(name, deepCopy);
        assertEquals(name, deepCopy);
    }
}
