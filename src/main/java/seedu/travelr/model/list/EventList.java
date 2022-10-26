package seedu.travelr.model.list;

import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;

import java.util.Comparator;

/**
 * Represents the EventList interface.
 */
abstract class EventList implements Iterable<Event> {
    abstract ObservableList<Event> getInternalList();

    public abstract void sort(Comparator<Event> comp);
}
