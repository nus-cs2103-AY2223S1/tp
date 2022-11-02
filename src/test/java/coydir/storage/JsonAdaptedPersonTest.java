package coydir.storage;

import static coydir.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static coydir.testutil.Assert.assertThrows;
import static coydir.testutil.TypicalPersons.BENSON;
import static coydir.testutil.TypicalPersons.PRITTAM;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import coydir.commons.exceptions.IllegalValueException;
import coydir.logic.commands.CommandTestUtil;
import coydir.model.person.Address;
import coydir.model.person.Department;
import coydir.model.person.Email;
import coydir.model.person.EmployeeId;
import coydir.model.person.Name;
import coydir.model.person.Phone;
import coydir.model.person.Position;
import coydir.testutil.TestUtil;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_EMPLOYEE_ID = "abc";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_POSITION = " ";
    private static final String INVALID_DEPARTMENT = "Invalid";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String ORIGINAL_EMPLOYEE_ID = BENSON.getEmployeeId().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_POSITION = BENSON.getPosition().toString();
    private static final String VALID_DEPARTMENT = BENSON.getDepartment().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedLeave> VALID_LEAVE = new ArrayList<>();
    private static final String VALID_RATING = BENSON.getRating().toString();
    private static final List<JsonAdaptedRating> VALID_PERFORMANCEHISTORY = new ArrayList<>();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        TestUtil.restartEmployeeId(Integer.parseInt(ORIGINAL_EMPLOYEE_ID) + 1);
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_validPersonDetailsNaValues_returnsPerson() throws IllegalValueException {
        TestUtil.restartEmployeeId(Integer.parseInt(CommandTestUtil.VALID_EMPLOYEE_ID_PRITTAM) + 1);
        JsonAdaptedPerson person = new JsonAdaptedPerson(PRITTAM);
        assertEquals(PRITTAM, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                INVALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, VALID_POSITION,
                VALID_DEPARTMENT, VALID_ADDRESS, "14", "14", VALID_TAGS, VALID_LEAVE,
                VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                null, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT,
                VALID_ADDRESS, "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_stringEmployeeId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, INVALID_EMPLOYEE_ID, VALID_PHONE, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT,
                VALID_ADDRESS, "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = EmployeeId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmployeeId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, null, VALID_PHONE, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT, VALID_ADDRESS,
                "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EmployeeId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmployeeId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, CommandTestUtil.VALID_EMPLOYEE_ID_PRITTAM, VALID_PHONE, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT, VALID_ADDRESS,
                "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = EmployeeId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), INVALID_PHONE, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT,
                VALID_ADDRESS, "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), null, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT, VALID_ADDRESS,
                "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, INVALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT,
                VALID_ADDRESS, "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, null, VALID_POSITION, VALID_DEPARTMENT, VALID_ADDRESS,
                "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPosition_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, INVALID_POSITION, VALID_DEPARTMENT,
                VALID_ADDRESS, "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = Position.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, null, VALID_DEPARTMENT, VALID_ADDRESS,
                "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDepartment_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, VALID_POSITION, INVALID_DEPARTMENT,
                VALID_ADDRESS, "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = Department.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDepartment_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, VALID_POSITION, null, VALID_ADDRESS,
                "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Department.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT,
                INVALID_ADDRESS, "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT, null,
                "14", "14", VALID_TAGS, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, TestUtil.getNextEmployeeId(), VALID_PHONE, VALID_EMAIL, VALID_POSITION, VALID_DEPARTMENT,
                VALID_ADDRESS, "14", "14", invalidTags, VALID_LEAVE, VALID_RATING, VALID_PERFORMANCEHISTORY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
