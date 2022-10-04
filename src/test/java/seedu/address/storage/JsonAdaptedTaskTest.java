package seedu.address.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Module;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

class JsonAdaptedTaskTest {
    private static final String INVALID_TASKNAME = " ";
    private static final String INVALID_MODULE = " ";
    private static final String INVALID_DEADLINE = "02-02-2022 23:59";

    private static final String VALID_TASKNAME = "Lab 2";
    private static final String VALID_MODULE = "CS2030S";
    private static final String VALID_DEADLINE = "2022-02-02 23:59";

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        Task actualTask = new Task(new TaskName(VALID_TASKNAME), new Module(VALID_MODULE), new Deadline(VALID_DEADLINE));
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASKNAME, VALID_MODULE, VALID_DEADLINE);
        assertEquals(actualTask, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_TASKNAME, VALID_MODULE, VALID_DEADLINE);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASKNAME, INVALID_MODULE, VALID_DEADLINE);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASKNAME, VALID_MODULE, INVALID_DEADLINE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
