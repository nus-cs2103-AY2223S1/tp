package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

        // invalid tag
        assertFalse(Tag.isValidTagName(" ")); // spaces only
        assertFalse(Tag.isValidTagName("")); // empty string
        assertFalse(Tag.isValidTagName("UTown Residences 5th floor")); // long tag with space

        // valid tag
        assertTrue(Tag.isValidTagName("Friendly")); // alphabets only
        assertTrue(Tag.isValidTagName("60")); // numbers only
        assertTrue(Tag.isValidTagName("LT4")); // alphanumeric characters

    }

    @Test
    public void equalsTest() {
        Tag tagA = new Tag("a");
        Tag tagB = new Tag("b");
        Tag tagA2 = new Tag("a");

        // equals
        assertEquals(tagA, tagA);
        assertEquals(tagA, tagA2);
        assertEquals(tagB, tagB);

        // not equals
        assertNotEquals(tagA, tagB);
    }

    @Test
    public void toStringTest() {
        Tag tagA = new Tag("a");
        assertEquals(tagA.toString(), "[a]");

        assertNotEquals(tagA.toString(), "[\"a\"]");
    }
}
