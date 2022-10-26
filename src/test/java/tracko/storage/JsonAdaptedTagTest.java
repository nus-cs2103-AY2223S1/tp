package tracko.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tracko.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import tracko.commons.exceptions.IllegalValueException;
import tracko.model.tag.Tag;

public class JsonAdaptedTagTest {
    private static final String INVALID_TAG_NAME = "@cocomonster";

    private static final String INVALID_TAG_NAME_WITH_SPACE = "coco monster";

    private static final String VALID_TAG_NAME = "kitchen";

    @Test
    public void toModelType_validTagDetails_returnsTag() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_TAG_NAME);
        JsonAdaptedTag tagFromTag = new JsonAdaptedTag(new Tag(VALID_TAG_NAME));

        assertEquals(new Tag(VALID_TAG_NAME), tag.toModelType());

        assertEquals(new Tag(VALID_TAG_NAME), tagFromTag.toModelType());
    }

    @Test
    public void validTagDetails_getTag() {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_TAG_NAME);

        assertEquals(VALID_TAG_NAME, tag.getTagName());

    }

    @Test
    public void toModelType_invalidTagName_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(INVALID_TAG_NAME);
        JsonAdaptedTag tagWithSpace =
                new JsonAdaptedTag(INVALID_TAG_NAME_WITH_SPACE);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> tag.toModelType());

        assertThrows(IllegalValueException.class, expectedMessage, () -> tagWithSpace.toModelType());
    }
}
