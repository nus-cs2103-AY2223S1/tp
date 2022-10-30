package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Medication;


class SampleDataUtilTest {

    @Test
    public void getMedicationSet_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SampleDataUtil.getMedicationSet((String) null));
    }

    @Test
    public void getMedicationSet_emptyString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> SampleDataUtil.getMedicationSet(""));
    }

    @Test
    public void getMedicationSet_validString_returnsMedicationSet() {
        assertTrue(SampleDataUtil.getMedicationSet("Paracetamol").contains(new Medication("Paracetamol")));
    }

}
