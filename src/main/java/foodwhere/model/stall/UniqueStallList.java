package foodwhere.model.stall;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import foodwhere.model.stall.exceptions.DuplicateStallException;
import foodwhere.model.stall.exceptions.StallNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of stalls that enforces uniqueness between its elements and does not allow nulls.
 * A stall is considered unique by comparing using {@code Stall#isSameStall(Stall)}. As such, adding and updating of
 * stalls uses Stall#isSameStalls(Stall) for equality so as to ensure that the stall being added or updated is
 * unique in terms of identity in the UniqueStallList. However, the removal of a stall uses Stall#equals(Object) so
 * as to ensure that the stall with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Stall#isSameStall(Stall)
 */
public class UniqueStallList implements Iterable<Stall> {

    private final ObservableList<Stall> internalList = FXCollections.observableArrayList();
    private final ObservableList<Stall> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent stall as the given argument.
     */
    public boolean contains(Stall toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStall);
    }

    /**
     * Adds a stall to the list.
     * The stall must not already exist in the list.
     *
     * @param toAdd {@code Stall} to be added.
     */
    public void add(Stall toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStallException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the stall {@code target} in the list with {@code editedStall}.
     * {@code target} must exist in the list.
     * The stall identity of {@code editedStall} must not be the same as another existing stall in the list.
     *
     * @param target {@code Stall} to be edited.
     * @param editedStall {@code Stall} that is edited.
     */
    public void setStall(Stall target, Stall editedStall) {
        requireAllNonNull(target, editedStall);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StallNotFoundException();
        }

        if (!target.isSameStall(editedStall) && contains(editedStall)) {
            throw new DuplicateStallException();
        }

        internalList.set(index, editedStall);
    }

    /**
     * Removes the equivalent stall from the list.
     * The stall must exist in the list.
     *
     * @param toRemove {@code Stall} to be removed.
     */
    public void remove(Stall toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StallNotFoundException();
        }
    }

    /** Sorts the list by {@code comparator}. */
    public void sort(Comparator<Stall> comparator) {
        internalList.sort(comparator);
    }

    /**
     * Sets the list of stalls from the given replacement list.
     *
     * @param replacement {@code UniqueStallList} to be replaced.
     */
    public void setStalls(UniqueStallList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code stalls}.
     * {@code stalls} must not contain duplicate stalls.
     *
     * @param stalls List of stalls.
     */
    public void setStalls(List<Stall> stalls) {
        requireAllNonNull(stalls);
        if (!stallsAreUnique(stalls)) {
            throw new DuplicateStallException();
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
                || (other instanceof UniqueStallList // instanceof handles nulls
                        && internalList.equals(((UniqueStallList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code stalls} contains only unique stalls.
     */
    private boolean stallsAreUnique(List<Stall> stalls) {
        for (int i = 0; i < stalls.size() - 1; i++) {
            for (int j = i + 1; j < stalls.size(); j++) {
                if (stalls.get(i).isSameStall(stalls.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
