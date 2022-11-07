package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_LARGE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class AttendanceListTest {

    private static final Attendance ATTENDANCE_STUB = new Attendance("2022-08-01");

    private static final Attendance ATTENDANCE_STUB_2 = new Attendance("2022-09-09");

    private AttendanceList attendanceList = new AttendanceList();

    @Test
    public void execute_index_failure() {
        // clear off list before starting test
        attendanceList.clearList();

        attendanceList.addAttendance(ATTENDANCE_STUB);
        attendanceList.addAttendance(ATTENDANCE_STUB_2);

        // index check for remove
        assertThrows(IllegalArgumentException.class , ()
                -> attendanceList.removeAtIndex(INDEX_LARGE));

        // index check for edit
        assertThrows(IllegalArgumentException.class , ()
                -> attendanceList.editAtIndex(INDEX_LARGE, ATTENDANCE_STUB_2));

        // index check for mark
        assertThrows(IllegalArgumentException.class , ()
                -> attendanceList.markAtIndex(INDEX_LARGE));

        // index check for unmark
        assertThrows(IllegalArgumentException.class , ()
                -> attendanceList.unmarkAtIndex(INDEX_LARGE));

        // clear off list
        attendanceList.clearList();
    }

    @Test
    public void test_unmarkAndMarkAtIndex() {
        // clear off list before starting test
        attendanceList.clearList();

        attendanceList.addAttendance(ATTENDANCE_STUB);

        attendanceList.markAtIndex(Index.fromOneBased(1));
        assertTrue(ATTENDANCE_STUB.getIsPresent());

        attendanceList.unmarkAtIndex(Index.fromOneBased(1));
        assertFalse(ATTENDANCE_STUB.getIsPresent());

        // clear off list
        attendanceList.clearList();
    }
}
