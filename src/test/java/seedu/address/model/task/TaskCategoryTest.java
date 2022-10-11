package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.task.exceptions.LevelOutOfRangeException;


public class TaskCategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskCategory(1,null));
    }

    @Test
    public void constructor_levelTooHigh() {
        assertThrows(LevelOutOfRangeException.class, () -> new TaskCategory(10,null));
        assertThrows(LevelOutOfRangeException.class, () -> new TaskCategory(-10,null));
    }

}
