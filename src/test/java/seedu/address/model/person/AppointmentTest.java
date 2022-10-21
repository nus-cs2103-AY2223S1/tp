package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class AppointmentTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Appointment.of(null));
    }

    @Test
    public void constructor_invalidInput_throwsIllegalArgumentException() {
        String invalidInput = "";
        assertThrows(IllegalArgumentException.class, () -> Appointment.of(invalidInput));
    }

    @Test
    public void isValidDate() {
        assertThrows(NullPointerException.class, () -> Appointment.isValidDate(null)); // null date

        // invalid dates
        assertFalse(Appointment.isValidDate("")); // empty string
        assertFalse(Appointment.isValidDate(" ")); // spaces only
        assertFalse(Appointment.isValidDate("02022002 2222")); // incorrect date format
        assertFalse(Appointment.isValidDate("11.11.2001 1100")); // incorrect date format
        assertFalse(Appointment.isValidDate("2009.09.21 0000")); // incorrect date format
        assertFalse(Appointment.isValidDate("10 Jul 2022 1000")); // incorrect date format
        assertFalse(Appointment.isValidDate("12 12 2007 1230")); // incorrect date format
        assertFalse(Appointment.isValidDate("2004-11-11 1300")); // incorrect date format
        assertFalse(Appointment.isValidDate("01-01-2001")); // missing time
        assertFalse(Appointment.isValidDate("01-01-2001 3000")); // invalid time

        // valid dates
        assertTrue(Appointment.isValidDate("01-01-2001 1200"));
        assertTrue(Appointment.isValidDate("12-02-1927 1230"));
        assertTrue(Appointment.isValidDate("31-12-2003 1300"));
    }

    @Test
    public void isValidAppointment() {
        assertThrows(NullPointerException.class, () -> Appointment.isValidAppointment(null)); // null date

        // invalid appointments
        assertFalse(Appointment.isValidAppointment("02022002 2222")); // incorrect date format
        assertFalse(Appointment.isValidAppointment("11.11.2001 1100")); // incorrect date format
        assertFalse(Appointment.isValidAppointment("2009.09.21 0000")); // incorrect date format
        assertFalse(Appointment.isValidAppointment("10 Jul 2022 1000")); // incorrect date format
        assertFalse(Appointment.isValidAppointment("12 12 2007 1230")); // incorrect date format
        assertFalse(Appointment.isValidAppointment("2004-11-11 1300")); // incorrect date format
        assertFalse(Appointment.isValidAppointment("01-01-2001")); // missing time
        assertFalse(Appointment.isValidAppointment("01-01-2001 3000")); // invalid time

        // valid appointments
        assertTrue(Appointment.isValidAppointment(Appointment.NO_APPOINTMENT_SCHEDULED));
        assertTrue(Appointment.isValidAppointment("01-01-2001 1200"));
        assertTrue(Appointment.isValidAppointment("12-02-1927 1230"));
    }

    @Test
    public void storageFormat() {
        Appointment emptyAppointment = Appointment.of(Appointment.NO_APPOINTMENT_SCHEDULED);
        Appointment testAppointment = Appointment.of("01-01-2001 1200");

        assertEquals(Appointment.NO_APPOINTMENT_SCHEDULED, emptyAppointment.storageFormat());
        assertEquals("01-01-2001 1200", testAppointment.storageFormat());
    }
}
