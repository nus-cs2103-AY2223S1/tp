package longtimenosee.testutil;

import longtimenosee.model.event.Date;
import longtimenosee.model.event.Description;
import longtimenosee.model.event.Duration;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Name;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_DATE = "2022-12-12";
    public static final String DEFAULT_DESCRIPTION = "Lunch with Alice Pauline";
    public static final String DEFAULT_DURATION = "10:00__12:00";
    public static final String DEFAULT_NAME = "Alice Pauline";

    private Date date;
    private Description description;
    private Duration duration;
    private Name name;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        date = new Date(DEFAULT_DATE);
        duration = new Duration(DEFAULT_DURATION);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getPersonName();
        description = eventToCopy.getDescription();
        date = eventToCopy.getDate();
        duration = eventToCopy.getDuration();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Event} that we are building.
     */
    public EventBuilder withDuration(String duration) {
        this.duration = new Duration(duration);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
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
     * Main builds a copy of the current client stored.
     * @return person
     */

    public Event build() {
        return new Event(description, name, date, duration);
    }
}

