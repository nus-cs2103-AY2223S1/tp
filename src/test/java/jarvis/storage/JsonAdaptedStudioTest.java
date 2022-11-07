package jarvis.storage;

import static jarvis.storage.JsonAdaptedStudio.MISSING_FIELD_MESSAGE_FORMAT;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import jarvis.model.LessonAttendance;
import jarvis.model.LessonNotes;
import jarvis.model.Student;
import jarvis.model.StudioParticipation;
import jarvis.model.TimePeriod;
import jarvis.testutil.TypicalLessons;

public class JsonAdaptedStudioTest {
    private static final String VALID_DESC = TypicalLessons.STUDIO_1.getDesc().toString();
    private static final LocalDateTime VALID_DT1 = TypicalLessons.STUDIO_1.startDateTime();
    private static final LocalDateTime VALID_DT2 = TypicalLessons.STUDIO_1.endDateTime();
    private static final ArrayList<JsonAdaptedStudent> VALID_STUDENT_LIST = TypicalLessons.STUDIO_1.getStudentList().stream()
            .map(s -> new JsonAdaptedStudent(s)).collect(Collectors.toCollection(ArrayList::new));
    private static final Map<Integer, Boolean> VALID_ATTENDANCE = TypicalLessons.STUDIO_1.getAttendance();
    private static final ArrayList<String> VALID_GENERAL_NOTES = TypicalLessons.STUDIO_1.getGeneralNotes();
    private static final Map<Integer, ArrayList<String>> VALID_STUDENT_NOTES = TypicalLessons.STUDIO_1.getStudentNotes();
    private static final Map<Integer, Integer> VALID_PARTICIPATION = STUDIO_1.getParticipation();

    @Test
    public void toModelType_validStudioDetails_returnsStudio() throws Exception {
        JsonAdaptedStudio studio = new JsonAdaptedStudio(STUDIO_1);
        assertEquals(STUDIO_1, studio.toModelType());
    }

    @Test
    public void toModelType_invalidTimePeriod_throwsIllegalArgumentException() {
        JsonAdaptedStudio studio =
                new JsonAdaptedStudio(VALID_DESC, VALID_DT2, VALID_DT1, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = TimePeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, studio::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalArgumentException() {
        JsonAdaptedStudio studio =
                new JsonAdaptedStudio((String) null, (LocalDateTime) null, VALID_DT1, VALID_STUDENT_LIST,
                        VALID_ATTENDANCE, VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimePeriod.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, studio::toModelType);
    }

    @Test
    public void toModelType_nullStudents_throwsIllegalArgumentException() {
        JsonAdaptedStudio studio =
                new JsonAdaptedStudio((String) null, VALID_DT1, VALID_DT2, null, VALID_ATTENDANCE,
                        VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Student.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, studio::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalArgumentException() {
        JsonAdaptedStudio studio =
                new JsonAdaptedStudio((String) null, VALID_DT1, VALID_DT2, VALID_STUDENT_LIST, null,
                        VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LessonAttendance.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, studio::toModelType);
    }

    @Test
    public void toModelType_nullGeneralNotes_throwsIllegalArgumentException() {
        JsonAdaptedStudio studio =
                new JsonAdaptedStudio((String) null, VALID_DT1, VALID_DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        null, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LessonNotes.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, studio::toModelType);
    }

    @Test
    public void toModelType_nullStudentNotes_throwsIllegalArgumentException() {
        JsonAdaptedStudio studio =
                new JsonAdaptedStudio((String) null, VALID_DT1, VALID_DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        VALID_GENERAL_NOTES, null, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                LessonNotes.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, studio::toModelType);
    }

    @Test
    public void toModelType_nullParticipation_throwsIllegalArgumentException() {
        JsonAdaptedStudio studio =
                new JsonAdaptedStudio((String) null, VALID_DT1, VALID_DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StudioParticipation.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, studio::toModelType);
    }
}

