package seedu.travelr.model.list;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.travelr.model.event.Event;

/**
 * Represents the EventList interface.
 */ abstract class EventList implements Iterable<Event>{

     abstract ObservableList<Event> getInternalList();
}
