package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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

    Path getArchivedTaskListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyTaskList addressBook);

    /** Returns the TaskList */
    ReadOnlyTaskList getAddressBook();

    ReadOnlyTaskList getArchivedTaskList();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    boolean hasPerson(Task task);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the archived task book.
     */
    boolean hasTaskInArchives(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deletePerson(Task target);


    /**
     * Adds the given task.
     * {@code task} must not already exist in the address book.
     */
    void addPerson(Task task);

    void archivedTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredPersonList();

    ObservableList<Task> getFilteredArchivedTaskList();

    ObservableList<Task> getObservableArchivedTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */

    void updateFilteredPersonList(Predicate<Task> predicate);

    /**
     * Updates filter status of the tasklist view.
     * @param filter status of the tasklist view.
     */
    void updateFilterStatus(String filter);

    /**
     * Updates filter status of the tasklist view.
     * @param filter status of the tasklist view.
     * @param newFilterSet to reset filter set.
     */
    void updateFilterStatus(String filter, boolean newFilterSet);

    /**
     * Returns filter status of the tasklist.
     * @return Returns filter status of the tasklist.
     */
    String getFilterStatus();

    void updateFilteredArchivedTaskList(Predicate<Task> predicate);

    void setArchivedTaskList(ReadOnlyTaskList addressBook);

    void setArchivedTaskListFilePath(Path archivedTaskBookFilePath);

    /**
     * @return archived task list to be printed in CommandResult.
     */
    String getArchivedTasks();

}
