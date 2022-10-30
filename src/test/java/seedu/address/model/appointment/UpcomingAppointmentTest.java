package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class UpcomingAppointmentTest {

    @Test
    public void isValidDate_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UpcomingAppointment.isValidDate(null));
    }

    @Test
    public void isValidDate_emptyString_returnsTrue() {
        assertTrue(UpcomingAppointment.isValidDate(""));
    }

    @Test
    public void isValidDate_today_returnsTrue() {
        assertTrue(UpcomingAppointment.isValidDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

    @Test
    public void isValidDate_yesterday_returnsFalse() {
        assertFalse(UpcomingAppointment.isValidDate(LocalDate.now().minusDays(1)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

    @Test
    public void isValidDate_tomorrow_returnsTrue() {
        assertTrue(UpcomingAppointment.isValidDate(LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
    }

}
