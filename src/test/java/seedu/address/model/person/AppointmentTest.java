package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_BENSON;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_CARL;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_DAVID;
import static seedu.address.testutil.TypicalAppointments.SECOND_APPOINTMENT_CARL;

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

    @Test
    public void yearCompares() {
        // 2010-12-31 23:45 < 2019-12-10 16:30
        assertEquals(APPOINTMENT_CARL.compareTo(APPOINTMENT_BENSON), -1);

        // 2019-12-10 16:30 = 2019-12-10 16:30
        assertEquals(APPOINTMENT_BENSON.compareTo(SECOND_APPOINTMENT_CARL), 0);

        // 2019-12-10 16:30 > 2010-12-31 23:45
        assertEquals(APPOINTMENT_BENSON.compareTo(APPOINTMENT_CARL), 1);
    }

    @Test
    public void compares() {
        // 2010-12-31 23:45 < 2019-12-10 16:30
        assertEquals(APPOINTMENT_CARL.compareTo(APPOINTMENT_BENSON), -1);

        // 2019-12-10 16:30 = 2019-12-10 16:30
        assertEquals(APPOINTMENT_BENSON.compareTo(SECOND_APPOINTMENT_CARL), 0);

        // 2019-12-10 16:30 > 2010-12-31 23:45
        assertEquals(APPOINTMENT_BENSON.compareTo(APPOINTMENT_CARL), 1);

        // 2010-12-31 23:45 < 2010-12-31 23:46
        assertEquals(APPOINTMENT_CARL.compareTo(APPOINTMENT_DAVID), -1);
    }
}
