package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null, null));
    }

    @Test
    public void constructor_invalidClassName_throwsIllegalArgumentException() {
        String invalidClassName = "!";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidClassName, Boolean.TRUE));
    }

    @Test
    public void isValidClassName() {
        // null class name
        assertThrows(NullPointerException.class, () -> Attendance.isValidClassName(null));

        // invalid class names
        assertFalse(Attendance.isValidClassName("")); // empty string
        assertFalse(Attendance.isValidClassName("asd!")); // contains forbidden symbols
        assertFalse(Attendance.isValidClassName("aasd*")); // contains forbidden symbols
        assertFalse(Attendance.isValidClassName("vafe?")); // contains forbidden symbols

        // valid class names
        assertTrue(Attendance.isValidClassName("T01"));
        assertTrue(Attendance.isValidClassName("T01_2-"));
        assertTrue(Attendance.isValidClassName("L3-4"));
    }
}
