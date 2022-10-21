package seedu.uninurse.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.uninurse.testutil.TypicalRemarks;

public class RemarkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemark_throwsIllegalArgumentException() {
        String invalidRemark = "";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemark));
    }

    @Test
    public void isValidRemark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));
    }

    @Test
    public void isValidRemark_validRemark_returnsTrue() {
        // numbers in remark -> returns true
        assertTrue(Remark.isValidRemark("123"));

        // special characters in remark -> returns true
        assertTrue(Remark.isValidRemark("@!#$%^&*()-=+_[];.,`~:<>?/"));

        // valid remark -> returns true
        assertTrue(Remark.isValidRemark(TypicalRemarks.TYPICAL_MEDICAL_ALLERGY));
    }

    @Test
    public void isValidRemark_invalidRemark_returnsFalse() {
        // empty remark -> returns false
        assertFalse(Remark.isValidRemark(""));

        // blank remark -> returns false
        assertFalse(Remark.isValidRemark("  "));
    }

    @Test
    public void testToString() {
        String expectedRemarkString = TypicalRemarks.TYPICAL_MEDICAL_ALLERGY;
        assertEquals(TypicalRemarks.REMARK_MEDICAL_ALLERGY.toString(), expectedRemarkString);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(TypicalRemarks.REMARK_MEDICAL_ALLERGY, TypicalRemarks.REMARK_MEDICAL_ALLERGY);

        // same values -> returns true
        Remark medicalAllergyRemarkCopy = new Remark(TypicalRemarks.TYPICAL_MEDICAL_ALLERGY);
        assertEquals(TypicalRemarks.REMARK_MEDICAL_ALLERGY, medicalAllergyRemarkCopy);

        // different types -> returns false
        assertNotEquals(1, TypicalRemarks.REMARK_MEDICAL_ALLERGY);

        // null -> returns false
        assertNotEquals(null, TypicalRemarks.REMARK_MEDICAL_ALLERGY);

        // different remark -> returns false
        assertNotEquals(TypicalRemarks.REMARK_MEDICAL_ALLERGY, TypicalRemarks.REMARK_WHEELCHAIR);
    }
}
