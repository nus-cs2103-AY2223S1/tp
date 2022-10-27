package seedu.address.model.profile;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.event.Event;

/**
 * Represents the events a Profile attends in NUScheduler.
 */
public class EventsAttending {
    private final List<Event> eventsAttending;

    /**
     * Constructs an empty {@code EventsToAttend}.
     */
    public EventsAttending() {
        this.eventsAttending = new ArrayList<>();
    }

    /**
     * Constructs an {@code EventsAttending} with the given list.
     *
     * @param events A list of events the profile is attending.
     */
    public EventsAttending(List<Event> events) {
        requireNonNull(events);
        this.eventsAttending = new ArrayList<>();
        this.eventsAttending.addAll(events);
    }

    /**
     * Returns an immutable event set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Event> getEventsList() {
        Collections.sort(eventsAttending);
        return Collections.unmodifiableList(eventsAttending);
    }

    /**
     * Adds the given event if it has not already been added.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        if (!hasEvent(event)) {
            this.eventsAttending.add(event);
        }
    }

    /**
     * Removes the given event if it exists.
     */
    public void removeEvent(Event event) {
        requireNonNull(event);
        if (hasEvent(event)) {
            this.eventsAttending.remove(event);
        }
    }

    /**
     * Returns true if the given event is in the list of events.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return this.eventsAttending.contains(event);
    }

    /**
     * Adds the given profile to each event in the list of events.
     */
    public void addAttendeeToEvents(Profile attendeeToAdd) {
        requireNonNull(attendeeToAdd);
        eventsAttending.forEach(event -> event.addAttendee(attendeeToAdd));
    }

    /**
     * Removes the given attendee from each event in the list of events.
     */
    public void removeAttendeeFromEvents(Profile attendeeToRemove) {
        requireNonNull(attendeeToRemove);
        eventsAttending.forEach(event -> event.removeAttendee(attendeeToRemove));
    }

    public boolean isEmpty() {
        return this.eventsAttending.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof EventsAttending)) {
            return false;
        }

        EventsAttending other = (EventsAttending) o;
        return this.eventsAttending.equals(other.eventsAttending);
    }

    @Override
    public int hashCode() {
        return eventsAttending.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Events Attending: ");
        List<Event> sortedEvents = this.getEventsList();

        for (int i = 0; i < sortedEvents.size() - 1; i++) {
            Event event = sortedEvents.get(i);
            builder.append(event.getTitle() + ", ");
        }
        Event lastEvent = sortedEvents.get(sortedEvents.size() - 1);
        builder.append(lastEvent.getTitle());

        return builder.toString();
    }
}
