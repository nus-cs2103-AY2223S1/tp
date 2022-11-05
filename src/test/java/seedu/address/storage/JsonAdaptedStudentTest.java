package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Email;
import seedu.address.model.student.Money;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_NOK_PHONE = "+659999";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final Integer INVALID_MONEY_OWED = -123;
    private static final Integer INVALID_MONEY_PAID = -145;
    private static final Integer INVALID_RATES_PER_CLASS = -100;
    private static final String INVALID_CLASS_DATE_TIME = "2022,04,19 1530-1630";
    private static final String INVALID_TAG = "#python";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_NOK_PHONE = BENSON.getNokPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final Integer VALID_MONEY_OWED = BENSON.getMoneyOwed().value;
    private static final Integer VALID_MONEY_PAID = BENSON.getMoneyPaid().value;
    private static final Integer VALID_RATES_PER_CLASS = BENSON.getRatesPerClass().value;
    private static final String VALID_ADDITIONAL_NOTES = BENSON.getAdditionalNotes().toString();
    private static final String VALID_CLASS_DATE_TIME = "2022-03-03 1300-1500";
    private static final Boolean VALID_ATTENDANCE_STATUS = BENSON.getMarkStatus().isMarked();

    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidNokPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID, VALID_RATES_PER_CLASS,
                VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullNokPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, null,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);

        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                null, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidClassDateTime_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, INVALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);
        String expectedMessage = Class.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidMoneyOwed_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, INVALID_MONEY_OWED, VALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);
        String expectedMessage = Money.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidMoneyPaid_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, INVALID_MONEY_PAID,
                VALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);
        String expectedMessage = Money.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidRatesPerClass_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID,
                INVALID_RATES_PER_CLASS, VALID_ADDITIONAL_NOTES, VALID_TAGS, VALID_ATTENDANCE_STATUS);
        String expectedMessage = Money.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_NOK_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_CLASS_DATE_TIME, VALID_MONEY_OWED, VALID_MONEY_PAID, VALID_RATES_PER_CLASS,
                        VALID_ADDITIONAL_NOTES, invalidTags, VALID_ATTENDANCE_STATUS);
        assertThrows(IllegalValueException.class, student::toModelType);
    }
}
