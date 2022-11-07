package seedu.modquik.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modquik.storage.JsonAdaptedTutorial.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.modquik.testutil.Assert.assertThrows;
import static seedu.modquik.testutil.TypicalTutorials.TUTORIAL1;

import org.junit.jupiter.api.Test;

import seedu.modquik.commons.exceptions.IllegalValueException;
import seedu.modquik.model.commons.ModuleCode;
import seedu.modquik.model.commons.Venue;
import seedu.modquik.model.tutorial.TutorialName;
import seedu.modquik.storage.datetime.JsonAdaptedWeeklyTimeslot;

public class JsonAdaptedTutorialTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_MODULE = "susu03";
    private static final String INVALID_START_TIME = "1000";
    private static final String INVALID_DAY = "8";

    private static final String VALID_NAME = TUTORIAL1.getName().toString();
    private static final String VALID_MODULE = TUTORIAL1.getModule().toString();
    private static final String VALID_VENUE = TUTORIAL1.getVenue().toString();
    private static final String VALID_START_TIME = TUTORIAL1.getTimeslot().getStartTimeFormatted();
    private static final String VALID_END_TIME = TUTORIAL1.getTimeslot().getEndTimeFormatted();
    private static final String VALID_DAY = TUTORIAL1.getTimeslot().getDay();

    @Test
    public void toModelType_validTutorialDetails_returnsTutorial() throws Exception {
        JsonAdaptedTutorial tutorial = new JsonAdaptedTutorial(TUTORIAL1);
        assertEquals(TUTORIAL1, tutorial.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(INVALID_NAME, VALID_MODULE, VALID_VENUE,
                        new JsonAdaptedWeeklyTimeslot(VALID_DAY, VALID_START_TIME, VALID_END_TIME));
        String expectedMessage = TutorialName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(null, VALID_MODULE, VALID_VENUE,
                        new JsonAdaptedWeeklyTimeslot(VALID_DAY, VALID_START_TIME, VALID_END_TIME));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TutorialName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_NAME, INVALID_MODULE, VALID_VENUE,
                        new JsonAdaptedWeeklyTimeslot(VALID_DAY, VALID_START_TIME, VALID_END_TIME));
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_NAME, null, VALID_VENUE,
                        new JsonAdaptedWeeklyTimeslot(VALID_DAY, VALID_START_TIME, VALID_END_TIME));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_nullVenue_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_NAME, VALID_MODULE, null,
                        new JsonAdaptedWeeklyTimeslot(VALID_DAY, VALID_START_TIME, VALID_END_TIME));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_invalidTimeFormat_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_NAME, VALID_MODULE, VALID_VENUE,
                        new JsonAdaptedWeeklyTimeslot(VALID_DAY, INVALID_START_TIME, VALID_END_TIME));
        String expectedMessage = "Invalid time";
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_invalidTimeRange_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_NAME, VALID_MODULE, VALID_VENUE,
                        new JsonAdaptedWeeklyTimeslot(VALID_DAY, VALID_END_TIME, VALID_START_TIME));
        String expectedMessage = "Invalid time";
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedTutorial tutorial =
                new JsonAdaptedTutorial(VALID_NAME, VALID_MODULE, VALID_VENUE,
                        new JsonAdaptedWeeklyTimeslot(INVALID_DAY, INVALID_START_TIME, VALID_END_TIME));
        String expectedMessage = "Invalid day";
        assertThrows(IllegalValueException.class, expectedMessage, tutorial::toModelType);
    }

}
