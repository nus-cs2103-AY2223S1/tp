package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.BINANCE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.InterviewDateTime;
import seedu.address.model.internship.Link;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_COMPANY = "";
    private static final String INVALID_LINK = "https://www.example.";
    private static final String INVALID_APPLIED_DATE = " ";
    private static final String INVALID_INTERVIEW_DATE_TIME = " Tuesday";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_COMPANY = BINANCE.getCompany().toString();
    private static final String VALID_LINK = BINANCE.getLink().toString();
    private static final String VALID_DESCRIPTION = BINANCE.getDescription().toString();
    private static final String VALID_APPLICATION_STATUS = BINANCE.getApplicationStatus().toString();
    private static final String VALID_APPLIED_DATE = BINANCE.getAppliedDate().toString();
    private static final String VALID_INTERVIEW_DATE_TIME = BINANCE.getInterviewDateTime().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BINANCE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(BINANCE);
        assertEquals(BINANCE, internship.toModelType());
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(INVALID_COMPANY, VALID_LINK, VALID_DESCRIPTION, VALID_APPLICATION_STATUS,
                        VALID_APPLIED_DATE, VALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(null, VALID_LINK, VALID_DESCRIPTION,
                VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, VALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidLink_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY, INVALID_LINK, VALID_DESCRIPTION, VALID_APPLICATION_STATUS,
                        VALID_APPLIED_DATE, VALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = Link.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullLink_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, null, VALID_DESCRIPTION,
                VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, VALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Link.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, INVALID_DESCRIPTION,
                        VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, VALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, null,
                VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, VALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidAppliedDate_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, VALID_DESCRIPTION,
                        VALID_APPLICATION_STATUS, INVALID_APPLIED_DATE, VALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = AppliedDate.FORMAT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullAppliedDate_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, VALID_DESCRIPTION,
                VALID_APPLICATION_STATUS, null, VALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AppliedDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidInterviewDateTime_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, VALID_DESCRIPTION,
                        VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, INVALID_INTERVIEW_DATE_TIME, VALID_TAGS);
        String expectedMessage = InterviewDateTime.FORMAT_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullInterviewDateTime_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, VALID_DESCRIPTION,
                VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, VALID_DESCRIPTION,
                        VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, VALID_INTERVIEW_DATE_TIME, invalidTags);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }

}
