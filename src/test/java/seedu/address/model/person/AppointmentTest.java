package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class AppointmentTest {
    @Test
    public void constructor_nullReason_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, "2022-09-09 16:00", "", false));
    }

    @Test
    public void constructor_nullDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment("nose pain", (String) null, "", false));
    }

    @Test
    public void isValidAppointment() {
        // invalid appointments
        assertFalse(Appointment.isValidReason("")); // empty string for reason
        assertFalse(Appointment.isValidDateTime("")); // empty string for date and time
        assertFalse(Appointment.isValidTimePeriod("s")); // invalid string for period

        // valid appointments
        assertTrue(Appointment.isValidReason("ear pain"));
        assertTrue(Appointment.isValidDateTime("2022-09-08 00:00")); // follows date/time format
        assertTrue(Appointment.isValidTimePeriod("")); // empty period allowed;
        assertTrue(Appointment.isValidTimePeriod("2D")); // a single value is sufficient
    }
}
