package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.unmodifiableObservableList;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of uids of persons.
 * */
public class UidList implements Iterable<seedu.address.model.person.Uid> {
    private final ObservableList<seedu.address.model.person.Uid> internalList = observableArrayList();
    private final ObservableList<seedu.address.model.person.Uid> internalUnmodifiableList =
            unmodifiableObservableList(internalList);
    /**
     * Returns true if the uid to be checked is already present in the uid list.
     * @param toCheck uid to be checked if a duplicate is present in the list.
     */
    public boolean contains(seedu.address.model.person.Uid toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }
    /**
     * Adds a new uid to the list of uids it stores in its list.
     *
     * @param toAdd uid to be added to the list.
     */
    public void add(seedu.address.model.person.Uid toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
            // this operation will result in Duplicates Persons.
        }
        internalList.add(toAdd);
    }
    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(seedu.address.model.person.Uid toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Returns the internal list as an observableList object
     */
    public ObservableList<seedu.address.model.person.Uid> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<seedu.address.model.person.Uid> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UidList // instanceof handles nulls
                && internalList.equals(((UidList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean uidsAreUnique(List<seedu.address.model.person.Uid> uids) {
        for (int i = 0; i < uids.size() - 1; i++) {
            for (int j = i + 1; j < uids.size(); j++) {
                if (uids.get(i).isSameUid(uids.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
