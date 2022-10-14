package seedu.application.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.storage.JsonAdaptedApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalApplications.SHOPEE;

import org.junit.jupiter.api.Test;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;

public class JsonAdaptedApplicationTest {
    private static final String INVALID_COMPANY = "Go@gle";
    private static final String INVALID_CONTACT = "+651234";
    private static final String INVALID_EMAIL = "example.com";

    private static final String INVALID_POSITION = "Software_Engineering";
    private static final String INVALID_DATE = "23-09-2022";

    private static final String VALID_COMPANY = SHOPEE.getCompany().company;
    private static final String VALID_CONTACT = SHOPEE.getContact().toString();
    private static final String VALID_EMAIL = SHOPEE.getEmail().toString();
    private static final String VALID_POSITION = SHOPEE.getPosition().toString();
    private static final String VALID_DATE = SHOPEE.getDate().value.toString();

    @Test
    public void toModelType_validApplicationDetails_returnsPerson() throws Exception {
        JsonAdaptedApplication application = new JsonAdaptedApplication(SHOPEE);
        assertEquals(SHOPEE, application.toModelType());
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(INVALID_COMPANY, VALID_CONTACT, VALID_EMAIL,
                        VALID_POSITION, VALID_DATE);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(null, VALID_CONTACT, VALID_EMAIL,
                VALID_POSITION, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidContact_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, INVALID_CONTACT, VALID_EMAIL,
                VALID_POSITION, VALID_DATE);
        String expectedMessage = Contact.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullContact_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, null, VALID_EMAIL,
                VALID_POSITION, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Contact.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_CONTACT, INVALID_EMAIL,
                VALID_POSITION, VALID_DATE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_CONTACT, null,
                VALID_POSITION, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidPosition_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_CONTACT, VALID_EMAIL,
                INVALID_POSITION, VALID_DATE);
        String expectedMessage = Position.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_CONTACT, VALID_EMAIL,
                null, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_CONTACT, VALID_EMAIL,
                VALID_POSITION, INVALID_DATE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_CONTACT, VALID_EMAIL,
                VALID_POSITION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

}
