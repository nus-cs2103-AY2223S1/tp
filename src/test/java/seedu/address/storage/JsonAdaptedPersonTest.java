package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_LESSON_PLAN = BENSON.getLessonPlan().toString();
    private static final List<JsonAdaptedHomework> VALID_HOMEWORK = BENSON.getHomeworkList().homeworkList.stream()
            .map(JsonAdaptedHomework::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAttendance> VALID_ATTENDANCE = BENSON.getAttendanceList()
            .attendanceList.stream()
            .map(JsonAdaptedAttendance::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedDuration> VALID_DURATION = BENSON.getDurationList()
            .durationList.stream()
            .map(JsonAdaptedDuration::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedGradeProgress> VALID_GRADE_PROGRESS = BENSON.getGradeProgressList()
            .gradeProgressList.stream()
            .map(JsonAdaptedGradeProgress::new)
            .collect(Collectors.toList());
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
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_LESSON_PLAN,
                        VALID_HOMEWORK, VALID_ATTENDANCE, VALID_DURATION, VALID_GRADE_PROGRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_PHONE, VALID_LESSON_PLAN,
                        VALID_HOMEWORK, VALID_ATTENDANCE, VALID_DURATION, VALID_GRADE_PROGRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_LESSON_PLAN,
                        VALID_HOMEWORK, VALID_ATTENDANCE, VALID_DURATION,VALID_GRADE_PROGRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_LESSON_PLAN,
                        VALID_HOMEWORK, VALID_ATTENDANCE, VALID_DURATION,VALID_GRADE_PROGRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullLessonPlan_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                        VALID_HOMEWORK, VALID_ATTENDANCE, VALID_DURATION,VALID_GRADE_PROGRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonPlan.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_LESSON_PLAN,
                        VALID_HOMEWORK, VALID_ATTENDANCE, VALID_DURATION,VALID_GRADE_PROGRESS, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
