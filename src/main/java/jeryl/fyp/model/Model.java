package jeryl.fyp.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.model.student.StudentId;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' FYP manager file path.
     */
    Path getFypManagerFilePath();

    /**
     * Sets the user prefs' FYP manager file path.
     */
    void setFypManagerFilePath(Path fypManagerFilePath);

    /**
     * Replaces FYP manager data with the data in {@code fypManager}.
     */
    void setFypManager(ReadOnlyFypManager fypManager);

    /** Returns the FypManager */
    ReadOnlyFypManager getFypManager();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the FYP manager.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the FYP manager.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the FYP manager.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the FYP manager.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the FYP manager.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Gets the unique student with the specified StudentId
     * {@code studentId} is unique so it should output 1 student only
     */
    Student getStudentByStudentId(StudentId studentId);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);
}
