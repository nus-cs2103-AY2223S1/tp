package seedu.taassist.model;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class TaAssist implements ReadOnlyTaAssist {

    private final UniqueList<Student> students;
    private final UniqueList<ModuleClass> moduleClasses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueList<Student>();
        moduleClasses = new UniqueList<ModuleClass>();
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
        this.students.setElements(students);
    }

    /**
     * Replaces the contents of the module class list with {@code moduleClasses}.
     * {@code moduleClasses} must not contain duplicate classes.
     */
    public void setModuleClasses(List<ModuleClass> moduleClasses) {
        this.moduleClasses.setElements(moduleClasses);
    }

    /**
     * Resets the existing data of this {@code TaAssist} with {@code newData}.
     */
    public void resetData(ReadOnlyTaAssist newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setModuleClasses(newData.getModuleClassList());
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
    public void addStudent(Student student) {
        requireNonNull(student);
        students.add(student);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in TA-Assist.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address
     * book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        students.setElement(target, editedStudent);
    }

    /**
     * Removes {@code student} from this {@code TaAssist}.
     * {@code student} must exist in TA-Assist.
     */
    public void removeStudent(Student student) {
        requireNonNull(student);
        students.remove(student);
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
    public void addModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        moduleClasses.add(moduleClass);
    }

    /**
     * Replaces the module class {@code target} in the list with {@code editedModuleClass}.
     * {@code target} must exist in the list.
     * The identity of {@code editedModuleClass} must not be the same as another existing module class in the app.
     */
    public void setModuleClass(ModuleClass target, ModuleClass editedModuleClass) {
        requireAllNonNull(target, editedModuleClass);
        moduleClasses.setElement(target, editedModuleClass);
    }

    /**
     * Finds and returns a module class with equivalent identity to {@code target}.
     */
    public ModuleClass findModuleClass(ModuleClass target) {
        requireNonNull(target);
        return moduleClasses.findElement(target);
    }

    /**
     * Removes {@code moduleClass} from this {@code TaAssist}.
     * {@code moduleClass} must exist in TA-Assist.
     */
    public void removeModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        moduleClasses.remove(moduleClass);
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
    public ObservableList<ModuleClass> getModuleClassList() {
        return moduleClasses.asUnmodifiableObservableList();
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
