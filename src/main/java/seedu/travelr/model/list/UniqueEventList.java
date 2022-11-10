package seedu.travelr.model.list;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
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
 * Represents the UniqueEventList class.
 */
public class UniqueEventList extends EventList {

    private final Set<Event> events = new HashSet<>();
    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an Event with the given title.
     *
     * @param title the title to be searched.
     */
    public boolean contains(String title) {
        Event temp = new Event(new Title(title));
        return events.contains(temp);
    }

    /**
     * Returns true if the UniqueEventList contains an equivalent Event as the given argument.
     *
     * @param toCheck the event desired.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Returns the internalList.
     */
    @Override
    protected ObservableList<Event> getInternalList() {
        return this.internalList;
    }

    /**
     * Adds an Event to the UniqueEventList.
     * The Event must not already exist in the UniqueEventList.
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
     * Sets the internalList
     */
    public void setInternalList(Set<Event> collections) {
        addEvents(collections);
        internalList.setAll(collections);
    }

    /**
     * Removes the equivalent Event from the UniqueEventList.
     * The Event must exist in the UniqueEventList.
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
     * Replaces the contents of this list with {@code events}.
     * {@code events} must not contain duplicate Events.
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

    @Override
    public void sort(Comparator<Event> comp) {
        internalList.sort(comp);
    }

    /**
     * Returns true if {@code trips} contains only unique Events.
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

    private void addEvent(Event event) {
        events.add(event);
    }

    private void addEvents(Set<Event> events) {
        this.events.addAll(events);
    }

    private void removeEvent(Event event) {
        events.remove(event);
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
