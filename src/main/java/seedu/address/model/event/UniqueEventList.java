package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.profile.EventsAttending;
import seedu.address.model.profile.Profile;

/**
 * A list of events that enforces uniqueness between its elements and does not allow nulls.
 * An event is unique by comparing using {@code Event#isSameEvent(Event)}. As such, adding and updating of
 * events uses Event#isSameEvent(Event) for equality so as to ensure that the event being added or updated is
 * unique in terms of identity in the UniqueEventList. However, removal of an event uses Event#equals(Object) so
 * as to ensure that the event with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Event#isSameEvent(Event)
 */
public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final SortedList<Event> unmodifiableSortedList = internalUnmodifiableList.sorted(Event::compareTo);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Adds an event to the list.
     * The event must not already exist in the list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the list.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the list.
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
     * Removes the equivalent event from the list.
     * The event must exist in the list.
     * Also removes event from all Profiles which contains event.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
        toRemove.removeFromAttendees();
    }

    /**
     * Adds profiles in {@code profilesToAdd} to the given event.
     * The event must exist in the list.
     */
    public void addEventAttendees(Event event, List<Profile> profilesToAdd) {
        requireAllNonNull(event, profilesToAdd);

        int index = internalList.indexOf(event);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        internalList.get(index).addAttendees(profilesToAdd);
    }

    /**
     * Deletes profiles in {@code profilesToDelete} from the given event.
     * The event must exist in the list.
     */
    public void deleteEventAttendees(Event event, List<Profile> profilesToDelete) {
        requireAllNonNull(event, profilesToDelete);

        int index = internalList.indexOf(event);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        internalList.get(index).removeAttendees(profilesToDelete);
    }

    /**
     * Adds event {@code event} to the profiles in list of profiles {@code profilesToAddEventTo}.
     * The event must exist in the list.
     */
    public void addEventToAttendees(Event event, List<Profile> profilesToAddEventTo) {
        requireAllNonNull(event, profilesToAddEventTo);

        int index = internalList.indexOf(event);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        internalList.get(index).addToAllAttendees(profilesToAddEventTo);
    }

    /**
     * Replaces the event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the list.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the list.
     * Ensures the change is updated for all event attendees.
     */
    public void setEventForAttendees(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        if (!target.isSameEvent(editedEvent) && contains(editedEvent)) {
            throw new DuplicateEventException();
        }

        target.removeFromAttendees();
        editedEvent.addToAllAttendees();
        internalList.set(index, editedEvent);
    }

    /**
     * Removes the event {@code target} from each profile in {@code profilesToEdit}.
     * Profiles in {@code profilesToEdit} must exist in the NUScheduler.
     * The event {@code target} must exist in the list.
     */
    public void removeEventFromAttendees(Event target, List<Profile> profilesToEdit) {
        requireAllNonNull(target, profilesToEdit);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EventNotFoundException();
        }

        internalList.get(index).removeFromAttendees(profilesToEdit);
    }

    /**
     * Replaces each event in {@code eventsToRefresh} with another copy in the list.
     * Events in {@code eventsToRefresh} must exist in the list.
     */
    public void refreshEvents(EventsAttending eventsToRefresh) {
        requireNonNull(eventsToRefresh);
        List<Event> eventList = eventsToRefresh.getEventsList();

        for (Event e : eventList) {
            Event eventCopy = new Event(e.getTitle(), e.getStartDateTime(), e.getEndDateTime(),
                    e.getTags(), e.getAttendees());
            setEvent(e, eventCopy);
        }

    }

    public void setEvents(UniqueEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        requireAllNonNull(events);
        if (!eventsAreUnique(events)) {
            throw new DuplicateEventException();
        }

        internalList.setAll(events);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        return unmodifiableSortedList;
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

    /**
     * Returns true if {@code events} contains only unique events.
     */
    private boolean eventsAreUnique(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).isSameEvent(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

