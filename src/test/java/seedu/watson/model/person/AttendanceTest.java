package seedu.watson.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void isValidAttendanceInput() {
        // null name
        assertThrows(NullPointerException.class, () -> Attendance.isValidAttendance(null));

        // invalid commands
        assertFalse(Attendance.isValidAttendance("13/2/2022 1")); // no prefixes
        assertFalse(Attendance.isValidAttendance("date/13/2/2022 1")); // no "attendance/" prefix
        assertFalse(Attendance.isValidAttendance("attendance/13/2/2022 date/1")); // swapped prefixes
        assertFalse(Attendance.isValidAttendance("date/13/2 attendance/1")); // invalid date

        // valid commands
        assertTrue(Attendance.isValidAttendance("")); // empty string is valid
        assertTrue(Attendance.isValidAttendance("date/13/02/2022 attendance/1")); // valid date and attendance
        assertTrue(Attendance.isValidAttendance("date/13-02-2022 attendance/1")); // date with different format
    }
}
