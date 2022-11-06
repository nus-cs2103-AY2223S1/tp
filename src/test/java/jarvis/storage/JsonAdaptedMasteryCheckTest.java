package jarvis.storage;

import static jarvis.storage.JsonAdaptedMasteryCheck.MISSING_FIELD_MESSAGE_FORMAT;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalLessons.MC_1;
import static jarvis.testutil.TypicalStudents.ALICE;
import static jarvis.testutil.TypicalStudents.BENSON;
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
import jarvis.model.TimePeriod;

public class JsonAdaptedMasteryCheckTest {
    private static final String VALID_DESC = "Mastery check";
    private static final LocalDateTime DT1 = LocalDateTime.of(2022,
            11, 11, 12, 0);
    private static final LocalDateTime DT2 = LocalDateTime.of(2022,
            11, 11, 13, 0);

    private static final ArrayList<JsonAdaptedStudent> VALID_STUDENT_LIST =
            new ArrayList<>(List.of(new JsonAdaptedStudent(ALICE), new JsonAdaptedStudent(BENSON)));
    private static final HashMap<Integer, Boolean> VALID_ATTENDANCE = new HashMap<>();
    private static ArrayList<String> validGeneralNotes;
    private static Map<Integer, ArrayList<String>> validStudentNotes;

    @BeforeEach
    public void setUp() {
        VALID_ATTENDANCE.put(1, false);
        VALID_ATTENDANCE.put(0, false);
        validGeneralNotes = new ArrayList<>();
        validGeneralNotes.addAll(List.of("General"));
        validStudentNotes = new HashMap<>();
        validStudentNotes.put(0, new ArrayList<>(List.of("1", "2")));
    }

    @Test
    public void toModelType_validMasteryCheckDetails_returnsMasteryCheck() throws Exception {
        JsonAdaptedMasteryCheck masteryCheck = new JsonAdaptedMasteryCheck(MC_1);
        assertEquals(MC_1, masteryCheck.toModelType());
    }

    @Test
    public void toModelType_invalidTimePeriod_throwsIllegalArgumentException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_DESC, DT2, DT1, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        validGeneralNotes, validStudentNotes, false);
        String expectedMessage = TimePeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalArgumentException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck((String) null, (LocalDateTime) null, DT1, VALID_STUDENT_LIST,
                        VALID_ATTENDANCE, validGeneralNotes, validStudentNotes, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimePeriod.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_nullStudents_throwsIllegalArgumentException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck((String) null, DT1, DT2, null, VALID_ATTENDANCE,
                        validGeneralNotes, validStudentNotes, false);
        String expectedMessage = String.format(JsonAdaptedMasteryCheck.MISSING_FIELD_MESSAGE_FORMAT,
                Student.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalArgumentException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck((String) null, DT1, DT2, VALID_STUDENT_LIST, null,
                        validGeneralNotes, validStudentNotes, false);
        String expectedMessage = String.format(JsonAdaptedMasteryCheck.MISSING_FIELD_MESSAGE_FORMAT,
                LessonAttendance.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_nullGeneralNotes_throwsIllegalArgumentException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck((String) null, DT1, DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        null, validStudentNotes, false);
        String expectedMessage = String.format(JsonAdaptedMasteryCheck.MISSING_FIELD_MESSAGE_FORMAT,
                LessonNotes.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_nullStudentNotes_throwsIllegalArgumentException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck((String) null, DT1, DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        validGeneralNotes, null, false);
        String expectedMessage = String.format(JsonAdaptedMasteryCheck.MISSING_FIELD_MESSAGE_FORMAT,
                LessonNotes.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, masteryCheck::toModelType);
    }
}


