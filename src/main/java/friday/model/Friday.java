package friday.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import friday.model.student.Student;
import friday.model.student.UniqueStudentList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the highest level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Friday implements ReadOnlyFriday {

    private final UniqueStudentList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueStudentList();
    }

    public Friday() {}

    /**
     * Creates FRIDAY using the Persons in the {@code toBeCopied}
     */
    public Friday(ReadOnlyFriday toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Student> students) {
        this.persons.setPersons(students);
    }

    /**
     * Resets the existing data of {@code FRIDAY} with {@code newData}.
     */
    public void resetData(ReadOnlyFriday newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in FRIDAY.
     */
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return persons.contains(student);
    }

    /**
     * Adds a person to FRIDAY.
     * The person must not already exist in FRIDAY.
     */
    public void addPerson(Student p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in FRIDAY.
     * The person identity of {@code editedPerson} must not be the same as another existing person in FRIDAY.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        persons.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code FRIDAY}.
     * {@code key} must exist in FRIDAY.
     */
    public void removePerson(Student key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Friday // instanceof handles nulls
                && persons.equals(((Friday) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
