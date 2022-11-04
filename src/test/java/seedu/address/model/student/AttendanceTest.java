package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }

    @Test
    public void constructor_invalidAttendance_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Attendance(""));
        assertThrows(IllegalArgumentException.class, () -> new Attendance(" "));
    }

    @Test
    public void isValidEmail() {
        // null attendance
        assertThrows(NullPointerException.class, () -> Attendance.isValidAttendance(null));

        // invalid attendance
        assertFalse(Attendance.isValidAttendance("")); // empty string
        assertFalse(Attendance.isValidAttendance(" ")); // spaces only
        assertFalse(Attendance.isValidAttendance("string")); // any string
        assertFalse(Attendance.isValidAttendance("1.2")); // float
        assertFalse(Attendance.isValidAttendance("5.0")); // float
        assertFalse(Attendance.isValidAttendance("-2")); // negative integer

        // valid attendance
        assertTrue(Attendance.isValidAttendance("1000000000")); // big integer
        assertTrue(Attendance.isValidAttendance("0")); // zero
    }
}
