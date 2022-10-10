package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_MODULE = "ABC QWE";
    private static final String INVALID_DEADLINE = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_MODULE = BENSON.getModule().toString();
    private static final String VALID_DEADLINE = BENSON.getDeadline().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_MODULE, VALID_DEADLINE, VALID_TAGS, false);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_MODULE, VALID_DEADLINE, VALID_TAGS, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_MODULE, VALID_DEADLINE, VALID_TAGS, false);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_DEADLINE, VALID_TAGS, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MODULE, INVALID_DEADLINE, VALID_TAGS, false);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_MODULE, null, VALID_TAGS, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_trueIsDone_success() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MODULE, VALID_DEADLINE, VALID_TAGS, true);
        assertDoesNotThrow(person::toModelType);
    }

    @Test
    public void toModelType_nullIsDone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_MODULE, VALID_DEADLINE, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "isDone");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_MODULE, VALID_DEADLINE, invalidTags, true);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
