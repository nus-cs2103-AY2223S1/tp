package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    public void add(Event toAdd) {
        requireNonNull(toAdd);
        //TODO: add Exceptions specific to Events
//        if (contains(toAdd)) {
//            throw new Exception("Duplicate Event Exception");
//        }
        internalList.add(toAdd);
    }

    //TODO: add method for editing Event at specified index here

    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove); //TODO: add exception handling for event not found case
    }

    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

}
