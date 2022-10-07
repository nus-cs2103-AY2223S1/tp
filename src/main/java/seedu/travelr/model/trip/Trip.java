package seedu.travelr.model.trip;

import static seedu.travelr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.travelr.model.event.Event;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Identity fields
    private final Title title;
    private final Description description;

    // Data fields
    private final Set<Event> events = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Trip(Title title, Description description, Set<Event> events) {
        requireAllNonNull(title, description, events);
        this.title = title;
        this.description = description;
        this.events.addAll(events);
        //System.out.println(events);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Event> getEvents() {
        return Collections.unmodifiableSet(events);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        }

        return otherTrip != null
                && otherTrip.getTitle().equals(getTitle());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
        return otherTrip.getTitle().equals(getTitle())
                && otherTrip.getDescription().equals(getDescription())
                && otherTrip.getEvents().equals(getEvents());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, events);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Description: ")
                .append(getDescription());

        Set<Event> tags = getEvents();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
