package seedu.travelr.model.list;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.event.exceptions.DuplicateEventException;
import seedu.travelr.model.event.exceptions.EventNotFoundException;


/**
 * Represents the BucketList class.
 */
public class UniqueEventList extends EventList {

    private final Set<Event> events = new HashSet<>();
    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an Event with the given title.
     *
     * @param title the title to be searched
     */
    public boolean contains(String title) {
        Event temp = new Event(new Title(title));
        return events.contains(temp);
    }

    /**
     * Returns true if the list contains an equivalent Event as the given argument.
     *
     * @param toCheck the event desired
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Returns the internalList
     * @return
     */
    @Override
    protected ObservableList<Event> getInternalList() {
        return this.internalList;
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        internalList.add(toAdd);
        addEvent(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
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
        removeEvent(target);
        addEvent(editedEvent);

    }

    /**
     * Sets the internalList
     */
    public void setInternalList(Set<Event> collections) {
        addEvents(collections);
        internalList.setAll(collections);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
        removeEvent(toRemove);
    }

    public void setEvents(EventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.getInternalList());
    }

    /**
     * Replaces the contents of this list with {@code trips}.
     * {@code trips} must not contain duplicate trips.
     */
    public void setEvents(List<Event> events) {
        requireAllNonNull(events);
        if (!eventsAreUnique(events)) {
            throw new DuplicateEventException();
        }

        internalList.setAll(events);
        this.events.addAll(events);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
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
                || (other instanceof EventList // instanceof handles nulls
                && internalList.equals(((EventList) other).getInternalList()));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code trips} contains only unique trips.
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

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addEvents(Set<Event> events) {
        this.events.addAll(events);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public void removeEvent(int i) {
        events.remove(events.toArray()[i - 1]);
    }

    public Event getEvent(int i) {
        Object temp = events.toArray()[i - 1];
        Event event = (Event) temp;
        return event;
    }

    public Event getEvent(Event event) {
        Object[] temp = events.toArray();
        for (Object o : temp) {
            if (o.equals(event)) {
                return (Event) o;
            }
        }
        return null;
    }

}
