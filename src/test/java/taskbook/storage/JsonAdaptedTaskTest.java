package taskbook.storage;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskbook.model.TaskBook;
import taskbook.testutil.TypicalTaskBook;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalTaskBook.BENSON.getName().toString();
    private static final String VALID_PHONE = TypicalTaskBook.BENSON.getPhone().toString();
    private static final String VALID_EMAIL = TypicalTaskBook.BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = TypicalTaskBook.BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalTaskBook.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final String VALID_ASSIGNMENT = TypicalTaskBook.SLEEPING.getAssignment().name();
    private static final String VALID_DESCRIPTION = TypicalTaskBook.SLEEPING.getDescription().toString();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTodo(TypicalTaskBook.SLEEPING);
        Assertions.assertEquals(TypicalTaskBook.SLEEPING, task.toModelType());
    }

    /*
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() throws IllegalValueException {
        TaskBook taskBook = new TaskBook();
        taskBook.addPerson(TypicalTaskBook.BENSON);
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_NAME, VALID_ASSIGNMENT, VALID_DESCRIPTION, false);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, ((tb) -> task.toModelType(tb)));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person.toModelType());
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT,
                Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT,
                Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT,
                Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
        Assert.assertThrows(IllegalValueException.class, person::toModelType);
    }

     */

}
