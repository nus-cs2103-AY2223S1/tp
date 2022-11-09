package seedu.guest.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.guest.model.guest.Guest;
import seedu.guest.model.guest.UniqueGuestList;

/**
 * Wraps all data at the guest-book level
 * Duplicates are not allowed (by .isSameGuest comparison)
 */
public class GuestBook implements ReadOnlyGuestBook {

    private final UniqueGuestList guests;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        guests = new UniqueGuestList();
    }

    public GuestBook() {}

    /**
     * Creates a GuestList using the Guests in the {@code toBeCopied}
     */
    public GuestBook(ReadOnlyGuestBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the guest list with {@code guests}.
     * {@code guests} must not contain duplicate guests.
     */
    public void setGuests(List<Guest> guests) {
        this.guests.setGuests(guests);
    }

    /**
     * Resets the existing data of this {@code GuestList} with {@code newData}.
     */
    public void resetData(ReadOnlyGuestBook newData) {
        requireNonNull(newData);

        setGuests(newData.getGuestList());
    }

    // guest-level operations

    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the guest book.
     */
    public boolean hasGuest(Guest guest) {
        requireNonNull(guest);
        return guests.contains(guest);
    }

    /**
     * Returns true if a guest with the same room as {@code guest} exists in the guest book.
     */
    public boolean hasSameRoom(Guest guest) {
        requireNonNull(guest);
        return guests.hasSameRoom(guest);
    }

    /**
     * Adds a guest to the guest book.
     * The guest must not already exist in the guest book.
     */
    public void addGuest(Guest p) {
        guests.add(p);
    }

    /**
     * Replaces the given guest {@code target} in the list with {@code editedGuest}.
     * {@code target} must exist in the guest book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the guest book.
     */
    public void setGuest(Guest target, Guest editedGuest) {
        requireNonNull(editedGuest);

        guests.setGuest(target, editedGuest);
    }

    /**
     * Removes {@code key} from this {@code GuestList}.
     * {@code key} must exist in the guest book.
     */
    public void removeGuest(Guest key) {
        guests.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return guests.asUnmodifiableObservableList().size() + " guest(s)";
        // TODO: refine later
    }

    @Override
    public ObservableList<Guest> getGuestList() {
        return guests.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GuestBook // instanceof handles nulls
                && guests.equals(((GuestBook) other).guests));
    }

    @Override
    public int hashCode() {
        return guests.hashCode();
    }
}
