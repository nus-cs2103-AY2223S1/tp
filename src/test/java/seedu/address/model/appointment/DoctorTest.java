package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DoctorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Doctor(null));
    }

    @Test
    public void constructor_invalidDoctor_throwsIllegalArgumentException() {
        String invalidDoctor = "";
        assertThrows(IllegalArgumentException.class, () -> new Doctor(invalidDoctor));
    }

    @Test
    public void isValidDoctor() {
        // null doctors
        assertThrows(NullPointerException.class, () -> Doctor.isValidDoctorName(null));

        // invalid doctors
        assertFalse(Doctor.isValidDoctorName("")); // empty string
        assertFalse(Doctor.isValidDoctorName(" ")); // spaces only
        assertFalse(Doctor.isValidDoctorName("-"));
        assertFalse(Doctor.isValidDoctorName("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
        assertFalse(Doctor.isValidDoctorName("Blk 456, Den Road, #01-355"));
        assertFalse(Doctor.isValidDoctorName("Lee, HL"));

        // valid doctors
        assertTrue(Doctor.isValidDoctorName("Wong123"));
        assertTrue(Doctor.isValidDoctorName("Lee HL"));
    }

    @Test
    public void equals() {
        Doctor d1 = new Doctor("A");
        Doctor d2 = new Doctor("B");
        Doctor d3 = new Doctor("A");
        assertTrue(d1.equals(d3));
        assertTrue(d3.equals(d1));
        assertFalse(d2.equals(d1));
        assertFalse(d3.equals(null));
        assertFalse(d3.equals("A"));
    }
}
