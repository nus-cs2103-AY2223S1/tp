package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the Survin level Duplicates are not allowed (by
 * .isSamePerson comparison)
 */
public class Survin implements ReadOnlySurvin {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public Survin() {
    }

    /**
     * Creates an Survin using the Persons in the {@code toBeCopied}
     */
    public Survin(ReadOnlySurvin toBeCopied) {
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
     * Resets the existing data of this {@code Survin} with {@code newData}.
     */
    public void resetData(ReadOnlySurvin newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * Survin.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public Optional<Person> getPerson(Person person) {
        requireNonNull(person);
        return persons.getPerson(person);
    }

    /**
     * Adds a person to Survin. The person must not already exist in Survin
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}. {@code target} must exist in Survin. The
     * person identity of {@code editedPerson} must not be the same as another
     * existing person in Survin.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Survin}. {@code key} must exist in
     * Survin.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Survin // instanceof handles nulls
                        && persons.equals(((Survin) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
