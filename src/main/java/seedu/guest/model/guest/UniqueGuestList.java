package seedu.guest.model.guest;

import static java.util.Objects.requireNonNull;
import static seedu.guest.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.guest.model.guest.exceptions.DuplicateGuestException;
import seedu.guest.model.guest.exceptions.GuestNotFoundException;

/**
 * A list of guests that enforces uniqueness between its elements and does not allow nulls.
 * A guest is considered unique by comparing using {@code Guest#isSameGuest(Guest)}. As such, adding and updating of
 * guests uses Guest#isSameGuest(Guest) for equality so as to ensure that the guest being added or updated is
 * unique in terms of identity in the UniqueGuestList. However, the removal of a guest uses Guest#equals(Object) so
 * as to ensure that the guest with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Guest#isSameGuest(Guest)
 */
public class UniqueGuestList implements Iterable<Guest> {

    private final ObservableList<Guest> internalList = FXCollections.observableArrayList();
    private final ObservableList<Guest> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent guest as the given argument.
     */
    public boolean contains(Guest toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameGuest);
    }

    /**
     * Adds a guest to the list.
     * The guest must not already exist in the list.
     */
    public void add(Guest toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGuestException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the guest {@code target} in the list with {@code editedGuest}.
     * {@code target} must exist in the list.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the list.
     */
    public void setGuest(Guest target, Guest editedGuest) {
        requireAllNonNull(target, editedGuest);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new GuestNotFoundException();
        }

        if (!target.isSameGuest(editedGuest) && contains(editedGuest)) {
            throw new DuplicateGuestException();
        }

        internalList.set(index, editedGuest);
    }

    /**
     * Removes the equivalent guest from the list.
     * The guest must exist in the list.
     */
    public void remove(Guest toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new GuestNotFoundException();
        }
    }

    public void setGuests(UniqueGuestList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code guests}.
     * {@code guests} must not contain duplicate guests.
     */
    public void setGuests(List<Guest> guests) {
        requireAllNonNull(guests);
        if (!guestsAreUnique(guests)) {
            throw new DuplicateGuestException();
        }

        internalList.setAll(guests);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Guest> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Guest> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueGuestList // instanceof handles nulls
                        && internalList.equals(((UniqueGuestList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code guests} contains only unique guests.
     */
    private boolean guestsAreUnique(List<Guest> guests) {
        for (int i = 0; i < guests.size() - 1; i++) {
            for (int j = i + 1; j < guests.size(); j++) {
                if (guests.get(i).isSameGuest(guests.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
