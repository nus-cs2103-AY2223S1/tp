package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.testutil.TypicalTasks;


class JsonAdaptedTaskTest {

    @Test
    void toModelType_validTask_returnsTask() throws IllegalValueException {
        JsonAdaptedTask task = new JsonAdaptedTask(TypicalTasks.TASK_1);
        assertEquals(TypicalTasks.TASK_1, task.toModelType());
    }

    @Test
    void toModelType_invalidTask_throwsException() {
        JsonAdaptedTask task = new JsonAdaptedTask(" ", List.of(), "false", "2022-12-12T23:59");
        assertThrows(IllegalValueException.class, () -> task.toModelType());
    }
}
