package jarvis.model.task;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jarvis.model.TaskDesc;

public class TaskDescTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDesc(null));
    }

    @Test
    public void constructor_invalidTaskDesc_throwsIllegalArgumentException() {
        String invalidTaskDesc = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDesc(invalidTaskDesc));
    }

    @Test
    public void isValidTaskDesc() {
        // null task desc
        assertThrows(NullPointerException.class, () -> TaskDesc.isValidTaskDesc(null));

        // invalid task desc
        assertFalse(TaskDesc.isValidTaskDesc("")); // empty string
        assertFalse(TaskDesc.isValidTaskDesc(" ")); // spaces only

        // valid task desc
        assertTrue(TaskDesc.isValidTaskDesc("Studio")); // alphabets only
        assertTrue(TaskDesc.isValidTaskDesc("Studio 2")); // alphanumeric characters
        assertTrue(TaskDesc.isValidTaskDesc("Grade quest 3 !")); // symbols
    }

    @Test
    public void equals() {
        TaskDesc desc1 = new TaskDesc("desc1");
        TaskDesc desc2 = new TaskDesc("desc2");

        //same values -> returns true
        TaskDesc desc1Copy = new TaskDesc("desc1");
        assertTrue(desc1.equals(desc1Copy));

        //same object -> returns true
        assertTrue(desc1.equals(desc1));

        // null -> returns false
        assertFalse(desc1.equals(null));

        // different type -> returns false
        assertFalse(desc1.equals(5));

        //different values -> returns false
        assertFalse(desc1.equals(desc2));
    }
}
