package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.teammate.Teammate;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Teammate> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that always evaluate to false */
    Predicate<Task> PREDICATE_HIDE_ALL_TASKS = unused -> false;

    /** {@code Predicate} that filters out incomplete tasks */
    Predicate<Task> PREDICATE_COMPLETED_TASKS = Task::getCompleted;

    /** {@code Predicate} that filters out completed tasks */
    Predicate<Task> PREDICATE_INCOMPLETE_TASKS = task -> !task.getCompleted();

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

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Teammate teammate);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Teammate target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Teammate teammate);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Teammate target, Teammate editedTeammate);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Teammate> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Teammate> predicate);

    /** Replaces the given new task list {@code newTasks} with {@code editedTasks}. */
    void setFilteredTaskList(ObservableList<Task> newTasks);

    /** Returns the TaskPanel */
    ReadOnlyTaskPanel getTaskPanel();

    /**
     * Replaces task panel data with the data in {@code taskPanel}.
     */
    void setTaskPanel(ReadOnlyTaskPanel taskPanel);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task panel.
     */
    boolean hasTask(Task task);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task panel.
     */
    void addTask(Task task);

    /**
     * Deletes the given task. The task must exist in the task list.
     * @param deletedTask The task to delete
     */
    void deleteTask(Task deletedTask);


    /** Returns an unmodifiable version of task list */
    ObservableList<Task> getFilteredTaskList();


    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the address book.
     * The task of {@code editedTask} must not be the same as another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);
}
