package seedu.foodrem.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodrem.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.CommandTestUtil;

public class TagTest {
    private static final String TAGNAME = "TagTest";
    private final Tag initialTag = new Tag(TAGNAME);

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    void constructor_invalidTagName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Tag(
                CommandTestUtil.INVALID_TAG_NAME_DISALLOWED_PUNCTUATION));
        assertThrows(IllegalArgumentException.class, () -> new Tag(
                CommandTestUtil.INVALID_TAG_NAME_EXCEED_CHAR_LIMIT));
    }

    @Test
    void testEquals_equal() {
        final Tag tagCopy = new Tag(TAGNAME);
        assertEquals(tagCopy, initialTag);
    }

    @Test
    void testEquals_notEqual() {
        final Tag tagCopy = new Tag(TAGNAME.concat("different"));
        assertNotEquals(tagCopy, initialTag);
    }
}
