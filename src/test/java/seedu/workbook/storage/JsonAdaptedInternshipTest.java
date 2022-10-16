package seedu.workbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.workbook.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.workbook.testutil.Assert.assertThrows;
import static seedu.workbook.testutil.TypicalInternships.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.workbook.commons.exceptions.IllegalValueException;
import seedu.workbook.model.internship.Company;
import seedu.workbook.model.internship.Email;
import seedu.workbook.model.internship.Role;
import seedu.workbook.model.internship.Stage;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_COMPANY = "R@chel";
    private static final String INVALID_ROLE = "R@chel";
    private static final String INVALID_STAGE = "H@ Interview";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_COMPANY = BENSON.getCompany().toString();
    private static final String VALID_ROLE = BENSON.getRole().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_STAGE = BENSON.getStage().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(BENSON);
        assertEquals(BENSON, internship.toModelType());
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(INVALID_COMPANY, VALID_ROLE,
                VALID_EMAIL, VALID_STAGE, VALID_TAGS);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(null, VALID_ROLE,
                VALID_EMAIL, VALID_STAGE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, INVALID_ROLE,
                VALID_EMAIL, VALID_STAGE, VALID_TAGS);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, null, VALID_EMAIL,
                VALID_STAGE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }


    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_ROLE,
                INVALID_EMAIL, VALID_STAGE, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_ROLE, null,
                VALID_STAGE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidStage_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_ROLE,
                VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Stage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullStage_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_ROLE,
                VALID_EMAIL, INVALID_STAGE, VALID_TAGS);
        String expectedMessage = Stage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY, VALID_ROLE,
                VALID_EMAIL, VALID_STAGE, invalidTags);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }

}
