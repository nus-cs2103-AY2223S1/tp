package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TITLE;
import static seedu.address.storage.JsonAdaptedToDo.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalToDos.FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

public class JsonAdaptedToDoTest {
    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedToDo task = new JsonAdaptedToDo(FIRST);
        assertEquals(FIRST, task.toModelType());
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedToDo task = new JsonAdaptedToDo(null, VALID_TASK_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedToDo task = new JsonAdaptedToDo(VALID_TASK_TITLE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedToDo task = new JsonAdaptedToDo(INVALID_TASK_TITLE, VALID_TASK_DESCRIPTION);
        String expectedMessage = String.format(TaskTitle.MESSAGE_CONSTRAINTS, TaskTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedToDo task = new JsonAdaptedToDo(VALID_TASK_TITLE, INVALID_TASK_DESCRIPTION);
        String expectedMessage = String.format(
                TaskDescription.MESSAGE_CONSTRAINTS, TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
