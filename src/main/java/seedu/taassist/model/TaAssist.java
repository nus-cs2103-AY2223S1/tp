package seedu.taassist.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.moduleclass.UniqueModuleClassList;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class TaAssist implements ReadOnlyTaAssist {

    private final UniqueStudentList students;
    private final UniqueModuleClassList moduleClasses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        moduleClasses = new UniqueModuleClassList();
    }

    public TaAssist() {}

    /**
     * Creates an TaAssist using the Students in the {@code toBeCopied}
     */
    public TaAssist(ReadOnlyTaAssist toBeCopied) {
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
     * Resets the existing data of this {@code TaAssist} with {@code newData}.
     */
    public void resetData(ReadOnlyTaAssist newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in TA-Assist.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to TA-Assist.
     * The student must not already exist in TA-Assist.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in TA-Assist.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address
     * book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code TaAssist}.
     * {@code key} must exist in TA-Assist.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// class-level operations

    /**
     * Returns true if a class with the same identity as {@code moduleClass} exists in TA-Assist.
     */
    public boolean hasModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        return moduleClasses.contains(moduleClass);
    }

    /**
     * Adds a class to TA-Assist.
     * The class must not already exist in TA-Assist.
     */
    public void addModuleClass(ModuleClass c) {
        moduleClasses.add(c);
    }

    /**
     * Removes {@code key} from this {@code TaAssist}.
     * {@code key} must exist in TA-Assist.
     */
    public void removeModuleClass(ModuleClass key) {
        moduleClasses.remove(key);
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
                || (other instanceof TaAssist // instanceof handles nulls
                && students.equals(((TaAssist) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
