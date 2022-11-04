package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.NURSE;
import static seedu.address.testutil.TypicalPersons.PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.enums.AppointmentSlotNumber;
import seedu.address.model.person.Date;

public class AppointmentTest {

    private AppointmentDateTime testAppointmentDateTime = new AppointmentDateTime(Date.today(),
            AppointmentSlotNumber.ONE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            new Appointment(null, null, null));

        assertThrows(NullPointerException.class, () ->
            new Appointment(PATIENT, null, null));

        assertThrows(NullPointerException.class, () ->
            new Appointment(null, NURSE, null));

        assertThrows(NullPointerException.class, () ->
            new Appointment(null, null, testAppointmentDateTime));

        assertThrows(NullPointerException.class, () ->
            new Appointment(PATIENT, NURSE, null));

        assertThrows(NullPointerException.class, () ->
            new Appointment(null, NURSE, testAppointmentDateTime));

        assertThrows(NullPointerException.class, () ->
            new Appointment(PATIENT, null, testAppointmentDateTime));

    }

    @Test
    public void constructor_valid_returnsNewAppointment() {
        Appointment newAppt = new Appointment(PATIENT, NURSE, testAppointmentDateTime);
        assertEquals(newAppt, new Appointment(PATIENT, NURSE, testAppointmentDateTime));
    }
}
