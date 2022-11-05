package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.storage.JsonAdaptedAbstractAttribute.CORRUPTED_FIELD_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedAbstractAttribute.isSaveableDataFormat;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.DisplayItemUtil.INVALID_SAVE_ATTRIBUTE;
import static seedu.address.testutil.TypicalAttributes.PHONE;

import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedAbstractAttributeTest {

    private static final Map<String, Object> INVALID_ATTRIBUTE = INVALID_SAVE_ATTRIBUTE;

    private static final Map<String, Object> VALID_ATTRIBUTE = PHONE.toSaveableData();

    @Test
    public void toModelType_validAttributeDetails_returnsAttribute() throws Exception {

        JsonAdaptedAbstractAttribute attribute = new JsonAdaptedAbstractAttribute(VALID_ATTRIBUTE);
        assertEquals(PHONE, attribute.toModelType());
    }

    @Test
    public void toModelType_invalidAttributeDetails_throwsIllegalValueException() {
        JsonAdaptedAbstractAttribute attribute = new JsonAdaptedAbstractAttribute(INVALID_ATTRIBUTE);
        assertThrows(IllegalValueException.class, CORRUPTED_FIELD_MESSAGE_FORMAT, attribute::toModelType);
    }

    @Test
    public void toModelType_nullAttributeDetails_throwsIllegalValueException() {
        JsonAdaptedAbstractAttribute attribute = new JsonAdaptedAbstractAttribute((Map<String, Object>) null);
        assertThrows(IllegalValueException.class, CORRUPTED_FIELD_MESSAGE_FORMAT, attribute::toModelType);
    }

    @Test
    public void isSaveableDataFormat_validAttributeDataFormat_returnsTrue() {
        assertTrue(isSaveableDataFormat(VALID_ATTRIBUTE));
    }

    @Test
    public void isSaveableDataFormat_invalidAttributeDataFormat_returnsFalse() {
        assertFalse(isSaveableDataFormat(INVALID_ATTRIBUTE));
    }
}
