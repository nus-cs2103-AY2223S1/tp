package seedu.address.testutil;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.DateTimeParser;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.calendar.CalendarMonth;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.DateTime;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;

/**
 * A utility class to help with building CalendarMonth objects.
 */
public class CalendarMonthBuilder {

    private static final String DEFAULT_FIRST_NAME = "Amy";
    private static final String DEFAULT_SECOND_NAME = "Bob";
    private static final String DEFAULT_FIRST_DATE = "1-04-2023 01:00";
    private static final String DEFAULT_SECOND_DATE = "1-5-2023 01:00";
    private static final String DEFAULT_THIRD_DATE = "1-4-2024 01:00";
    private ObservableList<CalendarEvent> eventList;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public CalendarMonthBuilder() {
        Name firstName = new Name(DEFAULT_FIRST_NAME);
        Name secondName = new Name(DEFAULT_SECOND_NAME);
        Appointment firstAppointment = new Appointment(new DateTime(
                DateTimeParser.parseLocalDateTimeFromString(DEFAULT_FIRST_DATE)),
                new Location("NUS TechnoEdge"));
        Appointment secondAppointment = new Appointment(new DateTime(
                DateTimeParser.parseLocalDateTimeFromString(DEFAULT_SECOND_DATE)),
                new Location("NUS TechnoEdge"));
        Appointment thirdAppointment = new Appointment(new DateTime(
                DateTimeParser.parseLocalDateTimeFromString(DEFAULT_THIRD_DATE)),
                new Location("NUS TechnoEdge"));

        CalendarEvent firstEvent = new CalendarEvent(firstName, firstAppointment);
        CalendarEvent secondEvent = new CalendarEvent(firstName, secondAppointment);
        CalendarEvent thirdEvent = new CalendarEvent(firstName, thirdAppointment);

        this.eventList = FXCollections.observableArrayList(firstEvent, secondEvent, thirdEvent);

    }

    /**
     * Initializes the CalendarMonthBuilder with the data of {@code CalendarMonthToCopy}.
     */
    public CalendarMonthBuilder(CalendarMonth calendarMonthToCopy) {
        this.eventList = calendarMonthToCopy.getCalendarEvents();
    }


    /**
     * Returns a CalendarMonth with the respective arguments as fields.
     */
    public CalendarMonth build() {
        return new CalendarMonth(eventList);
    }

}
