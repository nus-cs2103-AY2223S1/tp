package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

public class JsonAdaptedTagTest {
    private static final String INVALID_TAG = "#friend";
    private static final String MULTI_WORD_TAG = "best friend";

    private static final String VALID_TAG = CS2103T.getName();
    private static final int VALID_COUNT = CS2103T.getCount();

    @Test
    public void toModelType_validTagDetails_returnsTag() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(CS2103T);
        assertEquals(CS2103T, tag.toModelType());
    }

    @Test
    public void toModelType_invalidTagDetails_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(INVALID_TAG, VALID_COUNT);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_multiWordTag_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(MULTI_WORD_TAG, VALID_COUNT);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }
}
