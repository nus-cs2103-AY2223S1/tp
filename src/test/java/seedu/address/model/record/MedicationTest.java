package seedu.address.model.record;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECORD_MEDICATION;

import org.junit.jupiter.api.Test;

public class MedicationTest {

    private final Medication validMedication = Medication.of(VALID_RECORD_MEDICATION);

    @Test
    public void equals() {
        // same values -> returns true
        Medication medicationCopy = Medication.of(VALID_RECORD_MEDICATION);
        assertTrue(validMedication.equals(medicationCopy));

        // same object -> returns true
        assertTrue(validMedication.equals(validMedication));

        // null -> returns false
        assertFalse(validMedication.equals(null));

        // different type -> returns false
        assertFalse(validMedication.equals(5));

        // different medication -> returns false
        assertFalse(validMedication.equals(Medication.of("Test")));
    }
}

