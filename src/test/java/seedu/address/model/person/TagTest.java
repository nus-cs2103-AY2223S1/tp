package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    public static final Tag VALID_TEST_TAG = new Tag("test");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void isValidTagName() {

        // null Tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));

        // Invalid Tag names
        assertFalse(Tag.isValidTagName("friend!")); // non-alphanumeric characters
        assertFalse(Tag.isValidTagName("good teammate")); // no spaces in tag names

        // Valid Tag names
        assertTrue(Tag.isValidTagName("friend"));
        assertTrue(Tag.isValidTagName("123"));
    }

    @Test
    public void isEqualTagName() {
        Tag testTag = new Tag("test");
        assertTrue(VALID_TEST_TAG.equals(testTag));
    }
}
