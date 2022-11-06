package jarvis.storage;

import static jarvis.storage.JsonAdaptedStudio.MISSING_FIELD_MESSAGE_FORMAT;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalLessons.STUDIO_1;
import static jarvis.testutil.TypicalStudents.getTypicalStudents;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jarvis.model.LessonAttendance;
import jarvis.model.LessonNotes;
import jarvis.model.Student;
import jarvis.model.StudioParticipation;
import jarvis.model.TimePeriod;

public class JsonAdaptedStudioTest {
    private static final String VALID_DESC = "Lesson";
    private static final LocalDateTime DT1 = LocalDateTime.of(2022,
            11, 11, 12, 0);
    private static final LocalDateTime DT2 = LocalDateTime.of(2022,
            11, 11, 13, 0);

    private static final ArrayList<JsonAdaptedStudent> VALID_STUDENT_LIST =
            new ArrayList<>();
    private static final HashMap<Integer, Boolean> VALID_ATTENDANCE = new HashMap<>();
    private static ArrayList<String> validGeneralNotes;
    private static Map<Integer, ArrayList<String>> VALID_STUDENT_NOTES;
    private static Map<Integer, Integer> VALID_PARTICIPATION;

    @BeforeEach
    public void setUp() {
        for (Student s : getTypicalStudents()) {
            VALID_STUDENT_LIST.add(new JsonAdaptedStudent(s));
        }
        VALID_ATTENDANCE.put(1, false);
        VALID_ATTENDANCE.put(0, false);
        validGeneralNotes = new ArrayList<>();
        validGeneralNotes.addAll(List.of("General"));
        VALID_STUDENT_NOTES = new HashMap<>();
        VALID_STUDENT_NOTES.put(0, new ArrayList<>(List.of("1", "2")));
        VALID_PARTICIPATION = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            VALID_PARTICIPATION.put(i, 100);
        }
    }

    @Test
    public void toModelType_validStudioDetails_returnsStudio() throws Exception {
        JsonAdaptedStudio Studio = new JsonAdaptedStudio(STUDIO_1);
        assertEquals(STUDIO_1, Studio.toModelType());
    }

    @Test
    public void toModelType_invalidTimePeriod_throwsIllegalArgumentException() {
        JsonAdaptedStudio Studio =
                new JsonAdaptedStudio(VALID_DESC, DT2, DT1, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        validGeneralNotes, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = TimePeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, Studio::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalArgumentException() {
        JsonAdaptedStudio Studio =
                new JsonAdaptedStudio((String) null, (LocalDateTime) null, DT1, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        validGeneralNotes, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimePeriod.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, Studio::toModelType);
    }

    @Test
    public void toModelType_nullStudents_throwsIllegalArgumentException() {
        JsonAdaptedStudio Studio =
                new JsonAdaptedStudio((String) null, DT1, DT2, null, VALID_ATTENDANCE,
                        validGeneralNotes, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(JsonAdaptedStudio.MISSING_FIELD_MESSAGE_FORMAT, Student.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, Studio::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalArgumentException() {
        JsonAdaptedStudio Studio =
                new JsonAdaptedStudio((String) null, DT1, DT2, VALID_STUDENT_LIST, null,
                        validGeneralNotes, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(JsonAdaptedStudio.MISSING_FIELD_MESSAGE_FORMAT, LessonAttendance.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, Studio::toModelType);
    }

    @Test
    public void toModelType_nullGeneralNotes_throwsIllegalArgumentException() {
        JsonAdaptedStudio Studio =
                new JsonAdaptedStudio((String) null, DT1, DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        null, VALID_STUDENT_NOTES, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(JsonAdaptedStudio.MISSING_FIELD_MESSAGE_FORMAT, LessonNotes.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, Studio::toModelType);
    }

    @Test
    public void toModelType_nullStudentNotes_throwsIllegalArgumentException() {
        JsonAdaptedStudio Studio =
                new JsonAdaptedStudio((String) null, DT1, DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        validGeneralNotes, null, false, VALID_PARTICIPATION);
        String expectedMessage = String.format(JsonAdaptedStudio.MISSING_FIELD_MESSAGE_FORMAT, LessonNotes.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, Studio::toModelType);
    }

    @Test
    public void toModelType_nullParticipation_throwsIllegalArgumentException() {
        JsonAdaptedStudio Studio =
                new JsonAdaptedStudio((String) null, DT1, DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        validGeneralNotes, VALID_STUDENT_NOTES, false, null);
        String expectedMessage = String.format(JsonAdaptedStudio.MISSING_FIELD_MESSAGE_FORMAT,
                StudioParticipation.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, Studio::toModelType);
    }
}

