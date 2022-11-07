package swift.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static swift.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static swift.testutil.Assert.assertThrows;
import static swift.testutil.TypicalTasks.CS2103T;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import swift.commons.exceptions.IllegalValueException;
import swift.model.task.Deadline;
import swift.model.task.Description;
import swift.model.task.TaskName;
import swift.testutil.TaskBuilder;

public class JsonAdaptedTaskTest {
    private static final String INVALID_ID = "hello world";
    private static final String INVALID_NAME = "M@ke bread";
    private static final String INVALID_DESCRIPTION = "~milk";
    private static final String INVALID_DEADLINE = "11/11/2020 12:00";

    private static final String VALID_ID = CS2103T.getId().toString();
    private static final String VALID_NAME = CS2103T.getName().toString();
    private static final String VALID_DESCRIPTION = CS2103T.getDescription().get().toString();
    private static final String VALID_DEADLINE = CS2103T.getDeadline().get().toString();
    private static final boolean VALID_IS_DONE = CS2103T.isDone();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(CS2103T);
        assertEquals(CS2103T, task.toModelType());
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_ID, VALID_NAME, VALID_DESCRIPTION, VALID_DEADLINE, VALID_IS_DONE);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_NAME, VALID_DESCRIPTION, VALID_DEADLINE, VALID_IS_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UUID.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_ID, INVALID_NAME, VALID_DESCRIPTION, VALID_DEADLINE, VALID_IS_DONE);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_ID, null, VALID_DESCRIPTION, VALID_DEADLINE, VALID_IS_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_ID, VALID_NAME, INVALID_DESCRIPTION, VALID_DEADLINE, VALID_IS_DONE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_ID, VALID_NAME, null, VALID_DEADLINE, VALID_IS_DONE);
        assertEquals(new TaskBuilder(CS2103T).withDescription(null).build(), task.toModelType());
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_ID, VALID_NAME, VALID_DESCRIPTION, INVALID_DEADLINE, VALID_IS_DONE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_ID, VALID_NAME, VALID_DESCRIPTION, null, VALID_IS_DONE);
        assertEquals(new TaskBuilder(CS2103T).withDeadline(null).build(), task.toModelType());
    }
}
