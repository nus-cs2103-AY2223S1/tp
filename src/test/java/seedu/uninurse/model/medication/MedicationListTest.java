package seedu.uninurse.model.medication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.uninurse.model.medication.exceptions.DuplicateMedicationException;
import seedu.uninurse.model.medication.exceptions.MedicationNotFoundException;
import seedu.uninurse.model.medication.exceptions.UnmodifiedMedicationException;
import seedu.uninurse.testutil.TypicalMedications;

public class MedicationListTest {
    private final MedicationList medicationList = new MedicationList();

    @Test
    public void add_nullMedication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medicationList.add(null));
    }

    @Test
    public void add_duplicateMedication_throwsDuplicateMedicationException() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertThrows(DuplicateMedicationException.class, () -> updatedMedicationList
                .add(TypicalMedications.MEDICATION_AMOXICILLIN));
    }

    @Test
    public void delete_emptyList_throwsMedicationNotFoundException() {
        assertThrows(MedicationNotFoundException.class, () -> medicationList.delete(0));
    }

    @Test
    public void delete_invalidIndex_throwsMedicationNotFoundException() {
        assertThrows(MedicationNotFoundException.class, () -> medicationList.delete(-1));
    }

    @Test
    public void delete_indexOutOfbounds_throwsMedicationNotFoundException() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertThrows(MedicationNotFoundException.class, () -> updatedMedicationList.delete(1));
    }

    @Test
    public void edit_emptyList_throwsMedicationNotFoundException() {
        assertThrows(MedicationNotFoundException.class, () -> medicationList
                .edit(1, TypicalMedications.MEDICATION_AMOXICILLIN));
    }

    @Test
    public void edit_invalidIndex_throwsMedicationNotFoundException() {
        assertThrows(MedicationNotFoundException.class, () -> medicationList
                .edit(-1, TypicalMedications.MEDICATION_AMOXICILLIN));
    }

    @Test
    public void edit_indexOutOfbounds_throwsMedicationNotFoundException() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertThrows(MedicationNotFoundException.class, () -> updatedMedicationList
                .edit(1, TypicalMedications.MEDICATION_AMOXICILLIN));
    }

    @Test
    public void edit_sameMedication_throwsUnmodifiedMedicationException() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertThrows(UnmodifiedMedicationException.class, () -> updatedMedicationList
                .edit(0, TypicalMedications.MEDICATION_AMOXICILLIN));
    }

    @Test
    public void edit_differentMedication_success() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        updatedMedicationList = updatedMedicationList.edit(0, TypicalMedications.MEDICATION_AMPICILLIN);

        MedicationList expectedMedicationList = new MedicationList();
        expectedMedicationList = expectedMedicationList.add(TypicalMedications.MEDICATION_AMPICILLIN);
        assertEquals(updatedMedicationList, expectedMedicationList);
    }

    @Test
    public void get_emptyList_throwsMedicationNotFoundException() {
        assertThrows(MedicationNotFoundException.class, () -> medicationList.get(0));
    }

    @Test
    public void get_invalidIndex_throwsMedicationNotFoundException() {
        assertThrows(MedicationNotFoundException.class, () -> medicationList.get(-1));
    }

    @Test
    public void get_indexOutOfbounds_throwsMedicationNotFoundException() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertThrows(MedicationNotFoundException.class, () -> updatedMedicationList.get(1));
    }

    @Test
    public void get_validIndex_success() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertEquals(updatedMedicationList.get(0), TypicalMedications.MEDICATION_AMOXICILLIN);
    }

    @Test
    public void size_emptyList_returnsZero() {
        assertEquals(medicationList.size(), 0);
    }

    @Test
    public void size_nonEmptyList_returnsNonZero() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertNotEquals(updatedMedicationList.size(), 0);
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(medicationList.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_returnsFalse() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertFalse(updatedMedicationList.isEmpty());
    }

    @Test
    public void getInternalList_modifyList_throwsUnsupportedOperationException() {
        MedicationList updatedMedicationList = medicationList.add(TypicalMedications.MEDICATION_AMOXICILLIN);
        assertThrows(UnsupportedOperationException.class, ()
            -> updatedMedicationList.getInternalList().remove(0));
    }
}
