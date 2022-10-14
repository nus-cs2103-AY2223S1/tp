package seedu.address.model.person;

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
    public void isValidAttendance() {
        // null name
        assertThrows(NullPointerException.class, () -> Attendance.isValidAttendance(null));

        // invalid attendance
        assertFalse(Attendance.isValidAttendance("")); // empty string
        assertFalse(Attendance.isValidAttendance(" ")); // spaces only
        assertFalse(Attendance.isValidAttendance("2022-0x-05")); // contains alphabetic character
        assertFalse(Attendance.isValidAttendance("2022-0*-07")); // contains non-alphanumeric characters

        // valid attendance
        assertTrue(Attendance.isValidAttendance("2022-08-08")); // valid yyyy-MM-dd format.
        assertTrue(Attendance.isValidAttendance("2021-07-12")); // valid yyyy-MM-dd format.
        assertTrue(Attendance.isValidAttendance("2021-08-27")); // valid yyyy-MM-dd format.
    }
}
