package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.team.Task;
import seedu.address.testutil.TypicalTasks;

import static org.junit.jupiter.api.Assertions.*;

class JsonAdaptedTaskTest {

    @Test
    void toModelType_validTask_returnsTask() throws IllegalValueException {
        JsonAdaptedTask task = new JsonAdaptedTask(TypicalTasks.TASK_1);
        assertEquals(TypicalTasks.TASK_1, task.toModelType());
    }

    @Test
    void toModelType_invalidTask_throwsException() {
        JsonAdaptedTask task = new JsonAdaptedTask(" ");
        assertThrows(IllegalValueException.class, () -> task.toModelType());
    }
}