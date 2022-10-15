package seedu.condonery.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.condonery.model.fields.Name;

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
        assertFalse(Name.isValidName("Waterfront*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("water front")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("water the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Water Front")); // with capital letters
        assertTrue(Name.isValidName("Water Front the condo near the sea")); // long names
        assertTrue(Name.isValidName("Pinnacle@Duxton")); // with @
    }
}
