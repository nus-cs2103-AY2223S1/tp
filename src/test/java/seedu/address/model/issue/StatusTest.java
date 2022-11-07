package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    private Status testStatusOne = new Status(true);
    private Status testStatusTwo = new Status(false);
    private Status testStatusThree = new Status(true);

    @Test
    public void isValidStatus_error() {
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));
    }

    @Test
    public void isValidStatus_success() {
        assertTrue(Status.isValidStatus("true"));
        assertTrue(Status.isValidStatus("FALSE"));
        assertTrue(Status.isValidStatus("tRuE"));
    }

    @Test
    public void isValidStatus_false() {
        assertFalse(Status.isValidStatus("veryfalse"));
        assertFalse(Status.isValidStatus("0"));
        assertFalse(Status.isValidStatus(" "));
    }

    @Test
    public void testEquals() {

        // same status -> returns true
        assertTrue(testStatusOne.equals(testStatusOne));

        // same value -> returns true
        assertTrue(testStatusOne.equals(testStatusThree));

        // different type -> returns false
        assertFalse(testStatusOne.equals(1));

        // null -> returns false
        assertFalse(testStatusOne.equals(null));

        // different value -> returns false
        assertFalse(testStatusOne.equals(testStatusTwo));

    }

    @Test
    public void getCompletionStatus_success() {
        Status completedStatus = new Status(true);
        Status incompleteStatus = new Status(false);

        assertEquals("Completed", completedStatus.getCompletionStatus());
        assertEquals("Incomplete", incompleteStatus.getCompletionStatus());
    }
}
