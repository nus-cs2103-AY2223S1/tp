package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStaff.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStaff.NEVER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.staff.StaffContact;
import seedu.address.model.staff.StaffDepartment;
import seedu.address.model.staff.StaffInsurance;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.StaffTitle;

public class JsonAdaptedStaffTest {
    private static final String INVALID_STAFF_NAME = "R@chel";
    private static final String INVALID_STAFF_CONTACT = "+651234";
    private static final String INVALID_STAFF_TITLE = "2022-05";
    private static final String INVALID_STAFF_DEPARTMENT = "%4+@";
    private static final String INVALID_STAFF_INSURANCE = "Invalid";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_STAFF_NAME = NEVER.getStaffName().toString();
    private static final String VALID_STAFF_CONTACT = NEVER.getStaffContact().toString();
    private static final String VALID_STAFF_TITLE = NEVER.getStaffTitle().toString();
    private static final String VALID_STAFF_DEPARTMENT = NEVER.getStaffDepartment().toString();
    private static final String VALID_STAFF_INSURANCE = NEVER.getStaffInsurance().toString();
    private static final List<JsonAdaptedStaff> VALID_STAFF = new ArrayList<>(
            Arrays.asList(new JsonAdaptedStaff(NEVER)));
    private static final List<JsonAdaptedTag> VALID_TAGS = NEVER.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStaffDetails_returnsStaff() throws Exception {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(NEVER);
        assertEquals(NEVER, staff.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(INVALID_STAFF_NAME, VALID_STAFF_CONTACT,
                        VALID_STAFF_TITLE, VALID_STAFF_DEPARTMENT, VALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = StaffName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(null, VALID_STAFF_CONTACT, VALID_STAFF_TITLE,
                VALID_STAFF_DEPARTMENT, VALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StaffName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidContact_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_STAFF_NAME, INVALID_STAFF_CONTACT, VALID_STAFF_TITLE,
                        VALID_STAFF_DEPARTMENT, VALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = StaffContact.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullContact_throwsIllegalValueException() {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(VALID_STAFF_NAME, null,
                VALID_STAFF_TITLE, VALID_STAFF_DEPARTMENT, VALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StaffContact.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_STAFF_NAME, VALID_STAFF_CONTACT, INVALID_STAFF_TITLE,
                        VALID_STAFF_DEPARTMENT, VALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = StaffTitle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(VALID_STAFF_NAME, VALID_STAFF_CONTACT, null,
                VALID_STAFF_DEPARTMENT, VALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StaffTitle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidDepartment_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_STAFF_NAME, VALID_STAFF_CONTACT, VALID_STAFF_TITLE,
                        INVALID_STAFF_DEPARTMENT, VALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = StaffDepartment.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullDepartment_throwsIllegalValueException() {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(VALID_STAFF_NAME, VALID_STAFF_CONTACT,
                VALID_STAFF_TITLE, null, VALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StaffDepartment.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidInsurance_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_STAFF_NAME, VALID_STAFF_CONTACT, VALID_STAFF_TITLE,
                        VALID_STAFF_DEPARTMENT, INVALID_STAFF_INSURANCE, VALID_TAGS);
        String expectedMessage = StaffInsurance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullInsurance_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_STAFF_NAME, VALID_STAFF_CONTACT, VALID_STAFF_TITLE,
                        VALID_STAFF_DEPARTMENT, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                StaffInsurance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_STAFF_NAME, VALID_STAFF_CONTACT,
                        VALID_STAFF_TITLE, VALID_STAFF_DEPARTMENT, VALID_STAFF_INSURANCE, invalidTags);
        assertThrows(IllegalValueException.class, staff::toModelType);
    }

}

