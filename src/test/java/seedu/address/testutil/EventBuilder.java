package seedu.address.testutil;

import seedu.address.model.date.Date;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventTitle;
import seedu.address.model.event.Purpose;
import seedu.address.model.event.StartTime;

/**
 * A utility class to help with building Event objects.
 */

public class EventBuilder {
    public static final String DEFAULT_EVENT_TITLE = "Car Sale";
    public static final String DEFAULT_DATE = "10/10/2022";
    public static final String DEFAULT_TIME = "10:40";
    public static final String DEFAULT_PURPOSE = "$1000 off all cars";

    private EventTitle eventTitle;
    private Date date;
    private StartTime startTime;
    private Purpose purpose;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventTitle = new EventTitle(DEFAULT_EVENT_TITLE);
        date = new Date(DEFAULT_DATE);
        startTime = new StartTime(DEFAULT_TIME);
        purpose = new Purpose(DEFAULT_PURPOSE);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventTitle = eventToCopy.getEventTitle();
        date = eventToCopy.getStartDate();
        startTime = eventToCopy.getStartTime();
        purpose = eventToCopy.getPurpose();
    }

    /**
     * Sets the {@code EventTitle} of the {@code Event} that we are building.
     */
    public EventBuilder withEventTitle(String eventTitle) {
        this.eventTitle = new EventTitle(eventTitle);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Event} that we are building.
     */
    public EventBuilder withStartTime(String startTime) {
        this.startTime = new StartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code Purpose} of the {@code Event} that we are building.
     */
    public EventBuilder withPurpose(String purpose) {
        this.purpose = new Purpose(purpose);
        return this;
    }

    public Event build() {
        return new Event(eventTitle, date, startTime, purpose);
    }
}
