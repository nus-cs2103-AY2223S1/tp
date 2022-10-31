package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.student.Class;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueScheduleList;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.timerange.TimeRange;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class TeachersPet implements ReadOnlyTeachersPet {

    private final UniqueStudentList students;
    private final UniqueScheduleList schedule;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        schedule = new UniqueScheduleList();
    }

    public TeachersPet() {}

    /**
     * Creates an TeachersPet using the Students in the {@code toBeCopied}
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
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the schedule list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setSchedule(List<Student> students) {
        this.schedule.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code TeachersPet} with {@code newData}.
     */
    public void resetData(ReadOnlyTeachersPet newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setSchedule(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
        schedule.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in Teacher's Pet.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
        schedule.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code TeachersPet}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
        schedule.remove(key);
    }

    /**
     * Returns the first available class from {@code TeachersPet} within the time range.
     * @return the first available class.
     */
    public Class findAvailableClass(TimeRange timeRange) {
        return students.findAvailableClass(timeRange);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public ObservableList<Student> getInternalList() {
        return students.getInternalList();
    }

    @Override
    public ObservableList<Student> getScheduleList() {
        return schedule.asUnmodifiableObservableList();
    }

    @Override
    public void sortStudents(Comparator<Student> comparator) {
        students.sortStudents(comparator);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeachersPet // instanceof handles nulls
                && students.equals(((TeachersPet) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
