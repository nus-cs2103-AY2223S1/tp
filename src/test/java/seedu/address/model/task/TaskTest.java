package seedu.address.model.task;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.task.Task;



public class TaskTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null,
                null, null, null, null, null, false));
    }
}
