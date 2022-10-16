package seedu.uninurse.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class UninurseBook implements ReadOnlyUninurseBook {

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

    public UninurseBook() {}

    /**
     * Creates an UninurseBook using the Persons in the {@code toBeCopied}
     */
    public UninurseBook(ReadOnlyUninurseBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Patient> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code UninurseBook} with {@code newData}.
     */
    public void resetData(ReadOnlyUninurseBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the uninurse book.
     */
    public boolean hasPerson(Patient person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the uninurse book.
     * The person must not already exist in the uninurse book.
     */
    public void addPerson(Patient p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the uninurse book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the uninurse book.
     */
    public void setPerson(Patient target, Patient editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code UninurseBook}.
     * {@code key} must exist in the uninurse book.
     */
    public void removePerson(Patient key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Patient> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UninurseBook // instanceof handles nulls
                && persons.equals(((UninurseBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
