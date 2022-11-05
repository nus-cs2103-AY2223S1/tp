package seedu.studmap.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.testutil.Assert.assertThrows;

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

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("Po, the Tato")); // with comma
        assertTrue(Name.isValidName("To-To the Mato")); // with hyphen
    }

    @Test
    public void isSameName() {
        String testFullName = "Test Full Name";
        Name testName = new Name(testFullName);

        // null name
        assertNotEquals(testName, null);

        // spaces skipped
        assertNotEquals(testName, new Name("TestFullName"));

        // trailing space
        assertNotEquals(testName, new Name(testFullName + " "));

        // different case
        assertEquals(testName, new Name(testFullName.toLowerCase()));
        assertEquals(testName, new Name(testFullName.toUpperCase()));
    }
}
