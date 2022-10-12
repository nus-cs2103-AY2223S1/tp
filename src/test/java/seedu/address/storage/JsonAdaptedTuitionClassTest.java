package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTuitionClass.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.level.Level;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Name;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;

public class JsonAdaptedTuitionClassTest {
    private static final String INVALID_NAME = "CS2040!!!";
    private static final String INVALID_SUBJECT = "Defense @gainst the D@rk @rts";
    private static final String INVALID_LEVEL = "masters";
    private static final String INVALID_DAY = "bidet";
    private static final String INVALID_STARTTIME = "5pm";
    private static final String INVALID_ENDTIME = "thursday";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TUITIONCLASS1.getName().toString();
    private static final String VALID_SUBJECT = TUITIONCLASS1.getSubject().name();
    private static final String VALID_LEVEL = TUITIONCLASS1.getLevel().name();
    private static final String VALID_DAY = TUITIONCLASS1.getDay().name();
    private static final String VALID_STARTTIME = TUITIONCLASS1.getTime().getStartTime();
    private static final String VALID_ENDTIME = TUITIONCLASS1.getTime().getEndTime();
    private static final List<JsonAdaptedTag> VALID_TAGS = TUITIONCLASS1.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedStudent> VALID_STUDENTS = TUITIONCLASS1.getStudents().stream()
            .map(JsonAdaptedStudent::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTutor> VALID_TUTORS = TUITIONCLASS1.getTutors().stream()
            .map(JsonAdaptedTutor::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTuitionClassDetails_returnsTuitionClass() throws Exception {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(TUITIONCLASS1);
        assertEquals(TUITIONCLASS1, tuitionClass.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass =
                new JsonAdaptedTuitionClass(INVALID_NAME, VALID_SUBJECT, VALID_LEVEL, VALID_DAY, VALID_STARTTIME,
                        VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(null, VALID_SUBJECT, VALID_LEVEL,
                VALID_DAY, VALID_STARTTIME, VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidSubject_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass =
                new JsonAdaptedTuitionClass(VALID_NAME, INVALID_SUBJECT, VALID_LEVEL, VALID_DAY, VALID_STARTTIME,
                        VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = Subject.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullSubject_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_NAME, null, VALID_LEVEL,
                VALID_DAY, VALID_STARTTIME, VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidLevel_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass =
                new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, INVALID_LEVEL, VALID_DAY, VALID_STARTTIME,
                        VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = Level.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullLevel_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, null,
                VALID_DAY, VALID_STARTTIME, VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Level.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass =
                new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, VALID_LEVEL, INVALID_DAY, VALID_STARTTIME,
                        VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullDay_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, VALID_LEVEL,
                null, VALID_STARTTIME, VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass =
                new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, VALID_LEVEL, VALID_DAY, INVALID_STARTTIME,
                        VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, VALID_LEVEL,
                VALID_DAY, null, VALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass =
                new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, VALID_LEVEL, VALID_DAY, VALID_STARTTIME,
                        INVALID_ENDTIME, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedTuitionClass tuitionClass = new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, VALID_LEVEL,
                VALID_DAY, VALID_STARTTIME, null, VALID_TAGS, VALID_STUDENTS, VALID_TUTORS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tuitionClass::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTuitionClass tuitionClass =
                new JsonAdaptedTuitionClass(VALID_NAME, VALID_SUBJECT, VALID_LEVEL, VALID_DAY, VALID_STARTTIME,
                        VALID_ENDTIME, invalidTags, VALID_STUDENTS, VALID_TUTORS);
        assertThrows(IllegalValueException.class, tuitionClass::toModelType);
    }

}
