package seedu.travelr.model.list;

import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;

/**
 * Represents the EventList interface.
 */
abstract class EventList implements Iterable<Event> {
    abstract ObservableList<Event> getInternalList();
}
