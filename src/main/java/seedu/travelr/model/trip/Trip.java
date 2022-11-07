package seedu.travelr.model.trip;

import static seedu.travelr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.travelr.model.component.DateField;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.list.Itinerary;

/**
 * Represents a Trip in Travelr.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Identity fields
    private final Title title;
    private final Description description;
    private final Location location;
    private boolean done;
    private final DateField dateField;

    // Data fields
    private final Itinerary events = new Itinerary();

    /**
     * Used in JsonAdaptedTrips.
     * Every field must be present and not null.
     */
    public Trip(Title title, Description description, Set<Event> events,
                boolean markedAsDone, Location location, DateField dateField) {
        requireAllNonNull(title, description, events);
        this.title = title;
        this.description = description;
        this.events.setInternalList(events);
        this.location = location;
        this.done = markedAsDone;
        this.dateField = dateField;
    }

    /**
     * Constructs Trip.
     * Every field must be present and not null.
     */
    public Trip(Title title, Description description, Set<Event> events, Location location, DateField dateField) {
        requireAllNonNull(title, description, events, location, dateField);
        this.title = title;
        this.description = description;
        this.events.setInternalList(events);
        this.location = location;
        this.done = false;
        this.dateField = dateField;
    }

    /**
     * Constructs a Trip.
     * Every field must be present and not null.
     */
    public Trip(Title title, Description description, Location location, DateField dateField) {
        requireAllNonNull(title, description, location, dateField);
        this.title = title;
        this.description = description;
        this.location = location;
        this.done = false;
        this.dateField = dateField;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void markDone() {
        this.done = true;
    }

    public void markNotDone() {
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    public Event getEvent(Event event) {
        return events.getEvent(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public boolean containsEvent(Event event) {
        return events.contains(event);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public DateField getDateField() {
        return dateField;
    }

    /**
     * Returns an immutable Events set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Event> getEvents() {
        return Collections.unmodifiableSet(events.getList());
    }

    /**
     * Returns the Itinerary of this Trip.
     */
    public Itinerary getItinerary() {
        return this.events;
    }

    /**
     * Returns true if both Trips have the same title.
     * This defines a weaker notion of equality between two Trips.
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        }

        return otherTrip != null
                && otherTrip.getTitle().equals(getTitle());
    }

    /**
     * Returns true if this Trip and another Object have the same title.
     * This defines a weaker notion of equality between this Trip and another Object.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Trip)) {
            return false;
        }

        Trip otherTrip = (Trip) other;
        return otherTrip.getTitle().equals(getTitle());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, events);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
                .append(getTitle())
                .append("; Description: ")
                .append(getDescription())
                .append("; Location: ")
                .append(getLocation())
                .append("; Date: ")
                .append(getDateField());

        return builder.toString();
    }

    public int compareTitle(Trip trip) {
        return title.compareTo(trip.title);
    }

    public int compareTime(Trip trip) {
        return dateField.compareTo(trip.dateField);
    }

    public int compareLocation(Trip trip) {
        return location.compareTo(trip.location);
    }

    public int compareNumberOfEvents(Trip trip) {
        return events.getAmountOfEvents() - trip.events.getAmountOfEvents();
    }

    /**
     * Compares this Trip against another Trip based on whether they are completed.
     */
    public int compareCompletion(Trip trip) {
        int thisCompletion = done ? 1 : 0;
        int otherCompletion = trip.done ? 1 : 0;
        return thisCompletion - otherCompletion;
    }
}
