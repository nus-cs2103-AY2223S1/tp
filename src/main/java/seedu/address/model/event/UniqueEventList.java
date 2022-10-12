package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of Events that ensures that all events presents in its internal data structure are unique. Also,
 * no null are allowed.
 */
public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the events to be checked is already present in its internal list.
     * @param toCheck event to be checked if a duplicate is present in the list.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a new Event to the list of events it stores in its internal list.
     * @param toAdd Event to be added to the internal list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        //TODO: add Exceptions specific to Events
        internalList.add(toAdd);
    }

    /**
     * Replace the entire list with the given UniqueEventList
     * @param replacementEvents UniqueEventList to replace the current internal list
     */
    public void setEvents(UniqueEventList replacementEvents) {
        requireNonNull(replacementEvents);
        internalList.setAll(replacementEvents.internalList);
    }

    /**
     * Replace the entire list with the given list
     * @param replacementEvents List to replace the current internal list
     */
    public void setEvents(List<Event> replacementEvents) {
        requireAllNonNull(replacementEvents);
        internalList.setAll(replacementEvents);
    }

    /**
     * Edits the specific event and replaces it with the new Event's information
     * @param target event to be replaced
     * @param editedEvent event to replace with
     */
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.equals(editedEvent)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedEvent);
    }

    //TODO: add method for editing Event at specified index here

    /**
     * Deletes an event from the internal list
     * @param toRemove Event to be deleted
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);
        //TODO: add exception handling for event not found case
    }

    /**
     * Returns the internal list as an observableList object
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

}
