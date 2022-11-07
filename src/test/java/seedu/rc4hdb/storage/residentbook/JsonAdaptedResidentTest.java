package seedu.rc4hdb.storage.residentbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.storage.residentbook.JsonAdaptedResident.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;

/**
 * Unit tests for {@link JsonAdaptedResident}.
 */
public class JsonAdaptedResidentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ROOM = "03_08";
    private static final String INVALID_GENDER = "k";
    private static final String INVALID_HOUSE = "q";
    private static final String INVALID_MATRIC_NUMBER = "a012343356a";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ROOM = BENSON.getRoom().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_HOUSE = BENSON.getHouse().toString();
    private static final String VALID_MATRIC_NUMBER = BENSON.getMatricNumber().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validResidentDetails_returnsResident() throws Exception {
        JsonAdaptedResident resident = new JsonAdaptedResident(BENSON);
        assertEquals(BENSON, resident.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROOM, VALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedResident resident = new JsonAdaptedResident(null, VALID_PHONE, VALID_EMAIL, VALID_ROOM,
                VALID_GENDER, VALID_HOUSE, VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ROOM, VALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, null, VALID_EMAIL, VALID_ROOM, VALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ROOM, VALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, null, VALID_ROOM, VALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidRoom_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ROOM, VALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = Room.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullRoom_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Room.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROOM, INVALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROOM, null, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidHouse_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROOM, VALID_GENDER, INVALID_HOUSE,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = House.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullHouse_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROOM, VALID_GENDER, null,
                        VALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, House.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidMatricNumber_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROOM, VALID_GENDER, VALID_HOUSE,
                        INVALID_MATRIC_NUMBER, VALID_TAGS);
        String expectedMessage = MatricNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_nullMatricNumber_throwsIllegalValueException() {
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ROOM, VALID_GENDER, VALID_HOUSE,
                        null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MatricNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, resident::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedResident resident =
                new JsonAdaptedResident(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_GENDER, VALID_HOUSE,
                        VALID_MATRIC_NUMBER, invalidTags);
        assertThrows(IllegalValueException.class, resident::toModelType);
    }
}
