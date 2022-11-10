package seedu.travelr.model.list;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.event.exceptions.DuplicateEventException;
import seedu.travelr.model.event.exceptions.EventNotFoundException;


/**
 * Represents the Itineraries class.
 */
public class Itinerary extends EventList {

    private final Set<Event> events = new HashSet<>();
    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Returns whether an Event with the specified title exists in the events.
     *
     * @param title the title to be searched.
     * @return true if the events contains an event with the specified title.
     */
    public boolean contains(String title) {
        Event temp = new Event(new Title(title));
        return events.contains(temp);
    }

    /**
     * Returns the internalList.
     *
     * @return the internalList.
     */
    @Override
    protected ObservableList<Event> getInternalList() {
        return this.internalList;
    }

    /**
     * Adds an Event to this Itinerary.
     * The Event must not already exist in this Itinerary.
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
     * Sets the internalList.
     */
    public void setInternalList(Set<Event> collections) {
        addEvents(collections);
        internalList.setAll(collections);
    }

    /**
     * Removes the equivalent Event from this Itinerary.
     * The Event must exist in this Itinerary.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
        removeEvent(toRemove);
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

    public Event getEvent(Event event) {
        Object[] temp = events.toArray();
        int size = temp.length;
        for (int i = 0; i < size; i++) {
            if (temp[i].equals(event)) {
                return (Event) temp[i];
            }
        }
        return null;
    }

    public Event getEvent(int i) {
        Object temp = events.toArray()[i - 1];
        Event event = (Event) temp;
        return event;
    }

    public Set<Event> getList() {
        return events;
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

    public int getAmountOfEvents() {
        return events.size();
    }

}
