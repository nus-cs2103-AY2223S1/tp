package seedu.address.model.task;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.LevelOutOfRangeException;

public class TaskCategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskCategory( null));
    }

}
