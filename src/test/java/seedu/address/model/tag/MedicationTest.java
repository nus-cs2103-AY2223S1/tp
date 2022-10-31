package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        Medication medication = new Medication("Paracetamol");
        Medication medication2 = new Medication("Paracetamol");
        Medication medication3 = new Medication("Ibuprofen");

        assertEquals(medication, medication); // same object
        assertEquals(medication, medication2); // same name
        assertNotEquals(medication, medication3); // different name
        assertNotEquals(medication, 5); // different type
    }

}
