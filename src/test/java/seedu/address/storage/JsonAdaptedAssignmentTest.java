package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_STUDENTS_LIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TITLE;
import static seedu.address.storage.JsonAdaptedAssignment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

class JsonAdaptedAssignmentTest {

    @Test
    public void toModelType_validAssignmentDetails_returnsAssignment() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(FIRST);
        assertEquals(FIRST, assignment.toModelType());
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(null, VALID_TASK_DESCRIPTION,
                VALID_ASSIGNMENT_STUDENTS_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_TASK_TITLE, null,
                VALID_ASSIGNMENT_STUDENTS_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }
}
