package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPoc.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPoc.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.poc.Email;
import seedu.address.model.poc.PersonName;
import seedu.address.model.poc.Phone;

public class JsonAdaptedPocTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "@98765";
    private static final String INVALID_EMAIL = "abcde";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().value;
    private static final String VALID_EMAIL = BENSON.getEmail().value;
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPocDetails_returnsPoc() throws Exception {
        JsonAdaptedPoc poc = new JsonAdaptedPoc(BENSON);
        assertEquals(BENSON, poc.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPoc poc =
                new JsonAdaptedPoc(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = PersonName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, poc::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPoc poc = new JsonAdaptedPoc(null, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PersonName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, poc::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPoc poc =
                new JsonAdaptedPoc(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, poc::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPoc poc = new JsonAdaptedPoc(VALID_NAME, null, VALID_EMAIL, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, poc::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPoc poc =
                new JsonAdaptedPoc(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidTags);
        assertThrows(IllegalValueException.class, poc::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPoc poc =
                new JsonAdaptedPoc(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, poc::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPoc poc = new JsonAdaptedPoc(VALID_NAME, VALID_PHONE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, poc::toModelType);
    }

}
