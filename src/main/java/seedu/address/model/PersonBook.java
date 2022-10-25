package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.UniqueBuyerList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PersonBook implements ReadOnlyPersonBook {

    private final UniqueBuyerList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueBuyerList();
    }

    public PersonBook() {}

    /**
     * Creates an PersonBook using the Persons in the {@code toBeCopied}
     */
    public PersonBook(ReadOnlyPersonBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the buyer list with {@code buyers}.
     * {@code buyers} must not contain duplicate buyers.
     */
    public void setPersons(List<Buyer> buyers) {
        this.persons.setBuyers(buyers);
    }

    /**
     * Resets the existing data of this {@code PersonBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPersonBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// buyer-level operations

    /**
     * Returns true if a buyer with the same identity as {@code buyer} exists in the address book.
     */
    public boolean hasPerson(Buyer buyer) {
        requireNonNull(buyer);
        return persons.contains(buyer);
    }

    /**
     * Adds a buyer to the address book.
     * The buyer must not already exist in the address book.
     */
    public void addPerson(Buyer p) {
        persons.add(p);
    }

    /**
     * Replaces the given buyer {@code target} in the list with {@code editedBuyer}.
     * {@code target} must exist in the address book.
     * The buyer identity of {@code editedBuyer} must not be the same as another existing buyer in the address book.
     */
    public void setPerson(Buyer target, Buyer editedBuyer) {
        requireNonNull(editedBuyer);

        persons.setPerson(target, editedBuyer);
    }

    /**
     * Removes {@code key} from this {@code PersonBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Buyer key) {
        persons.remove(key);
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Buyer> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonBook // instanceof handles nulls
                && persons.equals(((PersonBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
