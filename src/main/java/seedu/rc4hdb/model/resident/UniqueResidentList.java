package seedu.rc4hdb.model.resident;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.rc4hdb.model.resident.exceptions.DuplicateResidentException;
import seedu.rc4hdb.model.resident.exceptions.ResidentNotFoundException;

/**
 * A list of residents that enforces uniqueness between its elements and does not allow nulls.
 * A resident is considered unique by comparing using {@code Resident#isSameResident(Resident)}. As such, adding and
 * updating of residents uses Resident#isSameResident(Resident) for equality so as to ensure that the resident being
 * added or updated is unique in terms of identity in the UniqueResidentList. However, the removal of a resident uses
 * Resident#equals(Object) so as to ensure that the resident with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Resident#isSameResident(Resident)
 */
public class UniqueResidentList implements Iterable<Resident> {

    private final ObservableList<Resident> internalList = FXCollections.observableArrayList();
    private final ObservableList<Resident> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent resident as the given argument.
     */
    public boolean contains(Resident toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameResident);
    }

    /**
     * Adds a resident to the list.
     * The resident must not already exist in the list.
     */
    public void add(Resident toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateResidentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the resident {@code target} in the list with {@code editedResident}.
     * {@code target} must exist in the list.
     * The resident identity of {@code editedResident} must not be the same as another existing resident in the list.
     */
    public void setResident(Resident target, Resident editedResident) {
        requireAllNonNull(target, editedResident);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ResidentNotFoundException();
        }

        if (!target.isSameResident(editedResident) && contains(editedResident)) {
            throw new DuplicateResidentException();
        }

        internalList.set(index, editedResident);
    }

    /**
     * Removes the equivalent resident from the list.
     * The resident must exist in the list.
     */
    public void remove(Resident toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ResidentNotFoundException();
        }
    }

    public void setResidents(UniqueResidentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code residents}.
     * {@code residents} must not contain duplicate residents.
     */
    public void setResidents(List<Resident> residents) {
        requireAllNonNull(residents);
        if (!residentsAreUnique(residents)) {
            throw new DuplicateResidentException();
        }

        internalList.setAll(residents);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Resident> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Resident> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueResidentList // instanceof handles nulls
                && internalList.equals(((UniqueResidentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code residents} contains only unique residents.
     */
    private boolean residentsAreUnique(List<Resident> residents) {
        for (int i = 0; i < residents.size() - 1; i++) {
            for (int j = i + 1; j < residents.size(); j++) {
                if (residents.get(i).isSameResident(residents.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
