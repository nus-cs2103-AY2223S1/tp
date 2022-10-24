package seedu.uninurse.model.medication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalMedications.MEDICATION_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.MEDICATION_AMPICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class MedicationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Medication(null, null));
    }

    @Test
    public void constructor_invalidMedicationType_throwsIllegalArgumentException() {
        String invalidMedicationType = "";
        assertThrows(IllegalArgumentException.class, () ->
                new Medication(invalidMedicationType, TYPICAL_DOSAGE_AMOXICILLIN));
    }

    @Test
    public void constructor_emptyMedicationDosage_throwsIllegalArgumentException() {
        String emptyMedicationDosage = "";
        assertThrows(IllegalArgumentException.class, () ->
                new Medication(TYPICAL_MEDICATION_AMOXICILLIN, emptyMedicationDosage));
    }

    @Test
    public void constructor_invalidMedicationDosage_throwsIllegalArgumentException() {
        String invalidMedicationDosage = "+%*";
        assertThrows(IllegalArgumentException.class, () ->
                new Medication(TYPICAL_MEDICATION_AMOXICILLIN, invalidMedicationDosage));
    }


    @Test
    public void isValidMedication_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Medication.isValidMedication((String) null, (String) null));
        assertThrows(NullPointerException.class, () ->
                Medication.isValidMedication((Optional<String>) null, (Optional<String>) null));
    }

    @Test
    public void isValidMedication_validMedication_returnsTrue() {
        assertTrue(Medication.isValidMedication(TYPICAL_MEDICATION_AMOXICILLIN, TYPICAL_DOSAGE_AMOXICILLIN));
    }

    @Test
    public void isValidMedication_validMedicationType_returnsTrue() {
        // numbers in medication -> returns true
        assertTrue(Medication.isValidMedication("123", TYPICAL_DOSAGE_AMOXICILLIN));

        // special characters in medication -> returns true
        assertTrue(Medication.isValidMedication("@!#$%^&*()-=+_[];.,`~:<>?/",
                TYPICAL_DOSAGE_AMOXICILLIN));
    }

    @Test
    public void isValidMedication_invalidMedicationType_returnsFalse() {
        // empty medication -> returns false
        assertFalse(Medication.isValidMedication("", TYPICAL_DOSAGE_AMOXICILLIN));

        // blank medication -> returns false
        assertFalse(Medication.isValidMedication("  ", TYPICAL_DOSAGE_AMOXICILLIN));
    }

    @Test
    public void isValidMedication_validMedicationDosage_returnsTrue() {
        // alphanumeric characters with decimal point in dosage -> returns true
        assertTrue(Medication.isValidMedication(TYPICAL_MEDICATION_AMOXICILLIN, "abc.123"));
    }

    @Test
    public void isValidMedication_invalidMedicationDosage_returnsFalse() {
        // empty dosage -> returns false
        assertFalse(Medication.isValidMedication(TYPICAL_MEDICATION_AMOXICILLIN, ""));

        // blank dosage -> returns false
        assertFalse(Medication.isValidMedication(TYPICAL_MEDICATION_AMOXICILLIN, " "));

        // special characters dosage -> returns false
        assertFalse(Medication.isValidMedication(TYPICAL_MEDICATION_AMOXICILLIN,
                "@!#$%^&*()-=+_[];.,`~:<>?/"));
    }

    @Test
    public void isValidMedication_bothOptionalEmpty_returnsFalse() {
        assertFalse(Medication.isValidMedication(Optional.empty(), Optional.empty()));
    }

    @Test
    public void isValidMedication_bothOptionalNonEmpty_returnsTrue() {
        assertTrue(Medication.isValidMedication(Optional.of(TYPICAL_MEDICATION_AMOXICILLIN),
                Optional.of(TYPICAL_DOSAGE_AMOXICILLIN)));
    }

    @Test
    public void isValidMedication_optionalMedicationTypeValidMedicationDosage_returnsTrue() {
        // alphanumeric characters in dosage -> returns true
        assertTrue(Medication.isValidMedication(Optional.empty(), Optional.of("abc123")));

        // valid dosage -> returns true
        assertTrue(Medication.isValidMedication(Optional.empty(), Optional.of(TYPICAL_DOSAGE_AMOXICILLIN)));
    }

    @Test
    public void isValidMedication_optionalMedicationTypeInvalidMedicationDosage_returnsTrue() {
        // empty dosage -> returns false
        assertFalse(Medication.isValidMedication(Optional.empty(), Optional.of("")));

        // blank dosage -> returns false
        assertFalse(Medication.isValidMedication(Optional.empty(), Optional.of(" ")));

        // special characters dosage -> returns false
        assertFalse(Medication.isValidMedication(Optional.empty(), Optional.of("@!#$%^&*()-=+_[];.,`~:<>?/")));
    }

    @Test
    public void isValidMedication_validMedicationTypeOptionalMedicationDosage_returnsTrue() {
        // numbers in medication -> returns true
        assertTrue(Medication.isValidMedication(Optional.of("123"), Optional.empty()));

        // special characters in medication -> returns true
        assertTrue(Medication.isValidMedication(Optional.of("@!#$%^&*()-=+_[];.,`~:<>?/"), Optional.empty()));
    }

    @Test
    public void isValidMedication_invalidMedicationTypeOptionalMedicationDosage_returnsFalse() {
        // empty medication -> returns false
        assertFalse(Medication.isValidMedication(Optional.of(""), Optional.empty()));

        // blank medication -> returns false
        assertFalse(Medication.isValidMedication(Optional.of("  "), Optional.empty()));
    }

    @Test
    public void testToString() {
        String expectedMedicationString = TYPICAL_MEDICATION_AMOXICILLIN + " | "
                + TYPICAL_DOSAGE_AMOXICILLIN;
        assertEquals(MEDICATION_AMOXICILLIN.toString(), expectedMedicationString);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(MEDICATION_AMOXICILLIN, MEDICATION_AMOXICILLIN);

        // same values -> returns true
        Medication amoxicillinMedicationCopy = new Medication(TYPICAL_MEDICATION_AMOXICILLIN,
                TYPICAL_DOSAGE_AMOXICILLIN);
        assertEquals(MEDICATION_AMOXICILLIN, amoxicillinMedicationCopy);

        // different types -> returns false
        assertNotEquals(1, MEDICATION_AMOXICILLIN);

        // null -> returns false
        assertNotEquals(null, MEDICATION_AMOXICILLIN);

        // different medication -> returns false
        assertNotEquals(MEDICATION_AMOXICILLIN, MEDICATION_AMPICILLIN);
    }
}
