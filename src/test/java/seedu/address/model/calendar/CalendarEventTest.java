package seedu.address.model.calendar;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;


public class CalendarEventTest {
    private static final Name AMY = new Name("AMY");
    private static final Name BOB = new Name("BOB");
    private static final Appointment firstAppointment = new Appointment(new DateTime(
            DateTimeParser.parseLocalDateTimeFromString("01-04-2023 01:00")),
            new Location("NUS TechnoEdge"));
    private static final CalendarEvent firstEvent = new CalendarEvent(AMY, firstAppointment);

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
    public void method_getYear_success() {
        Assertions.assertEquals(2023, firstEvent.getYear());
        Assertions.assertNotEquals(2022, firstEvent.getYear());
    }

    @Test
    public void method_getTimeFormat_success() {
        Assertions.assertEquals("01:00 AM", firstEvent.getTimeFormat());
        Assertions.assertNotEquals("01:00", firstEvent.getTimeFormat());
        Assertions.assertNotEquals("1-Apr-2023 01:00", firstEvent.getTimeFormat());
        Assertions.assertNotEquals("1/Apr/2023 01:00", firstEvent.getTimeFormat());
        Assertions.assertNotEquals("13:00 AM", firstEvent.getTimeFormat());
    }


    private class ValidAppointmentStub extends Appointment {
        ValidAppointmentStub() {
            super(new ValidDateTimeStub(), new ValidLocationStub());
        }
        @Override
        public String toString() {
            return "21-Jan-2023 09:00 AM";
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

    private class ValidDateTimeStub extends DateTime {
        ValidDateTimeStub() {
            super(LocalDateTime.of(2023, 1, 21, 9, 00));
        }
        @Override
        public String toString() {
            return "21-Jan-2023 09:00 AM";
        }
    }

    /**
     * A stub class to check the String representation
     * of the Appointment and to check equality.
     */
    private static class AppointmentStub extends Appointment {
        AppointmentStub() {
            super(new DateTime(DateTimeParser.parseLocalDateTimeFromString("01-04-2023 12:30")),
                    new Location("NUS TechnoEdge"));
        }

        @Override
        public String toString() {
            return "01-04-2023 12:30, NUS TechnoEdge";
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
