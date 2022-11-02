package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedRemark.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemark.GOOD_SELLER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.remark.Text;

public class JsonAdaptedRemarkTest {
    private static final String INVALID_TEXT = " ";

    private static final String VALID_TEXT = GOOD_SELLER.getText().getValue();

    @Test
    public void toModelType_validRemarkDetails_returnsRemark() throws Exception {
        JsonAdaptedRemark remark = new JsonAdaptedRemark(GOOD_SELLER);
        assertEquals(GOOD_SELLER, remark.toModelType());
    }

    @Test
    public void toModelType_invalidText_throwsIllegalValueException() {
        JsonAdaptedRemark remark =
                new JsonAdaptedRemark(INVALID_TEXT);
        String expectedMessage = Text.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

    @Test
    public void toModelType_nullText_throwsIllegalValueException() {
        JsonAdaptedRemark remark = new JsonAdaptedRemark((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Text.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

}
