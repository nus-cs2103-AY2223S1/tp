package seedu.clinkedin.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.model.tag.Tag;

public class JsonAdaptedTagTest {
    private static final String VALID_TAG = "Java";
    private static final String INVALID_TAG = "UI-UX";

    @Test
    public void toModelType_validTag_returnsLink() throws IllegalValueException {
        Tag tag = new Tag(VALID_TAG);
        JsonAdaptedTag jsonAdaptedTag = new JsonAdaptedTag(tag);
        assertEquals(tag, jsonAdaptedTag.toModelType());
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedTag jsonAdaptedTag = new JsonAdaptedTag(INVALID_TAG);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedTag::toModelType);
    }
}
