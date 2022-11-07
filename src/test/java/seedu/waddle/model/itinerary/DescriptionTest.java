package seedu.waddle.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid name
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDescription("summer*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Description.isValidDescription("summer trip")); // alphabets only
        assertTrue(Description.isValidDescription("12345")); // numbers only
        assertTrue(Description.isValidDescription("2nd grad trip")); // alphanumeric characters
        assertTrue(Description.isValidDescription("Capital City Trip")); // with capital letters
        assertTrue(Description.isValidDescription("a wonderful trip with my beloved friends")); // long names
    }
}
