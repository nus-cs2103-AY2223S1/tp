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
public class MassLinkers implements ReadOnlyMassLinkers {
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

    public MassLinkers() {}

    /**
     * Creates an MassLinkers using the Persons in the {@code toBeCopied}
     */
    public MassLinkers(ReadOnlyMassLinkers toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    /**
     * Resets the existing data of this {@code MassLinkers} with {@code newData}.
     */

    public void resetData(ReadOnlyMassLinkers newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());

    }
    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    //// person-level operations
    /**
     * Returns true if a person with the same identity as {@code person} exists in the mass linkers.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the mass linkers.
     * The person must not already exist in the mass linkers.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the mass linkers.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the mass linkers.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code MassLinkers}.
     * {@code key} must exist in the mass linkers.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods
    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size()
                + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MassLinkers // instanceof handles nulls
                && persons.equals(((MassLinkers) other).persons));
    }

}
