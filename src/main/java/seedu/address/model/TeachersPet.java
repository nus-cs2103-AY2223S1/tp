package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Class;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniquePersonList;
import seedu.address.model.student.UniqueScheduleList;
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
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setPersons(List<Student> students) {
        this.persons.setPersons(students);
    }

    /**
     * Replaces the contents of the schedule list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setSchedule(List<Student> students) {
        this.schedule.setPersons(students);
    }

    /**
     * Resets the existing data of this {@code TeachersPet} with {@code newData}.
     */
    public void resetData(ReadOnlyTeachersPet newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setSchedule(newData.getPersonList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return persons.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addPerson(Student p) {
        persons.add(p);
        schedule.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address book.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        persons.setPerson(target, editedStudent);
        schedule.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code TeachersPet}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Student key) {
        persons.remove(key);
        schedule.remove(key);
    }

    /**
     * Returns the first available class from {@code TeachersPet} within the time range.
     * @return the first available class.
     */
    public Class findAvailableClass(TimeRange timeRange) {
        return persons.findAvailableClass(timeRange);
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

    public ObservableList<Student> getInternalList() {
        return persons.getInternalList();
    }

    @Override
    public ObservableList<Student> getScheduleList() {
        return schedule.asUnmodifiableObservableList();
    }

    @Override
    public void sortPersons(Comparator<Student> comparator) {
        persons.sortPersons(comparator);
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
