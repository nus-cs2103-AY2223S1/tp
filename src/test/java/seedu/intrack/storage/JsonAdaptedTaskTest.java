package seedu.intrack.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.intrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.model.internship.Task;

public class JsonAdaptedTaskTest {

    private static final Task VALID_TASK = new Task("Online assessment", "25-11-2022 10:00");

    private static final String INVALID_TASKNAME = "";
    private static final String INVALID_TASKTIME = "20/10/2022 12:00";

    private static final String VALID_TASKNAME = "Technical Interview";
    private static final String VALID_TASKTIME = "20-10-2022 12:00";

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws IllegalValueException {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASK);
        assertEquals(VALID_TASK, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_TASKNAME, VALID_TASKTIME);
        String expectedMessage = Task.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_TASKTIME);
        String expectedMessage = Task.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASKNAME, INVALID_TASKTIME);
        String expectedMessage = Task.TIME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASKNAME, null);
        String expectedMessage = Task.TIME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
