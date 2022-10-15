package longtimenosee.model.event;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import longtimenosee.model.event.exceptions.DuplicateEventException;
import longtimenosee.model.event.exceptions.EventNotFoundException;
import longtimenosee.model.event.exceptions.OverlapEventException;


/**
 * A list of events that enforces uniqueness between its elements and does not allow nulls.
 * A Event is considered unique by comparing using {@code Event#isSameEvent(Event)}. As such, adding and updating of
 * events uses Event#isSameEvent(Event) for equality so as to ensure that the Event being added or updated is
 * unique in terms of identity in the UniqueEventList. However, the removal of a Event uses Event#equals(Object) so
 * as to ensure that the Event with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Event#isSameEvent(Event)
 */
public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();

    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Adds an Event to the list.
     * The event must not already exist in the list.
     */
    public void add(Event toAdd) throws DuplicateEventException, OverlapEventException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        if (overlaps(toAdd)) {
            throw new OverlapEventException();
        }
        internalList.add(toAdd);
    }

    private boolean overlaps(Event toAdd) {
        for (Event e : internalList) {
            if (Event.eventClash(e, toAdd)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the equivalent event from the list.
     * The event must exist in the list.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    public void setEvents(UniqueEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEventList // instanceof handles nulls
                && internalList.equals(((UniqueEventList) other).internalList));
    }

    /**
     * Check if the internal list has said event.
     * Less strict equalty used (i.e: Only the person's name /descriptor)
     * @param toCheck
     * @return
     */
    public boolean hasEvent(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Attempts to delete an event
     * Notice the strict equality for deleting events, as opposed to hasEvent
     * @param e
     */
    public void deleteEvent(Event e) {
        requireNonNull(e);
        if (!internalList.remove(e)) {
            throw new EventNotFoundException();
        }
    }
    //TODO: Events are unique?
}
