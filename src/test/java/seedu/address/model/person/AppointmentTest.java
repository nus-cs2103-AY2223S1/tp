package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateTimeParser;

public class AppointmentTest {
    private class InvalidDateTimeStub extends DateTime {
        InvalidDateTimeStub() {
            super(LocalDateTime.of(2023, 1, 21, 9, 00));
        }
        @Override
        public String toString() {
            return "20/Jan-2023 9:00 AM";
        }
    }

    private class ValidDateTimeStub extends DateTime {
        ValidDateTimeStub() {
            super(LocalDateTime.of(2023, 1, 21, 9, 00));
        }
        @Override
        public String toString() {
            return "21-Jan-2023 09:00 AM";
        }
    }

    private class ValidTimeStub extends Time {
        ValidTimeStub() {
            super(LocalTime.of(9, 00));
        }
        @Override
        public String toString() {
            return "09:00 AM";
        }
    }

    private class ValidDateStub extends Date {
        ValidDateStub() {
            super(LocalDate.of(2023, 1, 21));
        }
        @Override
        public String toString() {
            return "21-Jan-2023";
        }
    }

    /**
     * A stub class to check the String representation
     * of the DateTime and to check equality.
     */
    private static class DateTimeStub extends DateTime {
        DateTimeStub() {
            super(DateTimeParser.parseLocalDateTimeFromString("1-Apr-2023 12:30 PM"));
        }

        @Override
        public String toString() {
            return "1-Apr-2023 12:30 PM";
        }
    }

    /**
     * A stub class to check the String representation
     * of the DateTime and to check equality.
     */
    private static class LocationStub extends Location {
        LocationStub() {
            super("NUS TechnoEdge");
        }

        @Override
        public String toString() {
            return "NUS TechnoEdge";
        }
    }
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null));
    }

    @Test
    public void versionToString_validVersion_correctStringRepresentation() {
        Appointment newAppointment = new Appointment(new DateTimeStub(), new LocationStub());
        Assertions.assertEquals("1-Apr-2023 12:30 PM, NUS TechnoEdge", newAppointment.toString());
    }

    @Test
    public void isValidAppointment_validDateTime_returnsTrue() {
        Assertions.assertTrue(Appointment.isValidAppointment(new ValidDateTimeStub()));
    }

    @Test
    public void isValidAppointment_invalidDateTime_returnsFalse() {
        Assertions.assertFalse(Appointment.isValidAppointment(new InvalidDateTimeStub()));
    }

    @Test
    public void getDateTime_sameDateTime_success() {
        ValidDateTimeStub dateTimeStub = new ValidDateTimeStub();
        Appointment appointment = new Appointment(dateTimeStub);
        appointment.getDateTime().equals(dateTimeStub);
    }

    @Test
    public void getDate_sameDate_success() {
        ValidDateStub dateStub = new ValidDateStub();
        Appointment appointment = new Appointment(new ValidDateTimeStub());
        appointment.getDate().equals(dateStub);
    }

    @Test
    public void getTime_sameTime_success() {
        ValidTimeStub timeStub = new ValidTimeStub();
        Appointment appointment = new Appointment(new ValidDateTimeStub());
        appointment.getTime().equals(timeStub);
    }
    @Test
    public void equals_success() {
        Appointment firstAppointment = new Appointment(new DateTimeStub(), new LocationStub());
        Appointment secondAppointment = new Appointment(new DateTimeStub(), new LocationStub());

        Assertions.assertEquals(firstAppointment, firstAppointment);
        Assertions.assertNotEquals(firstAppointment, null);
        Assertions.assertEquals(firstAppointment, secondAppointment);
    }
}
