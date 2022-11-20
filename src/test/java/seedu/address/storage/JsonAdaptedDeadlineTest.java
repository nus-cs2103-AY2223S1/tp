package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TASK_TITLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TITLE;
import static seedu.address.storage.JsonAdaptedDeadline.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeadlines.FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.FormatDate;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

public class JsonAdaptedDeadlineTest {
    @Test
    public void toModelType_validDeadlineDetails_returnsDeadline() throws Exception {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(FIRST);
        assertEquals(FIRST, deadline.toModelType());
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(null, VALID_TASK_DESCRIPTION, VALID_DEADLINE_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, deadline::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(VALID_TASK_TITLE, null, VALID_DEADLINE_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, deadline::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FormatDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, deadline::toModelType);
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(INVALID_TASK_TITLE, VALID_TASK_DESCRIPTION,
                VALID_DEADLINE_DATE);
        String expectedMessage = String.format(TaskTitle.MESSAGE_CONSTRAINTS, TaskTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, deadline::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(VALID_TASK_TITLE, INVALID_TASK_DESCRIPTION,
                VALID_DEADLINE_DATE);
        String expectedMessage = String.format(TaskDescription.MESSAGE_CONSTRAINTS,
                TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, deadline::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedDeadline deadline = new JsonAdaptedDeadline(VALID_TASK_TITLE, VALID_TASK_DESCRIPTION,
                INVALID_DEADLINE_DATE);
        String expectedMessage = String.format(FormatDate.MESSAGE_CONSTRAINTS, FormatDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, deadline::toModelType);
    }


}
