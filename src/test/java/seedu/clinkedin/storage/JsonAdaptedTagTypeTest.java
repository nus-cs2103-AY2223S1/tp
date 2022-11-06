package seedu.clinkedin.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.parser.Prefix;
import seedu.clinkedin.model.tag.TagType;

public class JsonAdaptedTagTypeTest {
    private static final String VALID_TAG_TYPE = "Grade";
    private static final String VALID_PREFIX = "g";
    private static final String INVALID_TAG_TYPE = "UI-UX";

    @Test
    public void toModelType_validTag_returnsLink() throws IllegalValueException {
        TagType tagType = new TagType(VALID_TAG_TYPE, new Prefix(VALID_PREFIX + "/"));
        JsonAdaptedTagType jsonAdaptedTagType = new JsonAdaptedTagType(tagType);
        assertEquals(tagType, jsonAdaptedTagType.toModelType());
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedTagType jsonAdaptedTagType = new JsonAdaptedTagType(INVALID_TAG_TYPE, VALID_PREFIX);
        String expectedMessage = TagType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedTagType::toModelType);
    }
}
