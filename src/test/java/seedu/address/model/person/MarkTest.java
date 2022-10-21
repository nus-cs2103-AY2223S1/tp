package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mark((null)));
    }

    @Test
    public void isValidAttendanceTest() {
        // null attendance
        assertFalse(Mark.isValidAttendance(null));

        // valid attendance boolean
        assertTrue(Mark.isValidAttendance(Boolean.TRUE)); // attendance is set to true
        assertTrue(Mark.isValidAttendance(Boolean.FALSE)); // attendance is set to false
    }

    @Test
    public void constructor_valid_returnsCorrectObject() {
        Mark hasAttended = new Mark(Boolean.TRUE);
        assertTrue(hasAttended.isMarked());

        Mark didNotAttend = new Mark(Boolean.FALSE);
        assertFalse(didNotAttend.isMarked());

        Mark defaultAttendance = new Mark();
        assertFalse(defaultAttendance.isMarked());
    }

    @Test
    public void resetTests() {
        Mark hadAttended = new Mark(Boolean.TRUE);
        hadAttended.reset();
        assertFalse(hadAttended.isMarked());

        // no change of state test
        Mark hasNotAttended = new Mark();
        hasNotAttended.reset();
        assertFalse(hasNotAttended.isMarked());
    }
}
