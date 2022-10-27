package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.NURSE;
import static seedu.address.testutil.TypicalPersons.PATIENT;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.NurseIsBusyException;
import seedu.address.model.appointment.exceptions.PatientIsBusyException;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;

public class AppointmentManagerTest {

    private final AppointmentManager appointmentManager = new AppointmentManager();

    private final Optional<Patient> optionalPatient = Optional.of(PATIENT);
    private final Optional<Nurse> optionalNurse = Optional.of(NURSE);
    private final Optional<Patient> emptyPatient = Optional.empty();
    private final Optional<Nurse> emptyNurse = Optional.empty();
    private final LocalDateTime testDateTime = LocalDateTime.now();

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
        appointmentManager.removeAppointment(Optional.of(PATIENT), Optional.of(NURSE), testDateTime);
    }

    @Test
    public void removeAppointment_invalidNullArguments_throwsException() {
        createMockAppointment();

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(null, null, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(optionalPatient, null, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(null, optionalNurse, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(optionalPatient, optionalNurse, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(emptyPatient, null, null));

        assertThrows(NullPointerException.class, () ->
            appointmentManager.removeAppointment(null, emptyNurse, null));
    }

    @Test
    public void removeAppointment_invalidEmptyArguments_throwsException() {
        createMockAppointment();

        assertThrows(IllegalArgumentException.class, () ->
            appointmentManager.removeAppointment(emptyPatient, emptyNurse, testDateTime));
    }

    @Test
    public void hasAppointment_validArguments_returnsAppointment() {
        Appointment createdAppointment = createMockAppointment();
        Optional<Appointment> foundAppointment = appointmentManager.findAppointment(optionalNurse, optionalPatient,
            testDateTime);
        assertEquals(createdAppointment, foundAppointment.orElseThrow(AppointmentNotFoundException::new));
    }

    // @Test
    // public void hasAppointment_invalidArguments_throwsException() {
    //     Appointment createdAppointment = createMockAppointment();
    // }

    // @Test
    // public void changeNurse_validArguments_successfullyChangesNurse() {
    //     assertTrue(true);
    // }

    // @Test
    // public void changeNurse_invalidArguments_throwsException() {
    //     assertTrue(true);
    // }

    // @Test
    // public void findAppointment_validArguments_successfullyFindsAppointment() {
    //     assertTrue(true);
    // }

    // @Test
    // public void findAppointment_invalidArguments_throwsException() {
    //     assertTrue(true);
    // }

    // @Test
    // public void getAppointmentsByPatient_validArguments_successfullyRetrievesAppointments() {
    //     assertTrue(true);
    // }

    // @Test
    // public void getAppointmentsByPatient_invalidArguments_throwsException() {
    //     assertTrue(true);
    // }

    // @Test
    // public void getAppointmentsByNurse_validArguments_successfullyRetrievesAppointments() {
    //     assertTrue(true);
    // }

    // @Test
    // public void getAppointmentsByNurse_invalidArguments_throwsException() {
    //     assertTrue(true);
    // }

}
