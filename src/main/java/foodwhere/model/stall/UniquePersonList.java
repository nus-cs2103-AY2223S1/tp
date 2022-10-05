package foodwhere.model.stall;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import foodwhere.model.stall.exceptions.DuplicatePersonException;
import foodwhere.model.stall.exceptions.PersonNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A stall is considered unique by comparing using {@code Stall#isSamePerson(Stall)}. As such, adding and updating of
 * persons uses Stall#isSamePerson(Stall) for equality so as to ensure that the stall being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a stall uses Stall#equals(Object) so
 * as to ensure that the stall with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Stall#isSamePerson(Stall)
 */
public class UniquePersonList implements Iterable<Stall> {

    private final ObservableList<Stall> internalList = FXCollections.observableArrayList();
    private final ObservableList<Stall> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent stall as the given argument.
     */
    public boolean contains(Stall toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a stall to the list.
     * The stall must not already exist in the list.
     */
    public void add(Stall toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the stall {@code target} in the list with {@code editedStall}.
     * {@code target} must exist in the list.
     * The stall identity of {@code editedStall} must not be the same as another existing stall in the list.
     */
    public void setPerson(Stall target, Stall editedStall) {
        requireAllNonNull(target, editedStall);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedStall) && contains(editedStall)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedStall);
    }

    /**
     * Removes the equivalent stall from the list.
     * The stall must exist in the list.
     */
    public void remove(Stall toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code stalls}.
     * {@code stalls} must not contain duplicate stalls.
     */
    public void setPersons(List<Stall> stalls) {
        requireAllNonNull(stalls);
        if (!personsAreUnique(stalls)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(stalls);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Stall> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Stall> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code stalls} contains only unique stalls.
     */
    private boolean personsAreUnique(List<Stall> stalls) {
        for (int i = 0; i < stalls.size() - 1; i++) {
            for (int j = i + 1; j < stalls.size(); j++) {
                if (stalls.get(i).isSamePerson(stalls.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
