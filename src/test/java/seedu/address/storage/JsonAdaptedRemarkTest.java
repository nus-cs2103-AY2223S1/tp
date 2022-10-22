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
import seedu.address.model.remark.RemarkAddress;
import seedu.address.model.remark.RemarkName;

public class JsonAdaptedRemarkTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validRemarkDetails_returnsRemark() throws Exception {
        JsonAdaptedRemark remark = new JsonAdaptedRemark(BENSON);
        assertEquals(BENSON, remark.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRemark remark =
                new JsonAdaptedRemark(INVALID_NAME, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = RemarkName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedRemark remark = new JsonAdaptedRemark(null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RemarkName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRemark remark =
                new JsonAdaptedRemark(VALID_NAME, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, remark::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedRemark remark =
                new JsonAdaptedRemark(VALID_NAME, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = RemarkAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedRemark remark = new JsonAdaptedRemark(VALID_NAME, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RemarkAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

}
