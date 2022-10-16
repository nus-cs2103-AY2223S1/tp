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
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_GENDER = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TYPE = "s";
    private static final String VALID_MODULE_CODE = "";
    private static final List<JsonAdaptedModuleCode> VALID_MODULE_CODES = ((Student) BENSON)
            .getModuleCodes().stream().map(JsonAdaptedModuleCode::new).collect(Collectors.toList());
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());
    private static final String VALID_LOCATION = BENSON.getLocation().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedStudent((Student) BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, INVALID_NAME, VALID_MODULE_CODE,
                    VALID_MODULE_CODES, VALID_PHONE, VALID_EMAIL, VALID_GENDER, VALID_TAGS, VALID_LOCATION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, null, VALID_MODULE_CODE,
                    VALID_MODULE_CODES, VALID_PHONE, VALID_EMAIL, VALID_GENDER, VALID_TAGS, VALID_LOCATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, VALID_NAME, VALID_MODULE_CODE,
                    VALID_MODULE_CODES, INVALID_PHONE, VALID_EMAIL, VALID_GENDER, VALID_TAGS, VALID_LOCATION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, VALID_NAME, VALID_MODULE_CODE, VALID_MODULE_CODES,
                    null, VALID_EMAIL, VALID_GENDER, VALID_TAGS, VALID_LOCATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, VALID_NAME, VALID_MODULE_CODE, VALID_MODULE_CODES,
                    VALID_PHONE, INVALID_EMAIL, VALID_GENDER, VALID_TAGS, VALID_LOCATION);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, VALID_NAME, VALID_MODULE_CODE, VALID_MODULE_CODES,
                    VALID_PHONE, null, VALID_GENDER, VALID_TAGS, VALID_LOCATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, VALID_NAME, VALID_MODULE_CODE, VALID_MODULE_CODES,
                    VALID_PHONE, VALID_EMAIL, INVALID_GENDER, VALID_TAGS, VALID_LOCATION);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, VALID_NAME, VALID_MODULE_CODE, VALID_MODULE_CODES,
                    VALID_PHONE, VALID_EMAIL, null, VALID_TAGS, VALID_LOCATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
            new JsonAdaptedStudent(VALID_TYPE, VALID_NAME, VALID_MODULE_CODE,
                    VALID_MODULE_CODES, VALID_PHONE, VALID_EMAIL, null, invalidTags, VALID_LOCATION);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
