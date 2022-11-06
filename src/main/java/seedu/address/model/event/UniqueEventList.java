package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * A list of Events that enforces uniqueness between its elements and does not allow nulls.
 * Supports a minimal set of list operations.
 */
public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     * @param toCheck event to be checked.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Adds a new Event to the internal list.
     * The event must not already exist in the list.
     * @param toAdd Event to be added to the internal list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the list.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the list.
     * @param target event to be replaced.
     * @param editedEvent event to replace with.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        if (!target.isSameEvent(editedEvent) && contains(editedEvent)) {
            throw new DuplicateEventException();
        }

        internalList.set(index, editedEvent);
    }
    /**
     * Removes the equivalent event from the internal list.
     * The event must exist in the list.
     * @param toRemove Event to be removed.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    /**
     * Replace the contents of this list with {@code replacementEvents}.
     * @param replacementEvents UniqueEventList to replace the current internal list.
     */
    public void setEvents(UniqueEventList replacementEvents) {
        requireNonNull(replacementEvents);
        internalList.setAll(replacementEvents.internalList);
    }

    /**
     * Replaces the contents of this list with {@code replacementEvents}.
     * {@code replacementEvents} List to replace the current internal list, it must not contain duplicate events.
     */
    public void setEvents(List<Event> replacementEvents) {
        requireAllNonNull(replacementEvents);
        if (!eventsAreUnique(replacementEvents)) {
            throw new DuplicateEventException();
        }
        internalList.setAll(replacementEvents);
    }

    /**
     * Returns true if {@code events} contains only unique events.
     */
    private boolean eventsAreUnique(List<Event> events) {
        Integer eventListSize = events.size();
        for (int i = 0; i < eventListSize - 1; i++) {
            for (int j = i + 1; j < eventListSize; j++) {
                if (events.get(i).isSameEvent(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sorts the list in-place using the specified {@code sortField}.
     *
     * @param sortField field to sort by.
     */
    public void sort(EventSortField sortField) {
        requireNonNull(sortField);
        internalList.sort(sortField.getComparator());
    }

    /**
     * Returns the internal list as an unmodifiable {@code ObservableList}.
     */
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

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
