package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AttendanceListTest {

    private static AttendanceList emptyAttendance = new AttendanceList();
    private static AttendanceList testAttendance = new AttendanceList("CS2040", "12");
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
        assertFalse(testAttendance.isEmpty());
        assertTrue(emptyAttendance.isEmpty());
    }

    @Test
    public void getSize() {
        assertEquals(emptyAttendance.getSize(), 0);
        assertEquals(testAttendance.getSize(), 12);
    }

    @Test
    public void getAttendanceList() {
        assertEquals(new AttendanceList().getAttendanceList(), emptyAttendance.getAttendanceList());
    }

    @Test
    public void getMod() {
        assertEquals(emptyAttendance.getMod(), "NA");
        assertEquals(testAttendance.getMod(), "CS2040");
    }

    @Test
    public void mark() {
        testAttendance.mark("1", new Attendance("1"));
        assertEquals(testAttendance.toString(), "(CS2040)[1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
    }
}
