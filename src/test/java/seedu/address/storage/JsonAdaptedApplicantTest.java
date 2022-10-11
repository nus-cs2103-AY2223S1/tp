package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTrackAScholar.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.ApplicationStatus;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.applicant.Scholarship;

public class JsonAdaptedApplicantTest {

    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_SCHOLARSHIP = " ";
    private static final String INVALID_APPLICATION_STATUS = "failure";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_SCHOLARSHIP = BENSON.getScholarship().toString();
    private static final String VALID_APPLICATION_STATUS = BENSON.getApplicationStatus().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validApplicantDetails_returnsApplicant() throws Exception {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(BENSON);
        assertEquals(BENSON, applicant.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(INVALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(null,
                VALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                INVALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                null, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                VALID_PHONE, INVALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                VALID_PHONE, null, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidScholarship_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, INVALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = Scholarship.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullScholarship_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Scholarship.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, INVALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = ApplicationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_APPLICATION_STATUS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Scholarship.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTrackAScholar applicant = new JsonAdaptedTrackAScholar(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_SCHOLARSHIP, VALID_APPLICATION_STATUS, invalidTags);
        assertThrows(IllegalValueException.class, applicant::toModelType);
    }

}
