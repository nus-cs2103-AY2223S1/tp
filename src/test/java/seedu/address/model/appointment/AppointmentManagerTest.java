package seedu.address.model.appointment;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.NURSE;
import static seedu.address.testutil.TypicalPersons.PATIENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentManagerTest {

    private final AppointmentManager appointmentManager = new AppointmentManager();

    @Test
    public void createNewAppointment_validArguments_successfullyCreatesNewAppointment() {
        // appointmentManager.createNewAppointment(NURSE, PATIENT, LocalDateTime.now());

    @Test
    public void createNewAppointment_invalidArguments_throwsException() {
    }

    @Test
    public void removeAppointment_validArguments_successfullyRemovesAppointment() {
    }

    @Test
    public void removeAppointment_invalidArguments_throwsException() {
    }

    @Test
    public void hasAppointment_validArguments_returnsAppointment() {

    }

    @Test
    public void hasAppointment_invalidArguments_throwsException() {

    }

    @Test
    public void changeNurse_validArguments_successfullyChangesNurse() {

    }

    @Test
    public void changeNurse_invalidArguments_throwsException() {

    }

    @Test
    public void findAppointment_validArguments_successfullyFindsAppointment() {

    }

    @Test
    public void findAppointment_invalidArguments_throwsException() {

    }

    @Test
    public void getAppointmentsByPatient_validArguments_successfullyRetrievesAppointments() {

    }

    @Test
    public void getAppointmentsByPatient_invalidArguments_throwsException() {

    }

    @Test
    public void getAppointmentsByNurse_validArguments_successfullyRetrievesAppointments() {

    }

    @Test
    public void getAppointmentsByNurse_invalidArguments_throwsException() {

    }

}
