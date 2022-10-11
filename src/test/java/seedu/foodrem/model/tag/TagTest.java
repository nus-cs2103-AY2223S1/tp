package seedu.foodrem.model.tag;

import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_DESC_TAG_NAME_ILLEGAL_FIRST_CHAR;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_TAG_NAME_DISALLOWED_PUNCTUATION;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_TAG_NAME_EXCEED_CHAR_LIMIT;
import static seedu.foodrem.logic.commands.CommandTestUtil.INVALID_TAG_NAME_ILLEGAL_FIRST_CHAR;
import static seedu.foodrem.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Tag(INVALID_TAG_NAME_DISALLOWED_PUNCTUATION));
        assertThrows(IllegalArgumentException.class, () -> new Tag(INVALID_TAG_NAME_ILLEGAL_FIRST_CHAR));
        assertThrows(IllegalArgumentException.class, () -> new Tag(INVALID_TAG_NAME_EXCEED_CHAR_LIMIT));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

}
