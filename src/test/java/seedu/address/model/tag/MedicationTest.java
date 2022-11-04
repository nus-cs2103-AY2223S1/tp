package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Medication(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Medication(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Medication.isValidMedicationName(null));
        
    }

    @Test
    public void equals() {
        Medication medication = new Medication("paracetamol");

        // same object -> returns true
        assertTrue(medication.equals(medication));

        // same values -> returns true
        Medication medicationCopy = new Medication("paracetamol");
        assertTrue(medication.equals(medicationCopy));

        // different types -> returns false
        assertFalse(medication.equals(1));
        assertFalse(medication.equals("paracetamol"));

        // null -> returns false
        assertFalse(medication.equals(null));

        // different medication name -> returns false
        Medication differentMedication = new Medication("ibuprofen");
        assertFalse(medication.equals(differentMedication));
    }

}
