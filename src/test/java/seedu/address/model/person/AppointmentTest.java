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
            return "20/01-2023 9:00";
        }
    }

    private class ValidDateTimeStub extends DateTime {
        ValidDateTimeStub() {
            super(LocalDateTime.of(2023, 1, 21, 9, 00));
        }
        @Override
        public String toString() {
            return "21-01-2023 09:00";
        }
    }

    private class ValidTimeStub extends Time {
        ValidTimeStub() {
            super(LocalTime.of(9, 00));
        }
        @Override
        public String toString() {
            return "09:00";
        }
    }

    private class ValidDateStub extends Date {
        ValidDateStub() {
            super(LocalDate.of(2023, 1, 21));
        }
        @Override
        public String toString() {
            return "21-01-2023";
        }
    }

    /**
     * A stub class to check the String representation
     * of the DateTime and to check equality.
     */
    private static class DateTimeStub extends DateTime {
        DateTimeStub() {
            super(DateTimeParser.parseLocalDateTimeFromString("1-04-2023 12:30"));
        }

        @Override
        public String toString() {
            return "1-04-2023 12:30";
        }
    }

    /**
     * A stub class to check the String representation
     * of the Location and to check equality.
     */
    private static class ValidLocationStub extends Location {
        ValidLocationStub() {
            super("NUS TechnoEdge");
        }

        @Override
        public String toString() {
            return "NUS TechnoEdge";
        }
    }
    private static class InvalidLocationStub extends Location {
        InvalidLocationStub() {
            super("");
        }

        @Override
        public String toString() {
            return "";
        }
    }
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null));
    }

    @Test
    public void versionToString_validVersion_correctStringRepresentation() {
        Appointment newAppointment = new Appointment(new DateTimeStub(), new ValidLocationStub());
        Assertions.assertEquals("1-04-2023 12:30, NUS TechnoEdge", newAppointment.toString());
    }

    @Test
    public void isValidAppointment_validDateTimeAndLocation_returnsTrue() {
        Assertions.assertTrue(Appointment.isValidAppointment(new ValidDateTimeStub(), new ValidLocationStub()));
    }

    @Test
    public void isValidAppointment_invalidDateTime_returnsFalse() {
        Assertions.assertFalse(Appointment.isValidAppointment(new InvalidDateTimeStub(), new ValidLocationStub()));
    }

    @Test
    public void isValidAppointment_invalidLocation_returnsFalse() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(
                new InvalidDateTimeStub(), new InvalidLocationStub()));
    }
    @Test
    public void isValidAppointment_invalidLocationAndDateTime_returnsFalse() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment(
                new InvalidDateTimeStub(), new InvalidLocationStub()));
    }
    @Test
    public void getDateTime_sameDateTime_success() {
        ValidDateTimeStub dateTimeStub = new ValidDateTimeStub();
        Appointment appointment = new Appointment(dateTimeStub, new ValidLocationStub());
        appointment.getDateTime().equals(dateTimeStub);
    }

    @Test
    public void getDate_sameDate_success() {
        ValidDateStub dateStub = new ValidDateStub();
        Appointment appointment = new Appointment(new ValidDateTimeStub(), new ValidLocationStub());
        appointment.getDate().equals(dateStub);
    }

    @Test
    public void getYear_sameYear_success() {
        Appointment appointment = new Appointment(new ValidDateTimeStub(), new ValidLocationStub());
        Assertions.assertEquals(2023, appointment.getYear());
        Assertions.assertNotEquals(2022, appointment.getYear());
    }

    @Test
    public void getTime_sameTime_success() {
        ValidTimeStub timeStub = new ValidTimeStub();
        Appointment appointment = new Appointment(new ValidDateTimeStub(), new ValidLocationStub());
        appointment.getTime().equals(timeStub);
    }

    @Test
    public void getLocation_sameLocation_success() {
        ValidLocationStub locationStub = new ValidLocationStub();
        Appointment appointment = new Appointment(new ValidDateTimeStub(), new ValidLocationStub());
        appointment.getLocation().equals(locationStub);
    }
    @Test
    public void equals_success() {
        Appointment firstAppointment = new Appointment(new DateTimeStub(), new ValidLocationStub());
        Appointment secondAppointment = new Appointment(new DateTimeStub(), new ValidLocationStub());

        Assertions.assertEquals(firstAppointment, firstAppointment);
        Assertions.assertNotEquals(firstAppointment, null);
        Assertions.assertEquals(firstAppointment, secondAppointment);
    }
}
