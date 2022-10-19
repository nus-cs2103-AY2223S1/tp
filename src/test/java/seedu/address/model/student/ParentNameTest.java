package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ParentNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ParentName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "@";
        assertThrows(IllegalArgumentException.class, () -> new ParentName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ParentName.isValidName(null));

        // invalid name
        assertFalse(ParentName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(ParentName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ParentName.isValidName("")); // empty string
        assertTrue(ParentName.isValidName(" ")); // spaces only
        assertTrue(ParentName.isValidName("peter jack")); // alphabets only
        assertTrue(ParentName.isValidName("12345")); // numbers only
        assertTrue(ParentName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(ParentName.isValidName("Capital Tan")); // with capital letters
        assertTrue(ParentName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
