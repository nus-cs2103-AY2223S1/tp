package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NextOfKin;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_HOSPITAL_WING = " ";
    private static final String INVALID_NEXT_OF_KIN = " ";
    private static final String INVALID_PATIENT_TYPE = "b";
    private static final String INVALID_FLOOR_NUMBER = "-1";
    private static final String INVALID_WARD_NUMBER = "bense";
    private static final String INVALID_MEDICATION = " ";
    private static final String[] INVALID_PAST_APPOINTMENT = {".", ".", "."};

    private static final String VALID_NAME = BENSON.getName().fullName;
    private static final String VALID_PHONE = BENSON.getPhone().value;
    private static final String VALID_EMAIL = BENSON.getEmail().value;
    private static final String VALID_NEXT_OF_KIN = BENSON.getNextOfKin().value;
    private static final String VALID_PATIENT_TYPE = BENSON.getPatientType().value.name();
    private static final String VALID_HOSPITAL_WING = BENSON.getHospitalWing().get().value;
    private static final String VALID_FLOOR_NUMBER = BENSON.getFloorNumber().get().value.toString();
    private static final String VALID_WARD_NUMBER = BENSON.getWardNumber().get().value.toString();
    private static final List<JsonAdaptedMedication> VALID_MEDICATIONS = BENSON.getMedications().stream()
            .map(JsonAdaptedMedication::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedPastAppointment> VALID_PAST_APPOINTMENTS = BENSON.getPastAppointments()
            .stream().map(JsonAdaptedPastAppointment::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NEXT_OF_KIN, VALID_PATIENT_TYPE,
                        VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                        VALID_PAST_APPOINTMENTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_NEXT_OF_KIN,
                VALID_PATIENT_TYPE, VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                VALID_PAST_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_NEXT_OF_KIN, VALID_PATIENT_TYPE,
                        VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                        VALID_PAST_APPOINTMENTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_NEXT_OF_KIN,
                VALID_PATIENT_TYPE, VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                VALID_PAST_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_NEXT_OF_KIN, VALID_PATIENT_TYPE,
                        VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                        VALID_PAST_APPOINTMENTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_NEXT_OF_KIN,
                VALID_PATIENT_TYPE, VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                VALID_PAST_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNextOfKin_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_NEXT_OF_KIN, VALID_PATIENT_TYPE,
                        VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                        VALID_PAST_APPOINTMENTS);
        String expectedMessage = NextOfKin.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNextOfKin_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_PATIENT_TYPE, VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                VALID_PAST_APPOINTMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NextOfKin.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMedications_throwsIllegalValueException() {
        List<JsonAdaptedMedication> invalidMedications = new ArrayList<>(VALID_MEDICATIONS);
        invalidMedications.add(new JsonAdaptedMedication(INVALID_MEDICATION));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_NEXT_OF_KIN, VALID_PATIENT_TYPE,
                        VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, invalidMedications,
                        VALID_PAST_APPOINTMENTS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPastAppointments_throwsIllegalValueException() {
        List<JsonAdaptedMedication> invalidMedications = new ArrayList<>(VALID_MEDICATIONS);
        invalidMedications.add(new JsonAdaptedMedication(INVALID_MEDICATION));
        List<JsonAdaptedPastAppointment> invalidPastAppointments = new ArrayList<>(VALID_PAST_APPOINTMENTS);
        invalidPastAppointments.add(new JsonAdaptedPastAppointment("", "", invalidMedications));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_NEXT_OF_KIN, VALID_PATIENT_TYPE,
                        VALID_HOSPITAL_WING, VALID_FLOOR_NUMBER, VALID_WARD_NUMBER, VALID_MEDICATIONS,
                        invalidPastAppointments);
    }
}
