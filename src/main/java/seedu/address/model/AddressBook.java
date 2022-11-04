package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    {
        /*
         * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
         * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
         *
         * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
         *   among constructors.
         */
        persons = new UniquePersonList();
    }

    private final UniquePersonList persons;

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the FinBook.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the FinBook.
     * The person must not already exist in the FinBook.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the FinBook.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the FinBook.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the FinBook.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void sort(String sortParam) {
        persons.sort(sortParam);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
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
