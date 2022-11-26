package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TaskStatus.of(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> TaskStatus.of(invalidStatus));
    }

    @Test
    public void constructor_validStatus_success() {
        assertEquals(TaskStatus.COMPLETE, TaskStatus.of("Complete"));
    }

    @Test
    public void isComplete() {
        assertTrue(TaskStatus.of("ComPlete").isComplete());
        assertFalse(TaskStatus.of("InComplete").isComplete());
    }

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> TaskStatus.isValidStatus(null));

        // invalid status
        assertFalse(TaskStatus.isValidStatus("")); // empty string
        assertFalse(TaskStatus.isValidStatus(" ")); // spaces only
        assertFalse(TaskStatus.isValidStatus("completed")); // contains an extra character

        // valid status
        assertTrue(TaskStatus.isValidStatus("complete"));
        assertTrue(TaskStatus.isValidStatus("COMPLETE"));
        assertTrue(TaskStatus.isValidStatus("INCOMPLETE"));
        assertTrue(TaskStatus.isValidStatus("incomplete"));
        assertTrue(TaskStatus.isValidStatus("InComplete"));
    }

    @Test
    public void equals() {
        TaskStatus completeStatus = TaskStatus.of("complete");
        TaskStatus incompleteStatus = TaskStatus.of("incomplete");
        TaskStatus completeStatusCopy = TaskStatus.of("Complete");

        // same object -> returns true
        assertTrue(completeStatus.equals(completeStatus));

        // same values -> returns true
        assertTrue(completeStatus.equals(completeStatusCopy));

        // different types -> returns false
        assertFalse(completeStatus.equals(1));

        // null -> returns false
        assertFalse(completeStatus.equals(null));

        // different status -> returns false
        assertFalse(completeStatus.equals(incompleteStatus));
    }
}
