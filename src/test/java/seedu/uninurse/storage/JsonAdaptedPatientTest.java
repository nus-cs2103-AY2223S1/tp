package seedu.uninurse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.uninurse.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalPatients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Phone;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CONDITION = " ";
    private static final String INVALID_MEDICATION_TYPE = " ";
    private static final String INVALID_MEDICATION_DOSAGE = "+";
    private static final String INVALID_TASK = " ";
    private static final String INVALID_REMARK = " ";
    private static final String INVALID_TAG = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedCondition> VALID_CONDITIONS = BENSON.getConditions()
            .getInternalList()
            .stream()
            .map(JsonAdaptedCondition::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedMedication> VALID_MEDICATIONS = BENSON.getMedications()
            .getInternalList()
            .stream()
            .map(JsonAdaptedMedication::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTask> VALID_TASKS = BENSON.getTasks().getInternalList().stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedRemark> VALID_REMARKS = BENSON.getRemarks().getInternalList().stream()
            .map(JsonAdaptedRemark::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().getInternalList().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPatient person = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidConditions_throwsIllegalValueException() {
        List<JsonAdaptedCondition> invalidConditions = new ArrayList<>(VALID_CONDITIONS);
        invalidConditions.add(new JsonAdaptedCondition(INVALID_CONDITION));
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                invalidConditions, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidMedications_throwsIllegalValueException() {
        List<JsonAdaptedMedication> invalidMedications = new ArrayList<>(VALID_MEDICATIONS);
        invalidMedications.add(new JsonAdaptedMedication(INVALID_MEDICATION_TYPE, INVALID_MEDICATION_DOSAGE));
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_CONDITIONS, invalidMedications, VALID_TASKS, VALID_REMARKS, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    // TODO: add tests for invalid tasks

    @Test
    public void toModelType_invalidRemarks_throwsIllegalValueException() {
        List<JsonAdaptedRemark> invalidRemarks = new ArrayList<>(VALID_REMARKS);
        invalidRemarks.add(new JsonAdaptedRemark(INVALID_REMARK));
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, invalidRemarks, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_CONDITIONS, VALID_MEDICATIONS, VALID_TASKS, VALID_REMARKS, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
