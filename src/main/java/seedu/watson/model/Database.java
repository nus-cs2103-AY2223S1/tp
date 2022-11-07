package seedu.watson.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.watson.model.student.Student;
import seedu.watson.model.student.UniquePersonList;

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
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setPersons(List<Student> students) {
        this.persons.setPersons(students);
    }

    /**
     * Resets the existing data of this {@code Database} with {@code newData}.
     */
    public void resetData(ReadOnlyDatabase newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the database.
     */
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return persons.contains(student);
    }

    /**
     * Adds a student to the database.
     * The student must not already exist in the database.
     */
    public void addPerson(Student p) {
        persons.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the database.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the database.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        persons.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code Database}.
     * {@code key} must exist in the database.
     */
    public void removePerson(Student key) {
        persons.remove(key);
    }

    /**
     * Sorts the list of students by grades.
     * @param isInAscending sorts by ascending if true, descending otherwise
     */
    public void sortByGrade(boolean isInAscending, String subjectName) {
        persons.sortByGrade(isInAscending, subjectName);
    }

    //// util methods

    @Override
    public ObservableList<Student> getPersonList() {
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
