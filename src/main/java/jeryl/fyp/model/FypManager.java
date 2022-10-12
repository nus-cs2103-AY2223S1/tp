package jeryl.fyp.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;
import jeryl.fyp.model.student.UniqueStudentList;

/**
 * Wraps all data at the FYP-manager level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class FypManager implements ReadOnlyFypManager {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public FypManager() {}

    /**
     * Creates a FypManager using the Students in the {@code toBeCopied}
     */
    public FypManager(ReadOnlyFypManager toBeCopied) {
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
     * Resets the existing data of this {@code FypManager} with {@code newData}.
     */
    public void resetData(ReadOnlyFypManager newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the FYP manager.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the FYP manager.
     * The student must not already exist in the FYP manager.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the FYP manager.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the FYP manager.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code FypManager}.
     * {@code key} must exist in the FYP manager.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Returns unique Student if {@code students} contains the student with the specified studentId.
     */
    public Student getStudentByStudentId(StudentId studentId) {
        requireNonNull(studentId);

        return students.getStudentByStudentId(studentId);
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
                || (other instanceof FypManager // instanceof handles nulls
                && students.equals(((FypManager) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
