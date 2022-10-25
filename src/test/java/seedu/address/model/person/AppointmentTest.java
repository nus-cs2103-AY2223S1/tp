package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_BENSON;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_CARL;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_DANIEL;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_ELLE;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_GEORGE;
import static seedu.address.testutil.TypicalAppointments.SECOND_APPOINTMENT_CARL;
import static seedu.address.testutil.TypicalAppointments.SECOND_APPOINTMENT_GEORGE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Key;
import seedu.address.testutil.AppointmentBuilder;

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
    public void isYearCompareToCorrect() {
        APPOINTMENT_BENSON.setPatient(BENSON);
        APPOINTMENT_CARL.setPatient(CARL);

        // 2010-12-31 23:45 < 2019-12-10 16:30
        assertEquals(APPOINTMENT_CARL.compareTo(APPOINTMENT_BENSON), -1);

        // 2019-12-10 16:30 = 2019-12-10 16:30
        // BENSON < CARL
        APPOINTMENT_BENSON.setPatient(BENSON);
        SECOND_APPOINTMENT_CARL.setPatient(CARL);
        assertEquals(APPOINTMENT_BENSON.compareTo(SECOND_APPOINTMENT_CARL), -1);

        // 2019-12-10 16:30 > 2010-12-31 23:45
        assertEquals(APPOINTMENT_BENSON.compareTo(APPOINTMENT_CARL), 1);
    }

    @Test
    public void isSecondCompareToCorrect() {
        APPOINTMENT_BENSON.setPatient(BENSON);
        APPOINTMENT_CARL.setPatient(CARL);

        // 2010-12-31 23:45 < 2019-12-10 16:30
        assertEquals(APPOINTMENT_CARL.compareTo(APPOINTMENT_BENSON), -1);

        // 2019-12-10 16:30 = 2019-12-10 16:30
        // CARL > BENSON
        APPOINTMENT_BENSON.setPatient(CARL);
        SECOND_APPOINTMENT_CARL.setPatient(BENSON);
        assertEquals(SECOND_APPOINTMENT_CARL.compareTo(APPOINTMENT_BENSON), -1);

        // 2019-12-10 16:30 > 2010-12-31 23:45
        assertEquals(APPOINTMENT_BENSON.compareTo(APPOINTMENT_CARL), 1);

        // 2010-12-31 23:45 < 2010-12-31 23:46
        assertEquals(APPOINTMENT_CARL.compareTo(APPOINTMENT_DANIEL), -1);
    }

    @Test
    public void isCompareToCorrect() {
        APPOINTMENT_BENSON.setPatient(BENSON);
        APPOINTMENT_CARL.setPatient(CARL);

        // APPOINTMENT_CARL < APPOINTMENT_BENSON
        assertEquals(APPOINTMENT_CARL.compareTo(APPOINTMENT_BENSON), -1);

        // APPOINTMENT_BENSON == APPOINTMENT_BENSON
        APPOINTMENT_BENSON.setPatient(BENSON);
        assertEquals(APPOINTMENT_BENSON.compareTo(APPOINTMENT_BENSON), 0);

        // APPOINTMENT_BENSON > APPOINTMENT_DAVID
        assertEquals(APPOINTMENT_BENSON.compareTo(APPOINTMENT_DANIEL), 1);

        Appointment appointmentWithLowerPatient =
                new AppointmentBuilder(APPOINTMENT_BENSON).withPatient(BENSON).build();
        Appointment appointmentWithHigherPatient =
                new AppointmentBuilder(SECOND_APPOINTMENT_CARL).withPatient(CARL).build();
        assertEquals(appointmentWithLowerPatient.compareTo(appointmentWithHigherPatient), -1);
    }

    @Test
    public void isGetGroupNumberCorrect() {
        assertEquals(SECOND_APPOINTMENT_GEORGE.getGroupNumber(), 1);
        assertEquals(APPOINTMENT_GEORGE.getGroupNumber(), 4);
        assertEquals(APPOINTMENT_DANIEL.getGroupNumber(), 0);
    }

    @Test
    public void isGroupCompareToCorrect() {
        // Same tag group
        assertEquals(APPOINTMENT_ELLE.groupCompareTo(APPOINTMENT_GEORGE, Key.TAG), -1);

        // Single tag vs no tag
        assertEquals(APPOINTMENT_DANIEL.groupCompareTo(SECOND_APPOINTMENT_GEORGE, Key.TAG), -9);

        // Single tag vs single tag
        SECOND_APPOINTMENT_GEORGE.setPatient(GEORGE);
        assertEquals(SECOND_APPOINTMENT_GEORGE.groupCompareTo(SECOND_APPOINTMENT_GEORGE, Key.TAG), 0);

        // Single tag vs double tag
        assertEquals(SECOND_APPOINTMENT_GEORGE.groupCompareTo(APPOINTMENT_GEORGE, Key.TAG), -31);

    }
}
