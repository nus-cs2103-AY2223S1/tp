package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.attendance.Attendance;

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
}
