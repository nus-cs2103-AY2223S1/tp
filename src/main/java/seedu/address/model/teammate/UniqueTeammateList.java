package seedu.address.model.teammate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.teammate.exceptions.DuplicateTeammateException;
import seedu.address.model.teammate.exceptions.TeammateNotFoundException;

/**
 * A list of teammates that enforces uniqueness between its elements and does not allow nulls.
 * A teammate is considered unique by comparing using {@code Teammate#isSameTeammate(Teammate)}. As such, adding and
 * updating of teammates uses Teammate#isSameTeammate(Teammate) for equality to ensure that the teammate being added or
 * updated is unique in terms of identity in the UniqueTeammateList. However, the removal of a teammate uses
 * Teammate#equals(Object)  * to ensure that the teammate with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Teammate#isSameTeammate(Teammate)
 */
public class UniqueTeammateList implements Iterable<Teammate> {

    private final ObservableList<Teammate> internalList = FXCollections.observableArrayList();
    private final ObservableList<Teammate> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent teammate as the given argument.
     */
    public boolean contains(Teammate toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTeammate);
    }

    /**
     * Adds a teammate to the list.
     * The teammate must not already exist in the list.
     */
    public void add(Teammate toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTeammateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the teammate {@code target} in the list with {@code editedTeammate}.
     * {@code target} must exist in the list.
     * The teammate identity of {@code editedTeammate} must not be the same as another existing teammate in the list.
     */
    public void setTeammate(Teammate target, Teammate editedTeammate) {
        requireAllNonNull(target, editedTeammate);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TeammateNotFoundException();
        }

        if (!target.isSameTeammate(editedTeammate) && contains(editedTeammate)) {
            throw new DuplicateTeammateException();
        }

        internalList.set(index, editedTeammate);
    }

    /**
     * Removes the equivalent teammate from the list.
     * The teammate must exist in the list.
     */
    public void remove(Teammate toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TeammateNotFoundException();
        }
    }

    public void setTeammates(UniqueTeammateList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code teammates}.
     * {@code teammates} must not contain duplicate teammates.
     */
    public void setTeammates(List<Teammate> teammates) {
        requireAllNonNull(teammates);
        if (!teammatesAreUnique(teammates)) {
            throw new DuplicateTeammateException();
        }

        internalList.setAll(teammates);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Teammate> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Teammate> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTeammateList // instanceof handles nulls
                        && internalList.equals(((UniqueTeammateList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code teammates} contains only unique teammates.
     */
    private boolean teammatesAreUnique(List<Teammate> teammates) {
        for (int i = 0; i < teammates.size() - 1; i++) {
            for (int j = i + 1; j < teammates.size(); j++) {
                if (teammates.get(i).isSameTeammate(teammates.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
