package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AttendanceListTest {

    public static AttendanceList EMPTY_ATTENDANCE = new AttendanceList();
    public static AttendanceList TEST_ATTENDANCE = new AttendanceList("CS2040","12");
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

    @Test
    public void isValidSize() {
        // null size of list
        assertThrows(NullPointerException.class, () -> AttendanceList.isValidSize(null));

        // invalid size of list
        assertFalse(AttendanceList.isValidSize("")); // empty string
        assertFalse(AttendanceList.isValidSize(" ")); // spaces only
        assertFalse(AttendanceList.isValidSize("13")); // more than 12 numbers
        assertFalse(AttendanceList.isValidSize("phone")); // non-numeric

        // valid size
        assertTrue(AttendanceList.isValidSize("0")); // empty
        assertTrue(AttendanceList.isValidSize("12")); // max capacity of 12
    }

    @Test
    public void isEmpty() {
        assertFalse(TEST_ATTENDANCE.isEmpty());
        assertTrue(EMPTY_ATTENDANCE.isEmpty());
    }

    @Test
    public void getSize() {

    }

    @Test
    public void getAttendanceList() {
    }

    @Test
    public void getMod() {
    }

    @Test
    public void mark() {
    }
}
