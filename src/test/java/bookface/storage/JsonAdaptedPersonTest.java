package bookface.storage;

import static bookface.storage.JsonAdaptedPerson.MISSING_PERSON_FIELD_MESSAGE_FORMAT;
import static bookface.testutil.Assert.assertThrows;
import static bookface.testutil.TypicalDates.TYPICAL_DATE_STRING;
import static bookface.testutil.TypicalPersons.HILLY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import bookface.commons.exceptions.IllegalValueException;
import bookface.model.person.Email;
import bookface.model.person.Name;
import bookface.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = HILLY.getName().toString();
    private static final String VALID_PHONE = HILLY.getPhone().toString();
    private static final String VALID_EMAIL = HILLY.getEmail().toString();

    private static final JsonAdaptedBook VALID_BOOK = new JsonAdaptedBook("The Deep Down",
            "Kerry Abiguidea", TYPICAL_DATE_STRING, true);
    private static final List<JsonAdaptedBook> VALID_LOANS = List.of(VALID_BOOK);
    private static final JsonAdaptedBook INVALID_BOOK = new JsonAdaptedBook("The Deep Down",
            "Kerry Abiguidea", TYPICAL_DATE_STRING, false);
    private static final List<JsonAdaptedTag> VALID_TAGS = HILLY.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(HILLY);
        assertEquals(HILLY, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOANS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_LOANS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_PERSON_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_LOANS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_LOANS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_PERSON_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_LOANS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_LOANS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_PERSON_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOANS, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(null);
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_LOANS, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidLoanedBooks_throwsIllegalValueException() {
        List<JsonAdaptedBook> invalidBooks = new ArrayList<>(VALID_LOANS);
        invalidBooks.add(INVALID_BOOK);
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidBooks, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullLoanedBooks_throwsIllegalValueException() {
        List<JsonAdaptedBook> invalidBooks = new ArrayList<>(VALID_LOANS);
        invalidBooks.add(null);
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidBooks, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
