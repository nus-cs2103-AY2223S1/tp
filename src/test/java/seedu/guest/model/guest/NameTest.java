package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

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
        assertFalse(Name.isValidName("peter^")); // ^ is not an allowed special character
        assertFalse(Name.isValidName("peter the 2nd")); // contains numbers
        assertFalse(Name.isValidName(".peter")); // starts with a special character

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets
        assertTrue(Name.isValidName("CaPItal TaN")); // with capital letters
        assertTrue(Name.isValidName("Capital  Tan")); // with multiple spaces
        assertTrue(Name.isValidName("Capital Tan',. /-',. /-Roger")); // with special characters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr Ariana Blake Cecil Dennis Elmer")); // long names
    }

    @Test
    public void hashcode() {
        Name tempName = new Name("peter jack");

        // same values -> return true
        assertEquals(tempName, new Name("peter jack"));

        // different values -> return false
        assertNotEquals(tempName, new Name("peter the 2nd"));
    }
}
