package seedu.address.model.calendar;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Time;


public class CalendarEventTest {
    private static final Name AMY = new Name("AMY");
    private static final Name BOB = new Name("BOB");
    private static final Appointment firstAppointment = new Appointment(new DateTime(
            DateTimeParser.parseLocalDateTimeFromString("1-Apr-2023 01:00 PM")));
    private static final CalendarEvent firstEvent = new CalendarEvent(AMY, new AppointmentStub());

    @Test
    public void method_getName_success() {
        Assertions.assertEquals(AMY, firstEvent.getName());
        Assertions.assertNotEquals(BOB, firstEvent.getName());
    }

    @Test
    public void method_getDay_success() {
        Assertions.assertEquals(1, firstEvent.getDay());
        Assertions.assertNotEquals(2, firstEvent.getDay());
    }

    @Test
    public void method_getMonth_success() {
        Assertions.assertEquals(4, firstEvent.getMonth());
        Assertions.assertNotEquals(1, firstEvent.getMonth());
    }

    @Test
    public void method_getTime_success() {
        Assertions.assertEquals("01:00 PM", firstEvent.getTime());
        Assertions.assertNotEquals("01:00 AM", firstEvent.getTime());
        Assertions.assertNotEquals("1-Apr-2023 01:00 PM", firstEvent.getTime());
        Assertions.assertNotEquals("1/Apr/2023 01:00 PM", firstEvent.getTime());
        Assertions.assertNotEquals("13:00 PM", firstEvent.getTime());
    }

    private class InvalidAppointmentStub extends Appointment {
        InvalidAppointmentStub() {
            super(new ValidDateTimeStub());
        }
        @Override
        public String toString() {
            return "20/Jan-2023 9:00 AM";
        }
    }

    private class ValidAppointmentStub extends Appointment {
        ValidAppointmentStub() {
            super(new ValidDateTimeStub());
        }
        @Override
        public String toString() {
            return "21-Jan-2023 09:00 AM";
        }
    }
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
     * of the Appointment and to check equality.
     */
    private static class AppointmentStub extends Appointment {
        AppointmentStub() {
            super(new DateTime(DateTimeParser.parseLocalDateTimeFromString("1-Apr-2023 12:30 PM")));
        }

        @Override
        public String toString() {
            return "1-Apr-2023 12:30 PM";
        }
    }

    @Test
    public void constructor_nameNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(null, new ValidAppointmentStub()));
    }
    @Test
    public void constructor_appointmentNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(AMY, null));
    }

    @Test
    public void constructor_nameAndAppointmentNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalendarEvent(null, null));
    }

    @Test
    public void versionToString_validVersion_correctStringRepresentation() {
        CalendarEvent newEvent = new CalendarEvent(AMY, new ValidAppointmentStub());
        Assertions.assertEquals("09:00 AM, AMY", newEvent.toString());
    }


    @Test
    public void equals_success() {
        CalendarEvent firstCalendarEvent = new CalendarEvent(AMY, new AppointmentStub());
        CalendarEvent secondCalendarEvent = new CalendarEvent(AMY, new AppointmentStub());
        CalendarEvent thirdCalendarEvent = new CalendarEvent(BOB, new AppointmentStub());

        Assertions.assertEquals(firstCalendarEvent, firstCalendarEvent);
        Assertions.assertNotEquals(firstCalendarEvent, null);
        Assertions.assertEquals(firstCalendarEvent, secondCalendarEvent);
        Assertions.assertNotEquals(firstCalendarEvent, thirdCalendarEvent);

    }



}
