package jarvis.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import jarvis.commons.core.GuiSettings;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    //=========== UserPrefs ==================================================================================

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
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Sets the user prefs' student book file path.
     */
    void setStudentBookFilePath(Path studentBookFilePath);

    /**
     * Returns the user prefs' task book file path.
     */
    Path getTaskBookFilePath();

    /**
     * Sets the user prefs' task book file path.
     */
    void setTaskBookFilePath(Path taskBookFilePath);

    //=========== StudentBook ================================================================================

    /**
     * Replaces student book data with the data in {@code studentBook}.
     */
    void setStudentBook(ReadOnlyStudentBook studentBook);

    /** Returns the StudentBook */
    ReadOnlyStudentBook getStudentBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the student book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the student book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the student book.
     * The identity of {@code editedStudent} must not be the same as another existing student in the student book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    //=========== TaskBook ================================================================================

    /**
     * Replaces task book data with the data in {@code taskBook}.
     */
    void setTaskBook(ReadOnlyTaskBook taskBook);

    /** Returns the TaskBook */
    ReadOnlyTaskBook getTaskBook();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task book.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task book.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task book.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task book.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);
}
