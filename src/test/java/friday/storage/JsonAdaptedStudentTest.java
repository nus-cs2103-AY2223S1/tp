package friday.storage;

import static friday.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import friday.commons.exceptions.IllegalValueException;
import friday.model.student.Consultation;
import friday.model.student.MasteryCheck;
import friday.model.student.Name;
import friday.model.student.TelegramHandle;
import friday.testutil.TypicalStudents;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TELEGRAMHANDLE = "+john--";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalStudents.BENSON.getName().toString();
    private static final String VALID_TELEGRAMHANDLE = TypicalStudents.BENSON.getTelegramHandle().value;
    private static final LocalDate VALID_CONSULTATION =
            LocalDate.parse(TypicalStudents.BENSON.getConsultation().getValue().toString());
    private static final LocalDate VALID_MASTERYCHECK =
            LocalDate.parse(TypicalStudents.BENSON.getMasteryCheck().getValue().toString());
    private static final String VALID_REMARK = TypicalStudents.BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalStudents.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(TypicalStudents.BENSON);
        assertEquals(TypicalStudents.BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(INVALID_NAME, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, VALID_MASTERYCHECK,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(null, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, VALID_MASTERYCHECK,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, INVALID_TELEGRAMHANDLE, VALID_CONSULTATION, VALID_MASTERYCHECK,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, null, VALID_CONSULTATION, VALID_MASTERYCHECK,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, VALID_MASTERYCHECK,
                        VALID_REMARK, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }


    @Test
    public void toModelType_nullConsultation_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAMHANDLE, null, VALID_MASTERYCHECK, VALID_REMARK,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Consultation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMasteryCheck_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, null,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MasteryCheck.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
