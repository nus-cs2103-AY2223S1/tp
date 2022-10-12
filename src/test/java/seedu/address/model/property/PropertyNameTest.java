package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;


public class PropertyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PropertyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new PropertyName(invalidName));
    }

    @Test
    public void isValidPropertyName() {
        // null name
        assertThrows(NullPointerException.class, () -> PropertyName.isValidPropertyName(null));

        // invalid name
        assertFalse(PropertyName.isValidPropertyName("")); // empty string
        assertFalse(PropertyName.isValidPropertyName(" ")); // spaces only
        assertFalse(PropertyName.isValidPropertyName("^")); // only non-alphanumeric characters
        assertFalse(PropertyName.isValidPropertyName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(PropertyName.isValidPropertyName("peter jack")); // alphabets only
        assertTrue(PropertyName.isValidPropertyName("12345")); // numbers only
        assertTrue(PropertyName.isValidPropertyName("peter the 2nd")); // alphanumeric characters
        assertTrue(PropertyName.isValidPropertyName("Capital Tan")); // with capital letters
        assertTrue(PropertyName.isValidPropertyName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
