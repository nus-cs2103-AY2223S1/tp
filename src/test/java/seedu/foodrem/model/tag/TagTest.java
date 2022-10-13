package seedu.foodrem.model.tag;

import static seedu.foodrem.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.foodrem.logic.commands.CommandTestUtil;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Tag(CommandTestUtil.INVALID_TAG_NAME_DISALLOWED_PUNCTUATION));
        assertThrows(IllegalArgumentException.class, () -> new Tag(CommandTestUtil.INVALID_TAG_NAME_ILLEGAL_FIRST_CHAR));
        assertThrows(IllegalArgumentException.class, () -> new Tag(CommandTestUtil.INVALID_TAG_NAME_EXCEED_CHAR_LIMIT));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

}
