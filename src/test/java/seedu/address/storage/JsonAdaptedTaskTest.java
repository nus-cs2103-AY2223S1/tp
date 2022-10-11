package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.FINISH_TP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.TaskName;

class JsonAdaptedTaskTest {
    private static final String INVALID_TASKNAME = " ";
    private static final String INVALID_MODULE = " ";
    private static final String INVALID_DEADLINE = "123456";
    private static final String INVALID_STATUS = "a";

    private static final String VALID_TASKNAME = FINISH_TP.getName().toString();
    private static final String VALID_MODULE = FINISH_TP.getModule().toString();
    private static final String VALID_DEADLINE = FINISH_TP.getDeadline().toString();
    private static final String VALID_STATUS = FINISH_TP.getStatus().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(FINISH_TP);
        assertEquals(FINISH_TP, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_TASKNAME, VALID_MODULE, VALID_DEADLINE, VALID_STATUS);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASKNAME, INVALID_MODULE, VALID_DEADLINE, VALID_STATUS);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASKNAME, VALID_MODULE, INVALID_DEADLINE, VALID_STATUS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
