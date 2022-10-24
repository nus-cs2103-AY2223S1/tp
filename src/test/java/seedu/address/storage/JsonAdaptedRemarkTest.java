package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRemark.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemark.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.remark.Text;

public class JsonAdaptedRemarkTest {
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TEXT = BENSON.getText().getValue();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validRemarkDetails_returnsRemark() throws Exception {
        JsonAdaptedRemark remark = new JsonAdaptedRemark(BENSON);
        assertEquals(BENSON, remark.toModelType());
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRemark remark =
                new JsonAdaptedRemark(VALID_TEXT, invalidTags);
        assertThrows(IllegalValueException.class, remark::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedRemark remark =
                new JsonAdaptedRemark(INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Text.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedRemark remark = new JsonAdaptedRemark(null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Text.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

}
