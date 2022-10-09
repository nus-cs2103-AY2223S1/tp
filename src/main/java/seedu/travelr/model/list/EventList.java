package seedu.travelr.model.list;

import java.util.Set;

import seedu.travelr.model.event.Event;

/**
 * Represents the EventList interface.
 */
public interface EventList {

    abstract void addEvent(Event event);

    abstract void addEvents(Set<Event> events);

    abstract void removeEvent(Event event);

    abstract void removeEvent(int i);

    abstract boolean contains(Event event);

    abstract boolean contains(String title);

    abstract Event getEvent(int i);

    abstract Event getEvent(Event event);

    abstract Set<Event> getList();
}
