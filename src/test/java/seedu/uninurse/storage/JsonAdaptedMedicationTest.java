package seedu.uninurse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalMedications.MEDICATION_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_DOSAGE_AMOXICILLIN;
import static seedu.uninurse.testutil.TypicalMedications.TYPICAL_MEDICATION_AMOXICILLIN;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.medication.Medication;

public class JsonAdaptedMedicationTest {

    private static final JsonAdaptedMedication medication = new JsonAdaptedMedication(
            TYPICAL_MEDICATION_AMOXICILLIN, TYPICAL_DOSAGE_AMOXICILLIN);
    private static final String INVALID_MEDICATION = " ";
    private static final String INVALID_DOSAGE = "+%*";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedMedication((String) null, (String) null));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedMedication((Medication) null));
    }

    @Test
    public void toModelType_invalidMedicationType_throwsIllegalValueException() {
        JsonAdaptedMedication medication = new JsonAdaptedMedication(INVALID_MEDICATION, TYPICAL_DOSAGE_AMOXICILLIN);
        String expectedMessage = Medication.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, medication::toModelType);
    }

    @Test
    public void toModelType_invalidMedicationDosage_throwsIllegalValueException() {
        JsonAdaptedMedication medication = new JsonAdaptedMedication(TYPICAL_MEDICATION_AMOXICILLIN, INVALID_DOSAGE);
        String expectedMessage = Medication.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, medication::toModelType);
    }

    @Test
    public void toModelType_validMedication_success() throws IllegalValueException {
        JsonAdaptedMedication medication = new JsonAdaptedMedication(
                TYPICAL_MEDICATION_AMOXICILLIN, TYPICAL_DOSAGE_AMOXICILLIN);
        assertEquals(MEDICATION_AMOXICILLIN, medication.toModelType());
    }
}
