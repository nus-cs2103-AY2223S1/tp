package seedu.hrpro.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.hrpro.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalTasks.TASK_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.exceptions.IllegalValueException;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.task.TaskDescription;
import seedu.hrpro.model.task.TaskMark;

public class JsonAdaptedTaskTest {
    private static final String INVALID_TASK_DESCRIPTION = "HOMEWORK!!!";
    private static final String INVALID_TASK_DEADLINE = "2022-06";
    private static final String INVALID_TASK_MARK = "MAYBE";

    private static final String VALID_TASK_DESCRIPTION = TASK_1.getTaskDescription().toString();
    private static final String VALID_TASK_DEADLINE = TASK_1.getTaskDeadline().toString();
    private static final String VALID_TASK_MARK = TASK_1.getTaskMark().toString();
    private static final List<JsonAdaptedTask> VALID_TASK = new ArrayList<>(
            Arrays.asList(new JsonAdaptedTask(TASK_1)));

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TASK_1);
        assertEquals(TASK_1, task.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_DEADLINE, INVALID_TASK_DESCRIPTION, VALID_TASK_MARK);
        String expectedMessage = TaskDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_DEADLINE, null, VALID_TASK_MARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_TASK_DEADLINE, VALID_TASK_DESCRIPTION, VALID_TASK_MARK);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_TASK_DESCRIPTION, VALID_TASK_MARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskMark_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_DEADLINE, VALID_TASK_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                TaskMark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskMark_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_DEADLINE, VALID_TASK_DESCRIPTION, INVALID_TASK_MARK);
        String expectedMessage = TaskMark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
