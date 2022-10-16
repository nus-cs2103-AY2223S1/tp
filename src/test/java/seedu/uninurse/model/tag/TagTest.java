package seedu.uninurse.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;

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
    public void isValidTagName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isValidTagName_validTagName_returnsTrue() {
        // alphanumeric
        assertTrue(Tag.isValidTagName("elderly"));
        assertTrue(Tag.isValidTagName("123"));
    }

    @Test
    public void isValidTagName_invalidTagName_returnsFalse() {
        // empty tag name
        assertFalse(Tag.isValidTagName(""));

        // blank tag name
        assertFalse(Tag.isValidTagName(" "));

        // special characters
        assertFalse(Tag.isValidTagName("@!#$%^&*()-=+_[];.,`~:<>?/"));

        // multiple words
        assertFalse(Tag.isValidTagName("high risk"));
    }
}
