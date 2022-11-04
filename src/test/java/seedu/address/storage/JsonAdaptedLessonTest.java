package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Lesson;
import seedu.address.testutil.TypicalLessons;

public class JsonAdaptedLessonTest {

    private static final String INVALID_TYPE = "tut1";
    private static final String INVALID_MODULE = "CS2103T*";
    private static final String INVALID_DAY = "Thursday";
    private static final String INVALID_START_TIME = "12.00";
    private static final String INVALID_END_TIME = "abcd";

    private static final String VALID_TYPE = TypicalLessons.CS2040S_LAB.getType();
    private static final String VALID_MODULE = TypicalLessons.CS2040S_LAB.getModule();
    private static final String VALID_DAY = String.format("%d", TypicalLessons.CS2040S_LAB.getDay());
    private static final String VALID_START_TIME = TypicalLessons.CS2040S_LAB.getStartTime().toString();
    private static final String VALID_END_TIME = TypicalLessons.CS2040S_LAB.getEndTime().toString();

    @Test
    public void toModelType_validExistingLesson_returnsExistingLesson() throws Exception {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(TypicalLessons.CS2040S_LAB);
        assertEquals(TypicalLessons.CS2040S_LAB, lesson.toModelType());
    }

    @Test
    public void toModelType_invalidType_throwsParseException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(INVALID_TYPE, VALID_MODULE, VALID_DAY, VALID_START_TIME, VALID_END_TIME);
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsNullPointerException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(null, VALID_MODULE, VALID_DAY, VALID_START_TIME, VALID_END_TIME);
        assertThrows(NullPointerException.class, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsParseException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_TYPE, INVALID_MODULE, VALID_DAY, VALID_START_TIME, VALID_END_TIME);
        String expectedMessage = Lesson.MESSAGE_CONSTRAINTS;
        assertThrows(ParseException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsNullPointerException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_TYPE, null, VALID_DAY, VALID_START_TIME, VALID_END_TIME);
        assertThrows(NullPointerException.class, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsParseException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_TYPE, VALID_MODULE, INVALID_DAY, VALID_START_TIME, VALID_END_TIME);
        String expectedMessage = LessonCommand.MESSAGE_INVALID_DAY;
        assertThrows(ParseException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullDay_throwsNullPointerException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_TYPE, VALID_MODULE, null, VALID_START_TIME, VALID_END_TIME);
        assertThrows(NullPointerException.class, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsParseException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_TYPE, VALID_MODULE, VALID_DAY, INVALID_START_TIME, VALID_END_TIME);
        String expectedMessage = LessonCommand.MESSAGE_INVALID_TIME;
        assertThrows(ParseException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsNullPointerException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_TYPE, VALID_MODULE, VALID_DAY, null, VALID_END_TIME);
        assertThrows(NullPointerException.class, lesson::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsParseException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_TYPE, VALID_MODULE, VALID_DAY, VALID_START_TIME, INVALID_END_TIME);
        String expectedMessage = LessonCommand.MESSAGE_INVALID_TIME;
        assertThrows(ParseException.class, expectedMessage, lesson::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsNullPointerException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_TYPE, VALID_MODULE, VALID_DAY, VALID_START_TIME, null);
        assertThrows(NullPointerException.class, lesson::toModelType);
    }

}
