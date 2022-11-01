package seedu.travelr.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.travelr.model.component.DateField;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.model.util.SampleDataUtil;

/**
 * A utility class to help with building Trip objects.
 */
public class TripBuilder {

    public static final String DEFAULT_TITLE = "Singapore Trip";
    public static final String DEFAULT_DESCRIPTION = "See the Merlion.";

    private Title title;
    private Description description;
    private Set<Event> events;
    private Location location;
    private DateField dateField;

    /**
     * Creates a {@code TripBuilder} with the default details.
     */
    public TripBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        events = new HashSet<>();
    }

    /**
     * Initializes the TripBuilder with the data of {@code tripToCopy}.
     */
    public TripBuilder(Trip tripToCopy) {
        title = tripToCopy.getTitle();
        description = tripToCopy.getDescription();
        events = new HashSet<>(tripToCopy.getEvents());
        location = tripToCopy.getLocation();
        dateField = tripToCopy.getDateField();
    }

    /**
     * Sets the {@code Title} of the {@code Trip} that we are building.
     */
    public TripBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Trip} that we are building.
     */
    public TripBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code events} into a {@code Set<Event>} and set it to the {@code Trip} that we are building.
     */
    public TripBuilder withEvents(String... events) {
        this.events = SampleDataUtil.getEventSet(events);
        return this;
    }

    /**
     * Builds the trip from the inputs
     * @return
     */
    public Trip build() {
        return new Trip(title, description, events, location, dateField);
    }

}
