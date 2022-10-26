package seedu.uninurse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalRemarks.REMARK_MEDICAL_ALLERGY;
import static seedu.uninurse.testutil.TypicalRemarks.TYPICAL_REMARK_MEDICAL_ALLERGY;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.remark.Remark;

public class JsonAdaptedRemarkTest {

    private static final JsonAdaptedRemark JSON_REMARK = new JsonAdaptedRemark(TYPICAL_REMARK_MEDICAL_ALLERGY);
    private static final String INVALID_REMARK = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedRemark((String) null));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedRemark((Remark) null));
    }

    @Test
    public void getRemark() {
        assertEquals(JSON_REMARK.getRemark(), TYPICAL_REMARK_MEDICAL_ALLERGY);
    }

    @Test
    public void toModelType_invalidRemark_throwsIllegalValueException() {
        JsonAdaptedRemark remark = new JsonAdaptedRemark(INVALID_REMARK);
        String expectedMessage = Remark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, remark::toModelType);
    }

    @Test
    public void toModelType_validRemark_success() throws IllegalValueException {
        JsonAdaptedRemark remark = new JsonAdaptedRemark(TYPICAL_REMARK_MEDICAL_ALLERGY);
        assertEquals(REMARK_MEDICAL_ALLERGY, remark.toModelType());
    }
}
