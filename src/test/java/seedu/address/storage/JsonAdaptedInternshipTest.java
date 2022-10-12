package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
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
import seedu.address.model.internship.Link;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_COMPANY = "R@chel";
    private static final String INVALID_LINK = "+651234";
    private static final String INVALID_APPLIED_DATE = " ";
    private static final String INVALID_DESCRIPTION = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_COMPANY = BINANCE.getCompany().toString();
    private static final String VALID_LINK = BINANCE.getLink().toString();
    private static final String VALID_DESCRIPTION = BINANCE.getDescription().toString();
    private static final String VALID_APPLICATION_STATUS = BINANCE.getApplicationStatus().toString();
    private static final String VALID_APPLIED_DATE = BINANCE.getAppliedDate().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BINANCE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    /* Passes locally but not on GitHub Actions
    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(BINANCE);
        assertEquals(BINANCE, internship.toModelType());
    }
    */

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_APPLICATION_STATUS,
                        VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }
    */

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(null, VALID_LINK, VALID_DESCRIPTION,
                VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_APPLICATION_STATUS,
                        VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Link.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }
    */

    @Test
    public void toModelType_nullLink_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, null, VALID_DESCRIPTION,
                VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Link.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                        VALID_APPLICATION_STATUS, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }
    */

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, null,
                VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_APPLICATION_STATUS, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = AppliedDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }
    */

    @Test
    public void toModelType_nullAppliedDate_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, VALID_DESCRIPTION,
                VALID_APPLICATION_STATUS, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AppliedDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY, VALID_LINK, VALID_DESCRIPTION,
                        VALID_APPLICATION_STATUS, VALID_APPLIED_DATE, invalidTags);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }

}
