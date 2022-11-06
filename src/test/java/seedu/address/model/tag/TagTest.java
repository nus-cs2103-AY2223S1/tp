package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // invalid tag name
        assertFalse(Tag.isValidTagName(" ")); // Empty string

        // valid tag name
        assertTrue(Tag.isValidTagName("cool dude")); // Spaces allowed
        assertTrue(Tag.isValidTagName("friend"));
        assertTrue(Tag.isValidTagName("classmate and CCA mate")); // Multiple words
        assertTrue(Tag.isValidTagName("Dr.")); // Non-alphanumerics allowed
    }

}
