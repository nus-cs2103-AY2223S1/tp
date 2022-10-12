package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK_ONE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Title;

public class JsonAdaptedTaskTest {

    private static final String INVALID_TITLE = " ";
    private static final String INVALID_DONE = " ";
    private static final String INVALID_CONTACT = "TEST3";

    private static final String VALID_TITLE = TASK_ONE.getTitle().toString();
    private static final String VALID_DONE = "false";
    private static final List<JsonAdaptedContact> VALID_CONTACTS = TASK_ONE.getAssignedContacts().stream()
            .map(JsonAdaptedContact::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TASK_ONE);
        assertEquals(TASK_ONE, task.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_TITLE, VALID_DONE, VALID_CONTACTS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DONE, VALID_CONTACTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDone_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, INVALID_DONE, VALID_CONTACTS);
        // TODO: Update Message
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Done");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDone_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TITLE, null, VALID_CONTACTS);
        // TODO: Update Message
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Done");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidContacts_throwsIllegalValueException() {
        List<JsonAdaptedContact> invalidContacts = new ArrayList<>(VALID_CONTACTS);
        invalidContacts.add(new JsonAdaptedContact(INVALID_CONTACT));
        JsonAdaptedTask task =
            new JsonAdaptedTask(VALID_TITLE, VALID_DONE, invalidContacts);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    // TODO: Update test for all fields

}
