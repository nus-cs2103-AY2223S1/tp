package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

public class JsonAdaptedTagTest {

    private static final String INVALID_TAG = "#TAGGING";
    private static final String VALID_TAG = "Warning";

    @Test
    public void toModelType_validTagDetails_returnsTag() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_TAG);
        assertEquals(new Tag(VALID_TAG), tag.toModelType());
    }

    @Test
    public void toModelType_invalidTagName_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(INVALID_TAG);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }
}
