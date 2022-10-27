package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.NURSE;
import static seedu.address.testutil.TypicalPersons.PATIENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null));
        assertThrows(NullPointerException.class, () -> new Appointment(PATIENT, null, null));
        assertThrows(NullPointerException.class, () -> new Appointment(null, NURSE, null));
        assertThrows(NullPointerException.class, () -> new Appointment(PATIENT, NURSE, null));
    }

    @Test
    public void constructor_valid_returnsNewAppointment() {
        LocalDateTime testDateTime = LocalDateTime.now();
        Appointment newAppt = new Appointment(PATIENT, NURSE, testDateTime);
        assertEquals(newAppt, new Appointment(PATIENT, NURSE, testDateTime));
    }
}
