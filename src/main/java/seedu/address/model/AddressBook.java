package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.teammate.Teammate;
import seedu.address.model.teammate.UniqueTeammateList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameTeammate comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueTeammateList teammates;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        teammates = new UniqueTeammateList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Teammates in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the teammate list with {@code teammates}.
     * {@code teammates} must not contain duplicate teammates.
     */
    public void setTeammates(List<Teammate> teammates) {
        this.teammates.setTeammates(teammates);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setTeammates(newData.getTeammateList());
    }

    //// teammate-level operations

    /**
     * Returns true if a teammate with the same identity as {@code teammate} exists in the address book.
     */
    public boolean hasTeammate(Teammate teammate) {
        requireNonNull(teammate);
        return teammates.contains(teammate);
    }

    /**
     * Adds a teammate to the address book.
     * The teammate must not already exist in the address book.
     */
    public void addTeammate(Teammate p) {
        teammates.add(p);
    }

    /**
     * Replaces the given teammate {@code target} in the list with {@code editedTeammate}.
     * {@code target} must exist in the address book.
     * The teammate identity of {@code editedTeammate} must not be the same as another existing teammate in the address
     * book.
     */
    public void setTeammate(Teammate target, Teammate editedTeammate) {
        requireNonNull(editedTeammate);

        teammates.setTeammate(target, editedTeammate);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTeammate(Teammate key) {
        teammates.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return teammates.asUnmodifiableObservableList().size() + " teammates";
    }

    @Override
    public ObservableList<Teammate> getTeammateList() {
        return teammates.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && teammates.equals(((AddressBook) other).teammates));
    }

    @Override
    public int hashCode() {
        return teammates.hashCode();
    }
}
