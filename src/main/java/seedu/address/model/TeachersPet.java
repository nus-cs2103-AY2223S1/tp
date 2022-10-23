package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Class;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniqueScheduleList;
import seedu.address.model.timeRange.TimeRange;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TeachersPet implements ReadOnlyTeachersPet {

    private final UniquePersonList persons;
    private final UniqueScheduleList schedule;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        schedule = new UniqueScheduleList();
    }

    public TeachersPet() {}

    /**
     * Creates an TeachersPet using the Persons in the {@code toBeCopied}
     */
    public TeachersPet(ReadOnlyTeachersPet toBeCopied) {
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
     * Replaces the contents of the schedule list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setSchedule(List<Person> persons) {
        this.schedule.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code TeachersPet} with {@code newData}.
     */
    public void resetData(ReadOnlyTeachersPet newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setSchedule(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        schedule.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        schedule.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code TeachersPet}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        schedule.remove(key);
    }

    /**
     * Returns the first available class from {@code TeachersPet} within the time range.
     * @return the first available class.
     */
    public Class getAvailableClass(TimeRange timeRange) {
        return persons.getAvailableClass(timeRange);
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

    public ObservableList<Person> getInternalList() {
        return persons.getInternalList();
    }

    @Override
    public ObservableList<Person> getScheduleList() {
        return schedule.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeachersPet // instanceof handles nulls
                && persons.equals(((TeachersPet) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
