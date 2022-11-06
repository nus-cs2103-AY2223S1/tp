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
        String invalidAttendance = "";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendance));
    }

    @Test
    public void isValidTelegram() {
        // null attendance
        assertThrows(NullPointerException.class, () -> Attendance.isValidAttendance(null));

        // invalid attendance
        assertFalse(Attendance.isValidAttendance("")); // empty string
        assertFalse(Attendance.isValidAttendance(" ")); // spaces only
        assertFalse(Attendance.isValidAttendance("^")); // only non-alphanumeric characters
        assertFalse(Attendance.isValidAttendance("a")); // contains alphanumeric characters
        assertFalse(Attendance.isValidAttendance("-1")); // negative number

        // valid attendance
        assertTrue(Attendance.isValidAttendance("1")); // number only
    }
}
