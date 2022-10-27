package seedu.watson.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.watson.model.person.Person;
import seedu.watson.model.person.UniquePersonList;

/**
 * Wraps all data at the database level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Database implements ReadOnlyDatabase {

    private final UniquePersonList persons = new UniquePersonList();

    public Database() {
    }

    /**
     * Creates a Database using the Persons in the {@code toBeCopied}
     */
    public Database(ReadOnlyDatabase toBeCopied) {
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
     * Resets the existing data of this {@code Database} with {@code newData}.
     */
    public void resetData(ReadOnlyDatabase newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the database.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the database.
     * The person must not already exist in the database.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the database.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the database.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Database}.
     * {@code key} must exist in the database.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Sorts the list of students by grades.
     * @param isInAscending sorts by ascending if true, descending otherwise
     */
    public void sortByGrade(boolean isInAscending) {
        persons.sortByGrade(isInAscending);
    }

    //// util methods

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
               || (other instanceof Database // instanceof handles nulls
                   && persons.equals(((Database) other).persons));
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }
}
