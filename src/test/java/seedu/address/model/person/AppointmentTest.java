package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class AppointmentTest {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

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
        assertThrows(NullPointerException.class, () -> Appointment.isValidDateFormat(null)); // null date

        // invalid dates
        assertFalse(Appointment.isValidDateFormat("")); // empty string
        assertFalse(Appointment.isValidDateFormat(" ")); // spaces only
        assertFalse(Appointment.isValidDateFormat("02022002 2222")); // incorrect date format
        assertFalse(Appointment.isValidDateFormat("11.11.2001 1100")); // incorrect date format
        assertFalse(Appointment.isValidDateFormat("2009.09.21 0000")); // incorrect date format
        assertFalse(Appointment.isValidDateFormat("10 Jul 2022 1000")); // incorrect date format
        assertFalse(Appointment.isValidDateFormat("12 12 2007 1230")); // incorrect date format
        assertFalse(Appointment.isValidDateFormat("2004-11-11 1300")); // incorrect date format
        assertFalse(Appointment.isValidDateFormat("01-01-2001")); // missing time
        assertFalse(Appointment.isValidDateFormat("01-01-2001 3000")); // invalid time

        // valid dates
        assertTrue(Appointment.isValidDateFormat("01-01-2001 1200"));
        assertTrue(Appointment.isValidDateFormat("12-02-1927 1230"));
        assertTrue(Appointment.isValidDateFormat("31-12-2003 1300"));
    }

    @Test
    public void isFutureDate() {
        assertThrows(NullPointerException.class, () -> Appointment.isValidDateFormat(null)); // null date

        // invalid dates
        assertFalse(Appointment.isFutureDate(LocalDateTime.now()
                .minusDays(1).format(DATE_FORMAT).toString()));
        assertFalse(Appointment.isFutureDate(LocalDateTime.now()
                .minusMonths(1).format(DATE_FORMAT).toString()));

        // valid dates
        assertTrue(Appointment.isFutureDate(LocalDateTime.now()
                .plusDays(1).format(DATE_FORMAT).toString()));
        assertTrue(Appointment.isFutureDate(LocalDateTime.now()
                .plusMonths(1).format(DATE_FORMAT).toString()));
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
        Appointment testAppointment = Appointment.of("01-01-2024 1200");

        assertEquals(Appointment.NO_APPOINTMENT_SCHEDULED, emptyAppointment.storageFormat());
        assertEquals("01-01-2024 1200", testAppointment.storageFormat());
    }
}
