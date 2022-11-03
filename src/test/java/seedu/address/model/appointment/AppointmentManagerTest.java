package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.NURSE;
import static seedu.address.testutil.TypicalPersons.PATIENT;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.enums.AppointmentSlotNumber;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.NurseIsBusyException;
import seedu.address.model.appointment.exceptions.PatientIsBusyException;

public class AppointmentManagerTest {

    private final AppointmentManager appointmentManager = new AppointmentManager();

    private final AppointmentDateTime testDateTime = new AppointmentDateTime(LocalDate.now(),
            AppointmentSlotNumber.ONE);

    private Appointment createMockAppointment() {
        try {
            return appointmentManager.createNewAppointment(PATIENT, NURSE, testDateTime);
        } catch (NurseIsBusyException | PatientIsBusyException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test
    public void createNewAppointment_validArguments_successfullyCreatesNewAppointment() {
        createMockAppointment();
    }

    @Test
    public void createNewAppointment_invalidArguments_throwsException() {
        assertThrows(NullPointerException.class, () ->
            appointmentManager.createNewAppointment(null, null, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.createNewAppointment(PATIENT, null, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.createNewAppointment(null, NURSE, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.createNewAppointment(null, null, testDateTime));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.createNewAppointment(PATIENT, NURSE, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.createNewAppointment(null, NURSE, testDateTime));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.createNewAppointment(PATIENT, null, testDateTime));
    }

    @Test
    public void removeAppointment_validArguments_successfullyRemovesAppointment() {
        createMockAppointment();
        appointmentManager.removeAppointment(PATIENT, NURSE, testDateTime);
    }

    @Test
    public void removeAppointment_invalidNullArguments_throwsException() {
        createMockAppointment();

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(null, null, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(PATIENT, null, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(null, NURSE, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(PATIENT, NURSE, null));
    }

    @Test
    public void hasAppointment_validArguments_returnsAppointment() {
        Appointment createdAppointment = createMockAppointment();
        Optional<Appointment> foundAppointment = appointmentManager.findAppointment(NURSE, PATIENT, testDateTime);
        assertEquals(createdAppointment, foundAppointment.orElseThrow(AppointmentNotFoundException::new));
    }

    // @Test
    // public void hasAppointment_invalidArguments_throwsException() {
    // Appointment createdAppointment = createMockAppointment();
    // }

    // @Test
    // public void changeNurse_validArguments_successfullyChangesNurse() {
    // assertTrue(true);
    // }

    // @Test
    // public void changeNurse_invalidArguments_throwsException() {
    // assertTrue(true);
    // }

    // @Test
    // public void findAppointment_validArguments_successfullyFindsAppointment() {
    // assertTrue(true);
    // }

    // @Test
    // public void findAppointment_invalidArguments_throwsException() {
    // assertTrue(true);
    // }

    // @Test
    // public void
    // getAppointmentsByPatient_validArguments_successfullyRetrievesAppointments() {
    // assertTrue(true);
    // }

    // @Test
    // public void getAppointmentsByPatient_invalidArguments_throwsException() {
    // assertTrue(true);
    // }

    // @Test
    // public void
    // getAppointmentsByNurse_validArguments_successfullyRetrievesAppointments() {
    // assertTrue(true);
    // }

    // @Test
    // public void getAppointmentsByNurse_invalidArguments_throwsException() {
    // assertTrue(true);
    // }

}
