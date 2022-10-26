package seedu.address.model.profile;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.event.Event;

public class EventsAttending {
    private final List<Event> eventsAttending;

    /**
     * Constructs an empty {@code EventsToAttend}.
     */
    public EventsAttending() {
        this.eventsAttending = new ArrayList<>();
    }

    /**
     * Constructs an {@code EventsToAttend} with the given list.
     *
     * @param events A list of events the profile is attending.
     */
    public EventsAttending(List<Event> events) {
        requireNonNull(events);
        this.eventsAttending = new ArrayList<>();
        this.eventsAttending.addAll(events);
    }

    /**
     * Returns an immutable profile set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Event> getEventsList() {
        Collections.sort(eventsAttending);
        return Collections.unmodifiableList(eventsAttending);
    }

    public void add(Event event) {
        if (!hasEventAttending(event)) {
            this.eventsAttending.add(event);
        }
    }

    public void remove(Event event) {
        this.eventsAttending.remove(event);
    }

    public boolean hasEventAttending(Event event) {
        return this.eventsAttending.contains(event);
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
        final StringBuilder builder = new StringBuilder("Attendees: ");
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
