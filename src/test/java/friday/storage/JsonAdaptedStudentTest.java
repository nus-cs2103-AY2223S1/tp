package friday.storage;

import static friday.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import friday.model.student.Consultation;
import friday.model.student.TelegramHandle;
import org.junit.jupiter.api.Test;

import friday.commons.exceptions.IllegalValueException;
import friday.model.student.MasteryCheck;
import friday.model.student.Name;
import friday.testutil.TypicalPersons;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TELEGRAMHANDLE = "+john--";
    private static final LocalDate INVALID_CONSULTATION = LocalDate.parse("2022/09/01");
    private static final LocalDate INVALID_MASTERYCHECK = LocalDate.parse("August 25th");
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TypicalPersons.BENSON.getName().toString();
    private static final String VALID_TELEGRAMHANDLE = TypicalPersons.BENSON.getTelegramHandle().toString();
    private static final LocalDate VALID_CONSULTATION =
            LocalDate.parse(TypicalPersons.BENSON.getConsultation().toString());
    private static final LocalDate VALID_MASTERYCHECK =
            LocalDate.parse(TypicalPersons.BENSON.getMasteryCheck().toString());
    private static final String VALID_REMARK = TypicalPersons.BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalPersons.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TypicalPersons.BENSON);
        assertEquals(TypicalPersons.BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, VALID_MASTERYCHECK, VALID_REMARK, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, VALID_MASTERYCHECK, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_TELEGRAMHANDLE, VALID_CONSULTATION, VALID_MASTERYCHECK, VALID_REMARK, VALID_TAGS);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_CONSULTATION, VALID_MASTERYCHECK,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidConsultation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAMHANDLE, INVALID_CONSULTATION, VALID_MASTERYCHECK,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = Consultation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMasteryCheck_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, INVALID_MASTERYCHECK,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = MasteryCheck.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, VALID_MASTERYCHECK,
                        VALID_REMARK, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    /*
    Commenting these 2 out for now as consultation and mastery check can be null?

    @Test
    public void toModelType_nullConsultation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAMHANDLE, null, VALID_MASTERYCHECK, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Consultation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMasteryCheck_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAMHANDLE, VALID_CONSULTATION, null,
                        VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MasteryCheck.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
     */
}
