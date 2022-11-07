package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOCTOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEDICAL_TEST_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SLOT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOCTOR_CAITIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_TEST_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SLOT_7;
import static seedu.address.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Name;

public class JsonAdaptedAppointmentTest {
    public static final String VALID_NAME = "Benson";
    private static final String INVALID_NAME = "R@chel";
    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(APPOINTMENT_1);
        assertEquals(APPOINTMENT_1, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(INVALID_NAME, VALID_MEDICAL_TEST_7, VALID_SLOT_7, VALID_DOCTOR_CAITIE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(null, VALID_MEDICAL_TEST_7, VALID_SLOT_7, INVALID_DOCTOR_DESC);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidMedicalTest_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NAME, INVALID_MEDICAL_TEST_DESC, VALID_SLOT_7, null);
        String expectedMessage = MedicalTest.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullMedicalTest_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NAME, null, VALID_SLOT_7, VALID_DOCTOR_CAITIE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MedicalTest.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidSlot_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NAME, VALID_MEDICAL_TEST_7, INVALID_SLOT_DESC, VALID_DOCTOR_CAITIE);
        String expectedMessage = Slot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullSlot_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NAME, VALID_MEDICAL_TEST_7, null, VALID_DOCTOR_CAITIE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Slot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidDoctor_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NAME, VALID_MEDICAL_TEST_7, VALID_SLOT_7, INVALID_DOCTOR_DESC);
        String expectedMessage = Doctor.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDoctor_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_NAME, VALID_MEDICAL_TEST_7, VALID_SLOT_7, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Doctor.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }
}
