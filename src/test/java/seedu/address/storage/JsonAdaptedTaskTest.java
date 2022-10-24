package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK1;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

public class JsonAdaptedTaskTest {
    private static final String INVALID_TASK_NAME = "@Task";
    private static final String INVALID_TASK_DESCRIPTION = " ";
    private static final String INVALID_TASK_DEADLINE = "10-10-2020";

    private static final String VALID_TASK_NAME = TASK1.getTaskName().toString();
    private static final String VALID_TASK_DESCRIPTION = TASK1.getTaskDescription().toString();
    private static final String VALID_TASK_DEADLINE = TASK1.getTaskDeadline().toString();
    private static final List<JsonAdaptedStudent> VALID_STUDENTS = TASK1.getStudents().stream()
            .map(JsonAdaptedStudent::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TASK1);
        assertEquals(TASK1, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_TASK_NAME, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_STUDENTS);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_STUDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDescription_throwsIllegalArgumentException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, INVALID_TASK_DESCRIPTION, VALID_TASK_DEADLINE, VALID_STUDENTS);
        String expectedMessage = TaskDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, null, VALID_TASK_DEADLINE, VALID_STUDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_DESCRIPTION, INVALID_TASK_DEADLINE, VALID_STUDENTS);
        String expectedMessage = TaskDeadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TASK_NAME, VALID_TASK_DESCRIPTION, null, VALID_STUDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDeadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
