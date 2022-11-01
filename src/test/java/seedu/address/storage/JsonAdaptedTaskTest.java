package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_STRING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_A;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.task.Task;

public class JsonAdaptedTaskTest {
    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TASK_A);
        assertEquals(TASK_A, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(EMPTY_STRING);
        String expectedMessage = Task.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage,
                task::toModelType);
    }

    @Test
    public void toModelType_nullTaskDescription_throwsNullPointerException() {
        JsonAdaptedTask task = new JsonAdaptedTask((String) null);
        String expectedMessage = Task.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage,
                task::toModelType);
    }

}
