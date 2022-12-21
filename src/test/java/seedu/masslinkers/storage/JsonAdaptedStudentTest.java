package seedu.masslinkers.storage;
//@@author jonasgwt
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.masslinkers.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.masslinkers.testutil.Assert.assertThrows;
import static seedu.masslinkers.testutil.TypicalStudents.BENSON;
import static seedu.masslinkers.testutil.TypicalStudents.TOM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.commons.exceptions.IllegalValueException;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Telegram;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "    ";
    private static final String INVALID_TELEGRAM = " ";
    private static final String INVALID_GITHUB = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_INTEREST = "#tennis";
    private static final String INVALID_MOD = "#CS2100";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_GITHUB = BENSON.getGitHub().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().toString();
    private static final List<JsonAdaptedInterest> VALID_INTERESTS = BENSON.getInterests().stream()
            .map(JsonAdaptedInterest::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedMod> VALID_MODS = BENSON.getMods().stream()
            .map(JsonAdaptedMod::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_nullOptionalFields_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(TOM);
        assertEquals(TOM, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM,
                        VALID_GITHUB, VALID_INTERESTS, VALID_MODS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_EMAIL,
            VALID_TELEGRAM, VALID_GITHUB, VALID_INTERESTS, VALID_MODS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_GITHUB, VALID_INTERESTS, VALID_MODS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_TELEGRAM, VALID_GITHUB, VALID_INTERESTS, VALID_MODS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }


    @Test
    public void toModelType_invalidHandle_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        INVALID_TELEGRAM, VALID_GITHUB, VALID_INTERESTS, VALID_MODS);
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidUsername_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, INVALID_GITHUB, VALID_INTERESTS, VALID_MODS);
        String expectedMessage = GitHub.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidInterests_throwsIllegalValueException() {
        List<JsonAdaptedInterest> invalidInterests = new ArrayList<>(VALID_INTERESTS);
        invalidInterests.add(new JsonAdaptedInterest(INVALID_INTEREST));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_GITHUB, invalidInterests, VALID_MODS);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidMods_throwsIllegalValueException() {
        List<JsonAdaptedMod> invalidMods = new ArrayList<>(VALID_MODS);
        invalidMods.add(new JsonAdaptedMod(INVALID_MOD, false));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_TELEGRAM, VALID_GITHUB, VALID_INTERESTS, invalidMods);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

}
