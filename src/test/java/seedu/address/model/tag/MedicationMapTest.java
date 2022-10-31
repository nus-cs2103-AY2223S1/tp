package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MedicationMapTest {

    private final MedicationMap medicationMap;

    public MedicationMapTest() {
        medicationMap = new MedicationMap();
        medicationMap.add(new Medication("Paracetamol"));
        medicationMap.add(new Medication("Ibuprofen"));
    }

    @Test
    public void add_nullMedication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> medicationMap.add(null));
    }

    @Test
    public void toString_validMedicationMap_returnsString() {
        assertEquals("\nTypes of medication: 2\n"
                + "Patient count by medication:\n"
                + "   ibuprofen: 1\n"
                + "   paracetamol: 1", medicationMap.toString());
    }

    @Test
    public void toString_emptyMedicationMap_returnsString() {
        MedicationMap emptyMedicationMap = new MedicationMap();
        assertEquals("\nTypes of medication: 0\n"
                + "Patient count by medication:", emptyMedicationMap.toString());
    }

    @Test
    public void toString_medicationAdded_returnsString() {
        MedicationMap emptyMedicationMap = new MedicationMap();
        emptyMedicationMap.add(new Medication("Paracetamol"));
        assertEquals("\nTypes of medication: 1\n"
                + "Patient count by medication:\n"
                + "   paracetamol: 1", emptyMedicationMap.toString());
    }

}
