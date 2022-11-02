package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    private static final String ATTENDANCE_STUB = "2022-08-01";

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

    @Test
    public void test_markAsPresent() {
        Attendance testAtt = new Attendance(ATTENDANCE_STUB);
        testAtt.markAsPresent();
        assertEquals(testAtt.getIsPresent(), true);
    }

    @Test
    public void test_markAsAbsent() {
        Attendance testAtt = new Attendance(ATTENDANCE_STUB);
        testAtt.markAsAbsent();
        assertEquals(testAtt.getIsPresent(), false);
    }

    @Test
    public void test_attendanceCheckString() {
        Attendance testAtt = new Attendance(ATTENDANCE_STUB);
        assertEquals(testAtt.attendanceCheckString(), " [Absent]");
        testAtt.markAsPresent();
        assertEquals(testAtt.attendanceCheckString(), " [Present]");
        testAtt.markAsAbsent();
        assertEquals(testAtt.attendanceCheckString(), " [Absent]");
    }

    @Test
    public void test_setIsPresent() {
        Attendance testAtt = new Attendance(ATTENDANCE_STUB);
        testAtt.setIsPresent(true);
        assertTrue(testAtt.getIsPresent());
        testAtt.setIsPresent(false);
        assertFalse(testAtt.getIsPresent());
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "2022-02-29";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidDate));

        String invalidMonth = "2022-13-12";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidMonth));
    }

    @Test
    public void equals() {
        Attendance testAtt = new Attendance(ATTENDANCE_STUB);
        // to check self
        assertEquals(testAtt, testAtt);
        // to check with other commands
        assertFalse(testAtt.equals(new Homework("homework")));
        // to check with null object
        assertFalse(testAtt.equals(null));
    }

}
