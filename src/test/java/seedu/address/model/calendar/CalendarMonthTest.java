package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Location;
import seedu.address.model.person.Name;
import seedu.address.testutil.CalendarMonthBuilder;


public class CalendarMonthTest {
    private static final Name AMY = new Name("Amy");
    private static final Name BOB = new Name("Bob");
    private static final Appointment firstAppointment = new Appointment(new DateTime(
            DateTimeParser.parseLocalDateTimeFromString("1-Apr-2023 01:00 PM")),
            new Location("NUS TechnoEdge"));
    private static final Appointment secondAppointment = new Appointment(new DateTime(
            DateTimeParser.parseLocalDateTimeFromString("1-May-2023 01:00 PM")),
            new Location("NUS TechnoEdge"));
    private static final Appointment thirdAppointment = new Appointment(new DateTime(
            DateTimeParser.parseLocalDateTimeFromString("1-Apr-2024 01:00 PM")),
            new Location("NUS TechnoEdge"));


    @Test
    public void method_getCalendarEventDayNull_throwsNullPointerException() {
        CalendarMonth calendarMonth = new CalendarMonthBuilder().build();
        assertThrows(NullPointerException.class, () -> calendarMonth.getCalendarEventInDayOfMonth(null, 3, 2023));
    }

    @Test
    public void method_getCalendarEventMonthNull_throwsNullPointerException() {
        CalendarMonth calendarMonth = new CalendarMonthBuilder().build();
        assertThrows(NullPointerException.class, () -> calendarMonth.getCalendarEventInDayOfMonth(1, null, 2023));
    }

    @Test
    public void method_getCalendarEventYearNull_throwsNullPointerException() {
        CalendarMonth calendarMonth = new CalendarMonthBuilder().build();
        assertThrows(NullPointerException.class, () -> calendarMonth.getCalendarEventInDayOfMonth(1, 3, null));
    }

    @Test
    public void method_getCalendarEventAllArgumentsNull_throwsNullPointerException() {
        CalendarMonth calendarMonth = new CalendarMonthBuilder().build();
        assertThrows(NullPointerException.class, () -> calendarMonth.getCalendarEventInDayOfMonth(null, null, null));
    }

    @Test
    public void method_getCalendarEvenInDayOfMonth_success() {
        CalendarMonth calendarMonth = new CalendarMonthBuilder().build();
        CalendarEvent firstEvent = new CalendarEvent(AMY, firstAppointment);
        ObservableList<CalendarEvent> calendarEvents = FXCollections.observableArrayList(firstEvent);
        assertEquals(calendarEvents, calendarMonth.getCalendarEventInDayOfMonth(1, 4, 2023));
    }

    @Test
    public void constructor_nameNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalendarMonth(null));
    }


    @Test
    public void equals() {
        CalendarEvent firstEvent = new CalendarEvent(AMY, firstAppointment);
        CalendarEvent secondEvent = new CalendarEvent(AMY, secondAppointment);
        CalendarEvent thirdEvent = new CalendarEvent(AMY, thirdAppointment);
        ObservableList<CalendarEvent> firstEventList = FXCollections.observableArrayList(firstEvent, secondEvent,
                thirdEvent);
        ObservableList<CalendarEvent> secondEventList = FXCollections.observableArrayList(firstEvent, secondEvent);
        CalendarMonth firstCalendarMonth = new CalendarMonth(firstEventList);
        CalendarMonth secondCalendarMonth = new CalendarMonth(secondEventList);
        CalendarMonth calendarMonthCopy = new CalendarMonthBuilder().build();

        // same values -> returns true
        assertTrue(firstCalendarMonth.equals(firstCalendarMonth));

        // same object -> returns true
        assertTrue(firstCalendarMonth.equals(calendarMonthCopy));

        // null -> returns false
        assertFalse(calendarMonthCopy.equals(null));

        // different type -> returns false
        assertFalse(calendarMonthCopy.equals(5));

        // different calendarMonth -> returns false
        assertFalse(calendarMonthCopy.equals(secondCalendarMonth));

    }

}
