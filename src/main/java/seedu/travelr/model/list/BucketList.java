package seedu.travelr.model.list;

import java.util.HashSet;
import java.util.Set;

import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Title;

/**
 * Represents the BucketList class.
 */
public class BucketList implements EventList {

    private final Set<Event> events = new HashSet<>();

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public void addEvents(Set<Event> events) {
        this.events.addAll(events);
    }

    @Override
    public void removeEvent(Event event) {
        events.remove(event);
    }

    @Override
    public void removeEvent(int i) {
        events.remove(events.toArray()[i - 1]);
    }

    @Override
    public Event getEvent(int i) {
        Object temp = events.toArray()[i - 1];
        Event event = (Event) temp;
        return event;
    }

    @Override
    public Event getEvent(Event event) {
        return event;
    }

    @Override
    public boolean contains(Event event) {
        return events.contains(event);
    };

    @Override
    public boolean contains(String title) {
        Event temp = new Event(new Title(title));
        return events.contains(temp);
    }

    @Override
    public Set<Event> getList() {
        return events;
    }


}
