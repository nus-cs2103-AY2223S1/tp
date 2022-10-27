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
import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GENDER = "4Male";
    private static final String INVALID_GRADUATION_DATE = "asdf-12";
    private static final String INVALID_CAP = "10/5";
    private static final String INVALID_UNIVERSITY = "@univ";
    private static final String INVALID_MAJOR = "C0MPUT3R $C1ENCE";
    private static final String INVALID_ID = "J9021-1";
    private static final String INVALID_TITLE = "Intern | Software Engineer";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_CAP = BENSON.getCap().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_GRADUATION_DATE = BENSON.getGraduationDate().toString();
    private static final String VALID_UNIVERSITY = BENSON.getUniversity().toString();
    private static final String VALID_MAJOR = BENSON.getMajor().toString();
    private static final String VALID_ID = BENSON.getJob().getId().toString();
    private static final String VALID_TITLE = BENSON.getJob().getTitle().toString();
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
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME.repeat(100), VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Name.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE.repeat(100), VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL.repeat(100),
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Email.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS.repeat(100),
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Address.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                INVALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                null,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGraduationDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_GENDER,
                        INVALID_GRADUATION_DATE,
                        VALID_CAP,
                        VALID_UNIVERSITY,
                        VALID_MAJOR,
                        VALID_ID,
                        VALID_TITLE,
                        VALID_TAGS);
        String expectedMessage = GraduationDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGraduationDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_GENDER,
                        null,
                        VALID_CAP,
                        VALID_UNIVERSITY,
                        VALID_MAJOR,
                        VALID_ID,
                        VALID_TITLE,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GraduationDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCap_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                INVALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Cap.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCap_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                null,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cap.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidUniversity_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                INVALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = University.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededUniversity_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY.repeat(100),
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = University.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullUniversity_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                null,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, University.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMajor_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                INVALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Major.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededMajor_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR.repeat(100),
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Major.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMajor_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                null,
                VALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Major.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                INVALID_ID,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID.repeat(100),
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Id.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                null,
                VALID_TITLE,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                INVALID_TITLE,
                VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededTitle_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE.repeat(100),
                VALID_TAGS);
        String expectedMessage = Title.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                null,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                invalidTags);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_lengthLimitExceededTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> lengthLimitExceededTags = new ArrayList<>(VALID_TAGS);
        lengthLimitExceededTags.add(new JsonAdaptedTag(INVALID_TAG.repeat(100)));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS,
                VALID_GENDER,
                VALID_GRADUATION_DATE,
                VALID_CAP,
                VALID_UNIVERSITY,
                VALID_MAJOR,
                VALID_ID,
                VALID_TITLE,
                lengthLimitExceededTags);
        String expectedMessage = Tag.MESSAGE_LENGTH_LIMIT_EXCEEDED;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
