package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AttendanceListTest {

    @Test
    public void equals() {
        AttendanceList attendanceList = new AttendanceList("CS2040", "10");

        // same object -> returns true
        assertTrue(attendanceList.equals(attendanceList));

        // same values -> returns true
        AttendanceList attendanceCopy = new AttendanceList(attendanceList.getMod(), attendanceList.getAttendanceList());
        assertTrue(attendanceList.equals(attendanceCopy));

        // different types -> returns false
        assertFalse(attendanceList.equals(50));

        // null -> returns false
        assertFalse(attendanceList.equals(null));

        // different attendance -> returns false
        AttendanceList differentAttendanceList = new AttendanceList();
        assertFalse(attendanceList.equals(differentAttendanceList));
    }

}
