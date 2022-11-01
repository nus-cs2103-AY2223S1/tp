package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.appointment.Appointment.DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


class AppointmentTest {

    @Test
    public void isValidDate_nullDate_returnsTrue() {
        assertTrue(Appointment.isValidDate(null));
    }

    @Test
    public void isValidDate_validDate_returnsTrue() {
        assertTrue(Appointment.isValidDate("01-01-2020"));
    }

    @Test
    public void isValidDate_invalidDate_returnsFalse() {
        // valid date with time -> returns false
        assertFalse(Appointment.isValidDate("01-01-2020 12:00"));

        // date that does not exist -> returns false
        assertFalse(Appointment.isValidDate("99-99-9999"));

        // date in the wrong format -> returns false
        assertFalse(Appointment.isValidDate("2022-01-01"));

        // date that is in a month that does not exist -> returns false
        assertFalse(Appointment.isValidDate("14-14-2022"));
    }

    @Test
    public void parseLocalDate_validDate_returnsLocalDate() {
        assertEquals(LocalDate.parse("01-01-2020", DateTimeFormatter.ofPattern(DATE_FORMAT)),
                Appointment.parseLocalDate("01-01-2020"));
    }

    @Test
    public void parseLocalDate_invalidDate_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> Appointment.parseLocalDate("01-01-2020 12:00"));
    }

}
