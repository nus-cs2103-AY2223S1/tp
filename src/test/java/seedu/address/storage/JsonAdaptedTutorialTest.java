package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTutorial.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorials.TUTORIAL1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutorial.Content;
import seedu.address.model.tutorial.Group;
import seedu.address.model.tutorial.Time;

public class JsonAdaptedTutorialTest {

    private static final String VALID_GROUP = TUTORIAL1.getGroup().toString();
    private static final String INVALID_GROUP = "";

    private static final String VALID_CONTENT = TUTORIAL1.getContent().toString();
    private static final String INVALID_CONTENT = "";

    private static final String VALID_DATE_TIME = TUTORIAL1.getTime().toMemoryString();
    private static final String INVALID_DATE_TIME_FORMAT = "2022-10-01T0800";
    private static final String INVALID_DATE = "10-10-2022 1400";
    private static final String INVALID_TIME = "2022-10-01 200";

    private static final boolean VALID_STATUS = TUTORIAL1.getStatus();


    @Test
    public void toModelType_validTutorial_returnTutorial() throws Exception {
        JsonAdaptedTutorial tutorial = new JsonAdaptedTutorial(TUTORIAL1);
        assertEquals(TUTORIAL1, tutorial.toModelType());
    }

    @Test
    public void toModelType_nullGroup_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(null, VALID_CONTENT, VALID_DATE_TIME, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Group.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_nullContent_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_GROUP, null, VALID_DATE_TIME, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Content.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedTutorial question = new JsonAdaptedTutorial(VALID_GROUP, VALID_CONTENT, null, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, question::toModelType);
    }

    @Test
    public void toModelType_invalidGroup_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(INVALID_GROUP, VALID_CONTENT, VALID_DATE_TIME, VALID_STATUS);
        String expectedMessage = Group.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_invalidContent_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial = new
                JsonAdaptedTutorial(VALID_GROUP, INVALID_CONTENT, VALID_DATE_TIME, VALID_STATUS);
        String expectedMessage = Content.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;

        JsonAdaptedTutorial tutorial = new
                JsonAdaptedTutorial(VALID_GROUP, VALID_CONTENT, INVALID_DATE_TIME_FORMAT, VALID_STATUS);
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);

        JsonAdaptedTutorial tutorial1 = new JsonAdaptedTutorial(VALID_GROUP, VALID_CONTENT, INVALID_DATE, VALID_STATUS);
        assertThrows(IllegalValueException.class, expectedMessage, tutorial1::toModelType);

        JsonAdaptedTutorial tutorial2 = new JsonAdaptedTutorial(VALID_GROUP, VALID_CONTENT, INVALID_TIME, VALID_STATUS);
        assertThrows(IllegalValueException.class, expectedMessage, tutorial2::toModelType);
    }
}
