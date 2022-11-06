package jarvis.storage;

import static jarvis.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalTasks.MISSION1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import jarvis.model.TaskDesc;

public class JsonAdaptedTaskTest {
    private static final String INVALID_DESC = "";

    private static final String VALID_DESC = MISSION1.getDesc().toString();
    private static final LocalDate VALID_DATE = MISSION1.getDeadline();


    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(MISSION1);
        assertEquals(MISSION1, task.toModelType());
    }

    @Test
    public void toModelType_invalidDesc_throwsIllegalArgumentException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_DESC, VALID_DATE, false);
        String expectedMessage = TaskDesc.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalArgumentException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask((String) null, VALID_DATE, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDesc.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, task::toModelType);
    }
}
