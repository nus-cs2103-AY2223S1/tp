package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalTasks.ALICE;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_CATEGORY_NAME = "BACKEND";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_DEADLINE = "2022-1-1";
    private static final String INVALID_PRIORITY = "MEDIUM";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_IS_DONE = "True";

    private static final String VALID_NAME = ALICE.getName().toString();
    private static final String VALID_CATEGORY_NAME = ALICE.getCategory().getTaskCategoryType().toString();
    private static final String VALID_DESCRIPTION = ALICE.getDescription().getTaskDescription();
    private static final String VALID_PRIORITY = ALICE.getPriority().getPriority().toString();
    private static final String VALID_DEADLINE = ALICE.getDeadline().toString();
    private static final String VALID_EMAIL = ALICE.getEmail().toString();
    private static final String VALID_IS_DONE = Task.convertIsDoneFromBooleanToString(ALICE.isDone());

    private static final ObservableList<Person> persons = getTypicalAddressBook().getPersonList();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(ALICE);
        assertEquals(ALICE, task.toModelType(persons));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(INVALID_NAME, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    VALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName());
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    VALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_invalidCategoryName_throwsIllegalValueException() {
        String expectedMessage = TaskCategory.MESSAGE_CONSTRAINTS;
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, INVALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    VALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_nullCategoryName_throwsIllegalValueException() {
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskCategory.class.getSimpleName());
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_DESCRIPTION,
                    VALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, INVALID_DESCRIPTION,
                    VALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, null,
                    VALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    INVALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    null, VALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        String expectedMessage = TaskDeadline.MESSAGE_CONSTRAINTS;
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    VALID_PRIORITY, INVALID_DEADLINE, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDeadline.class.getSimpleName());
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    VALID_PRIORITY, null, VALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_invalidIsDone_throwsIllegalValueException() {
        String expectedMessage = Task.IS_DONE_MESSAGE_CONSTRAINT;
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    VALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, INVALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_nullIsDone_throwsIllegalValueException() {
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Boolean.class.getSimpleName());
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    VALID_PRIORITY, VALID_DEADLINE, VALID_EMAIL, null);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        String message = "";
        try {
            JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, VALID_CATEGORY_NAME, VALID_DESCRIPTION,
                    VALID_PRIORITY, VALID_DEADLINE, INVALID_EMAIL, VALID_IS_DONE);
            Task t = task.toModelType(persons);
        } catch (IllegalValueException e) {
            message = e.getMessage();
        }
        assertEquals(expectedMessage, message);
    }
}
