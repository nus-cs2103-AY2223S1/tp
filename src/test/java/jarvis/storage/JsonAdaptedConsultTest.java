package jarvis.storage;

import static jarvis.storage.JsonAdaptedConsult.MISSING_FIELD_MESSAGE_FORMAT;
import static jarvis.testutil.Assert.assertThrows;
import static jarvis.testutil.TypicalLessons.CONSULT_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import jarvis.model.LessonAttendance;
import jarvis.model.LessonNotes;
import jarvis.model.Student;
import jarvis.model.TimePeriod;

public class JsonAdaptedConsultTest {
    private static final String VALID_DESC = CONSULT_1.getDesc().toString();
    private static final LocalDateTime VALID_DT1 = CONSULT_1.startDateTime();
    private static final LocalDateTime VALID_DT2 = CONSULT_1.endDateTime();
    private static final ArrayList<JsonAdaptedStudent> VALID_STUDENT_LIST = CONSULT_1.getStudentList().stream()
            .map(s -> new JsonAdaptedStudent(s)).collect(Collectors.toCollection(ArrayList::new));
    private static final Map<Integer, Boolean> VALID_ATTENDANCE = CONSULT_1.getAttendance();
    private static final ArrayList<String> VALID_GENERAL_NOTES = CONSULT_1.getGeneralNotes();
    private static final Map<Integer, ArrayList<String>> VALID_STUDENT_NOTES = CONSULT_1.getStudentNotes();


    @Test
    public void toModelType_validConsultDetails_returnsConsult() throws Exception {
        JsonAdaptedConsult consult = new JsonAdaptedConsult(CONSULT_1);
        assertEquals(CONSULT_1, consult.toModelType());
    }

    @Test
    public void toModelType_invalidTimePeriod_throwsIllegalArgumentException() {
        JsonAdaptedConsult consult =
                new JsonAdaptedConsult(VALID_DESC, VALID_DT2, VALID_DT1, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false);
        String expectedMessage = TimePeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, consult::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalArgumentException() {
        JsonAdaptedConsult consult =
                new JsonAdaptedConsult((String) null, (LocalDateTime) null, VALID_DT1, VALID_STUDENT_LIST,
                        VALID_ATTENDANCE, VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimePeriod.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, consult::toModelType);
    }

    @Test
    public void toModelType_nullStudents_throwsIllegalArgumentException() {
        JsonAdaptedConsult consult =
                new JsonAdaptedConsult((String) null, VALID_DT1, VALID_DT2, null, VALID_ATTENDANCE,
                        VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Student.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, consult::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalArgumentException() {
        JsonAdaptedConsult consult =
                new JsonAdaptedConsult((String) null, VALID_DT1, VALID_DT2, VALID_STUDENT_LIST, null,
                        VALID_GENERAL_NOTES, VALID_STUDENT_NOTES, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonAttendance.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, consult::toModelType);
    }

    @Test
    public void toModelType_nullGeneralNotes_throwsIllegalArgumentException() {
        JsonAdaptedConsult consult =
                new JsonAdaptedConsult((String) null, VALID_DT1, VALID_DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        null, VALID_STUDENT_NOTES, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonNotes.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, consult::toModelType);
    }

    @Test
    public void toModelType_nullStudentNotes_throwsIllegalArgumentException() {
        JsonAdaptedConsult consult =
                new JsonAdaptedConsult((String) null, VALID_DT1, VALID_DT2, VALID_STUDENT_LIST, VALID_ATTENDANCE,
                        VALID_GENERAL_NOTES, null, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LessonNotes.class.getSimpleName());
        assertThrows(IllegalArgumentException.class, expectedMessage, consult::toModelType);
    }
}

