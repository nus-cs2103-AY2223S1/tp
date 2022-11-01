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
    public void add_validMedication_success() {
        Medication medication = new Medication("Aspirin");
        medicationMap.add(medication);
        assertEquals(medicationMap.size(), 3);
        // clean up
        medicationMap.remove(medication);
    }

    @Test
    public void remove_medicationNotInMap_success() {
        Medication medication = new Medication("Aspirin");
        medicationMap.remove(medication);
        assertEquals(medicationMap.size(), 2);
    }

    @Test
    public void remove_medicationInMap_success() {
        Medication medication = new Medication("Paracetamol");
        medicationMap.remove(medication);
        assertEquals(medicationMap.size(), 1);
        // clean up
        medicationMap.add(medication);
    }

    @Test
    public void setStringifiedMedicationMap_validString_success() {
        String medicationMapString = "Paracetamol /-/-/ 1 /-/-/ Ibuprofen /-/-/ 1";
        MedicationMap medicationMap = new MedicationMap();
        medicationMap.setStringifiedMedicationMap(medicationMapString);
        assertEquals(medicationMap.size(), 2);
    }

    @Test
    public void setStringifiedMedicationMap_emptyString_success() {
        String medicationMapString = "";
        MedicationMap medicationMap = new MedicationMap();
        medicationMap.setStringifiedMedicationMap(medicationMapString);
        assertEquals(medicationMap.size(), 0);
    }

    @Test
    public void toString_validMedicationMap_returnsString() {
        String expectedString = "\nTypes of medication: 2\n"
                + "Patient count by medication:\n"
                + "   ibuprofen: 1\n"
                + "   paracetamol: 1";
        assertEquals(expectedString, medicationMap.toString());
    }

    @Test
    public void toString_emptyMedicationMap_returnsString() {
        String expectedString = "\nTypes of medication: 0\n"
                + "Patient count by medication:";
        MedicationMap emptyMedicationMap = new MedicationMap();
        assertEquals(expectedString, emptyMedicationMap.toString());
    }

    @Test
    public void toString_medicationAdded_returnsString() {
        String expectedString = "\nTypes of medication: 1\n"
                + "Patient count by medication:\n"
                + "   paracetamol: 1";
        MedicationMap emptyMedicationMap = new MedicationMap();
        emptyMedicationMap.add(new Medication("Paracetamol"));
        assertEquals(expectedString, emptyMedicationMap.toString());
    }

}
