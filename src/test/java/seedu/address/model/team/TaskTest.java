package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_1_DUPLICATED;

import org.junit.jupiter.api.Test;


public class TaskTest {

    @Test
    public void null_constructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null));
    }

    @Test
    public void invalidName_constructor_throwsInvalidArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Task(" "));
    }

    @Test
    public void isValidName() {
        assertTrue(Task.isValidName("task"));
        assertTrue(Task.isValidName("123"));
        assertTrue(Task.isValidName("task 123"));
        assertFalse(Task.isValidName(" "));
        assertFalse(Task.isValidName(" task"));
    }

    @Test
    public void equals() {
        assertTrue(TASK_1.equals(TASK_1_DUPLICATED));
    }

    @Test
    public void valid_toString_equalNames() {
        assertEquals("task (Not assigned to any member yet)", TASK_1.toString());
    }
}
