package seedu.classify.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.classify.model.student.NameComparator;
import seedu.classify.model.student.Student;
import seedu.classify.model.student.UniqueStudentList;

/**
 * Wraps all data at the record level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class StudentRecord implements ReadOnlyStudentRecord {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public StudentRecord() {}

    /**
     * Creates an StudentRecord using the Students in the {@code toBeCopied}
     */
    public StudentRecord(ReadOnlyStudentRecord toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setPersons(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code StudentRecord} with {@code newData}.
     */
    public void resetData(ReadOnlyStudentRecord newData) {
        requireNonNull(newData);
        setPersons(newData.getStudentList());
    }

    public void sortList(Comparator<Student> studentComparator) {
        this.students.sortList(studentComparator);
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student record.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the student record.
     * The student must not already exist in the student record.
     */
    public void addStudent(Student p) {
        students.add(p);
        sortList(NameComparator.getNameComparator());
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the student record.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the student record.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code StudentRecord}.
     * {@code key} must exist in the student record.
     */
    public void removePerson(Student key) {
        students.remove(key);
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student record, where the
     * check excludes a particular student.
     * @param studentToExclude the student to be excluded from the check.
     * @param studentToCheck the student to be checked.
     * @return true if there is a matching student identity in the student record
     */
    public boolean excludesAndHasStudent(Student studentToExclude, Student studentToCheck) {
        return students.excludesButContains(studentToExclude, studentToCheck);
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentRecord // instanceof handles nulls
                && students.equals(((StudentRecord) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
