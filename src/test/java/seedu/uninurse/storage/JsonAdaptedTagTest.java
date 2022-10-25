package seedu.uninurse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalTags.TAG_ELDERLY;
import static seedu.uninurse.testutil.TypicalTags.TYPICAL_TAG_ELDERLY;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.tag.Tag;

public class JsonAdaptedTagTest {

    private static final JsonAdaptedTag JSON_TAG = new JsonAdaptedTag(TYPICAL_TAG_ELDERLY);
    private static final String INVALID_CONDITION = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedTag((String) null));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedTag((Tag) null));
    }

    @Test
    public void getTag() {
        assertEquals(JSON_TAG.getTagName(), TYPICAL_TAG_ELDERLY);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedTag condition = new JsonAdaptedTag(INVALID_CONDITION);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, condition::toModelType);
    }

    @Test
    public void toModelType_validTag_success() throws IllegalValueException {
        JsonAdaptedTag condition = new JsonAdaptedTag(TYPICAL_TAG_ELDERLY);
        assertEquals(TAG_ELDERLY, condition.toModelType());
    }
}
