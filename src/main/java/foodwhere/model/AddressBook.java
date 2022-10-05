package foodwhere.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import foodwhere.model.stall.Stall;
import foodwhere.model.stall.UniquePersonList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the stall list with {@code stalls}.
     * {@code stalls} must not contain duplicate stalls.
     */
    public void setPersons(List<Stall> stalls) {
        this.persons.setPersons(stalls);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// stall-level operations

    /**
     * Returns true if a stall with the same identity as {@code stall} exists in the address book.
     */
    public boolean hasPerson(Stall stall) {
        requireNonNull(stall);
        return persons.contains(stall);
    }

    /**
     * Adds a stall to the address book.
     * The stall must not already exist in the address book.
     */
    public void addPerson(Stall p) {
        persons.add(p);
    }

    /**
     * Replaces the given stall {@code target} in the list with {@code editedStall}.
     * {@code target} must exist in the address book.
     * The stall identity of {@code editedStall} must not be the same as another existing stall in the address book.
     */
    public void setPerson(Stall target, Stall editedStall) {
        requireNonNull(editedStall);

        persons.setPerson(target, editedStall);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Stall key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Stall> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
