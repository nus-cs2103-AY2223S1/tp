package seedu.uninurse.model.medication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.uninurse.testutil.TypicalMedications;

public class MedicationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Medication(null, null));
    }

    @Test
    public void constructor_invalidMedicationType_throwsIllegalArgumentException() {
        String invalidMedicationType = "";
        assertThrows(IllegalArgumentException.class, () ->
                new Medication(invalidMedicationType, TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN));
    }

    @Test
    public void constructor_emptyMedicationDosage_throwsIllegalArgumentException() {
        String emptyMedicationDosage = "";
        assertThrows(IllegalArgumentException.class, () ->
                new Medication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN, emptyMedicationDosage));
    }

    @Test
    public void constructor_invalidMedicationDosage_throwsIllegalArgumentException() {
        String invalidMedicationDosage = "+%*";
        assertThrows(IllegalArgumentException.class, () ->
                new Medication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN, invalidMedicationDosage));
    }


    @Test
    public void isValidMedication_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Medication.isValidMedication(null, null));
    }

    @Test
    public void isValidMedication_validMedicationType_returnsTrue() {
        // numbers in medication -> returns true
        assertTrue(Medication.isValidMedication("123", TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN));

        // special characters in medication -> returns true
        assertTrue(Medication.isValidMedication("@!#$%^&*()-=+_[];.,`~:<>?/",
                TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN));

        // valid medication -> returns true
        assertTrue(Medication.isValidMedication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN,
                TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN));
    }

    @Test
    public void isValidMedication_invalidMedicationType_returnsFalse() {
        // empty medication -> returns false
        assertFalse(Medication.isValidMedication("", TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN));

        // blank medication -> returns false
        assertFalse(Medication.isValidMedication("  ", TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN));
    }

    @Test
    public void isValidMedication_validMedicationDosage_returnsTrue() {
        // alphanumeric characters in dosage -> returns true
        assertTrue(Medication.isValidMedication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN, "abc123"));

        // valid medication -> returns true
        assertTrue(Medication.isValidMedication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN,
                TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN));
    }

    @Test
    public void isValidMedication_invalidMedicationDosage_returnsFalse() {
        // empty dosage -> returns false
        assertFalse(Medication.isValidMedication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN, ""));

        // blank dosage -> returns false
        assertFalse(Medication.isValidMedication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN, " "));

        // special characters dosage -> returns false
        assertFalse(Medication.isValidMedication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN,
                "@!#$%^&*()-=+_[];.,`~:<>?/"));
    }

    @Test
    public void testToString() {
        String expectedMedicationString = TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN + " \\| "
                + TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN;
        assertEquals(TypicalMedications.MEDICATION_AMOXICILLIN.toString(), expectedMedicationString);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(TypicalMedications.MEDICATION_AMOXICILLIN, TypicalMedications.MEDICATION_AMOXICILLIN);

        // same values -> returns true
        Medication amoxicillinMedicationCopy = new Medication(TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN,
                TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN);
        assertEquals(TypicalMedications.MEDICATION_AMOXICILLIN, amoxicillinMedicationCopy);

        // different types -> returns false
        assertNotEquals(1, TypicalMedications.MEDICATION_AMOXICILLIN);

        // null -> returns false
        assertNotEquals(null, TypicalMedications.MEDICATION_AMOXICILLIN);

        // different medication -> returns false
        assertNotEquals(TypicalMedications.MEDICATION_AMOXICILLIN, TypicalMedications.MEDICATION_AMPICILLIN);
    }
}
