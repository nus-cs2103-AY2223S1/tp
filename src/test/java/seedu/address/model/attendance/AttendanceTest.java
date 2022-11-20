package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class AttendanceTest {
    @Test
    public void equals() {
        Attendance attendance = new Attendance("1");

        // same object -> returns true
        assertTrue(attendance.equals(attendance));

        // same values -> returns true
        Attendance attendanceCopy = new Attendance(attendance.value);
        assertTrue(attendance.equals(attendanceCopy));

        // different types -> returns false
        assertFalse(attendance.equals(50));

        // null -> returns false
        assertFalse(attendance.equals(null));

        // different attendance -> returns false
        Attendance differentAttendance = new Attendance("0");
        assertFalse(attendance.equals(differentAttendance));
    }

    @Test
    public void isValidMark() {
        // null mark
        assertThrows(NullPointerException.class, () -> Attendance.isValidMark(null));

        // invalid marks
        assertFalse(Attendance.isValidMark("")); // empty string
        assertFalse(Attendance.isValidMark(" ")); // spaces only
        assertFalse(Attendance.isValidMark("2")); // number greater than 1
        assertFalse(Attendance.isValidMark("phone")); // non-numeric

        assertTrue(Attendance.isValidMark("0")); // valid inputs 0 and 1
        assertTrue(Attendance.isValidMark("1")); // valid inputs 0 and 1
    }

    @Test
    public void constructor_invalidAttendance_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Attendance("3"));
    }
    @Test
    public void constructor_nullAttendance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }
}
