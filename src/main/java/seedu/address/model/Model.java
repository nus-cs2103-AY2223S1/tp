package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.project.Project;
import seedu.address.model.staff.Staff;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Staff> PREDICATE_SHOW_ALL_STAFF = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a project with the same identity as {@code project} exists in the address book.
     */
    boolean hasProject(Project project);

    /**
     * Deletes the given project.
     * The project must exist in the address book.
     */
    void deleteProject(Project target);

    /**
     * Sorts the project list.
     */
    void sortProjects();

    /**
     * Adds the given project.
     * {@code project} must not already exist in the address book.
     */
    void addProject(Project project);

    /**
     * Replaces the given project {@code target} with {@code editedProject}.
     * {@code target} must exist in the address book.
     * The project identity of {@code editedProject} must not be the same as
     * another existing project in the address book.
     */
    void setProject(Project target, Project editedProject);

    /** Sets the given project to be the target of the view command */
    void setTargetProject(Project target);

    /** Returns the project that is the target of the view command */
    ArrayList<Project> getTargetProject();

    /**
     * Returns an unmodifiable view of the filtered project list
     */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Updates the filter of the filtered project list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProjectList(Predicate<Project> predicate);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the address book.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as
     * another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);

    /** Sets the given task to be the target of the view command */
    void setTargetTask(Task target);

    /** Returns the task that is the target of the view command */
    ArrayList<Task> getTargetTask();

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    void filterTask();
}
