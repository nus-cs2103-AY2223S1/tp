package seedu.uninurse.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalTags.TAG_ELDERLY;
import static seedu.uninurse.testutil.TypicalTags.TAG_NURSING_HOME;
import static seedu.uninurse.testutil.TypicalTags.TYPICAL_TAG_ELDERLY;

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

        // non-trailing and non-leading whitespaces
        assertTrue(Tag.isValidTagName("high risk"));

        // special characters in condition -> returns true
        assertTrue(Tag.isValidTagName("@!#$%^&*()-=+_[];.,`~:<>?/"));
    }

    @Test
    public void isValidTagName_invalidTagName_returnsFalse() {
        // empty tag name -> returns false
        assertFalse(Tag.isValidTagName(""));

        // blank tag name -> returns false
        assertFalse(Tag.isValidTagName("  "));
    }

    @Test
    public void testToString() {
        assertEquals(TAG_ELDERLY.toString(), "[" + TYPICAL_TAG_ELDERLY + "]");
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(TAG_ELDERLY, TAG_ELDERLY);

        // same values -> returns true
        Tag elderlyTagCopy = new Tag(TYPICAL_TAG_ELDERLY);
        assertEquals(TAG_ELDERLY, elderlyTagCopy);

        // different types -> returns false
        assertNotEquals(1, TAG_ELDERLY);

        // null -> returns false
        assertNotEquals(null, TAG_ELDERLY);

        // different tag names -> returns false
        assertNotEquals(TAG_ELDERLY, TAG_NURSING_HOME);
    }
}
