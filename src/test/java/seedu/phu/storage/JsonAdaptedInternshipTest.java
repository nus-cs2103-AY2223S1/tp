package seedu.phu.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.phu.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.phu.testutil.Assert.assertThrows;
import static seedu.phu.testutil.TypicalInternships.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.phu.commons.exceptions.IllegalValueException;
import seedu.phu.model.internship.Email;
import seedu.phu.model.internship.Name;
import seedu.phu.model.internship.Phone;
import seedu.phu.model.internship.Remark;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_INTERNSHIP = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_POSITION = "intern @Google";
    private static final String INVALID_DATE = "12/12/12";
    private static final String INVALID_APPLICATION_PROCESS = "application";
    private static final String INVALID_WEBSITE = "www.invalid.com";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_INTERNSHIP = BENSON.getRemark().toString();
    private static final String VALID_POSITION = BENSON.getPosition().toString();
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final String VALID_APPLICATION_PROCESS = BENSON.getApplicationProcess().toString();
    private static final String VALID_WEBSITE = BENSON.getWebsite().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(BENSON);
        assertEquals(BENSON, internship.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_INTERNSHIP, VALID_POSITION,
                        VALID_APPLICATION_PROCESS, VALID_DATE, VALID_WEBSITE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(null, VALID_PHONE, VALID_EMAIL,
                VALID_INTERNSHIP, VALID_POSITION, VALID_APPLICATION_PROCESS, VALID_DATE, VALID_WEBSITE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_INTERNSHIP, VALID_POSITION,
                        VALID_APPLICATION_PROCESS, VALID_DATE, VALID_WEBSITE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_NAME, null, VALID_EMAIL,
                VALID_INTERNSHIP, VALID_POSITION, VALID_APPLICATION_PROCESS, VALID_DATE, VALID_WEBSITE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_INTERNSHIP, VALID_POSITION,
                        VALID_APPLICATION_PROCESS, VALID_DATE, VALID_WEBSITE, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_NAME, VALID_PHONE, null,
                VALID_INTERNSHIP, VALID_POSITION, VALID_APPLICATION_PROCESS, VALID_DATE, VALID_WEBSITE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_POSITION, VALID_APPLICATION_PROCESS, VALID_DATE, VALID_WEBSITE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_INTERNSHIP, VALID_POSITION,
                        VALID_APPLICATION_PROCESS, VALID_DATE, VALID_WEBSITE, invalidTags);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }

}
