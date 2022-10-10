package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedicalTestTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalTest(null));
    }

    @Test
    public void constructor_invalidMedicalTest_throwsIllegalArgumentException() {
        String invalidMedicalTest = "";
        assertThrows(IllegalArgumentException.class, () -> new MedicalTest(invalidMedicalTest));
    }

    @Test
    public void isValidMedicalTest() {
        // null medical tests
        assertThrows(NullPointerException.class, () -> MedicalTest.isValidMedicalTest(null));

        // invalid medical tests
        assertFalse(MedicalTest.isValidMedicalTest("")); // empty string
        assertFalse(MedicalTest.isValidMedicalTest(" ")); // spaces only

        // valid medical tests
        assertTrue(MedicalTest.isValidMedicalTest("X-ray"));
        assertTrue(MedicalTest.isValidMedicalTest("CT followed by X-ray"));
    }

    @Test
    public void equals() {
        MedicalTest t1 = new MedicalTest("A");
        MedicalTest t2 = new MedicalTest("B");
        MedicalTest t3 = new MedicalTest("A");
        assertTrue(t1.equals(t3));
        assertTrue(t3.equals(t1));
        assertFalse(t2.equals(t1));
        assertFalse(t3.equals(null));
        assertFalse(t3.equals("A"));
    }
}
