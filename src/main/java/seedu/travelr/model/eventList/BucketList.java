package seedu.travelr.model.eventList;

import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Title;

import java.util.HashSet;
import java.util.Set;

public class BucketList implements EventList{

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
        events.remove(events.toArray()[i-1]);
    }

    @Override
    public Event getEvent(int i) {
        Object temp = events.toArray()[i-1];
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
