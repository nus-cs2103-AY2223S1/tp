package taskbook.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import taskbook.commons.core.GuiSettings;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    /** {@code Predicate} that always evaluate to true */
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
     * Returns the user prefs' task book file path.
     */
    Path getTaskBookFilePath();

    /**
     * Sets the user prefs' task book file path.
     */
    void setTaskBookFilePath(Path taskBookFilePath);

    /**
     * Commits the current TaskBook to the version history.
     */
    void commitTaskBook();

    /**
     * Returns true if possible to revert the TaskBook to the previous state in the version history.
     * @see VersionedTaskBook#canUndo()
     */
    boolean canUndoTaskBook();

    /**
     * Reverts the TaskBook to the previous state in the version history.
     * @see VersionedTaskBook#undo()
     */
    void undoTaskBook();

    /**
     * Returns true if possible to revert the TaskBook to the previously undone state in the version history.
     * @see VersionedTaskBook#canUndo()
     */
    boolean canRedoTaskBook();

    /**
     * Reverts the TaskBook to the previously undone state in the version history.
     * @see VersionedTaskBook#redo()
     */
    void redoTaskBook();

    /**
     * Replaces task book data with the data in {@code taskBook}.
     */
    void setTaskBook(ReadOnlyTaskBook taskBook);

    /** Returns the TaskBook */
    ReadOnlyTaskBook getTaskBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the task book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns the person with the same name as {@code name} if they exist in the task book.
     */
    Person findPerson(Name name);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task book.
     */
    void addTask(Task task);

    /**
     * Deletes the given task.
     * {@code task} must exist in the task book.
     */
    void deleteTask(Task task);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task book.
     */
    boolean hasTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task book.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Returns true if the person can be deleted.
     */
    boolean canDeletePerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the task book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the task book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the task book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the task book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskListPredicate(Predicate<Task> predicate);

    /** Returns an unmodifiable view of the sorted task list */
    ObservableList<Task> getSortedTaskList();

    /**
     * Updates the comparator of the sorted task list to sort by the given {@code comparator}.
     * @param comparator comparator to sort task list by.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSortedTaskList(Comparator<Task> comparator);
    /**
     * Updates the comparator of the sorted task list to be null, which sorts tasks by the time they were added.
     */
    void resetSortedTaskList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonListPredicate(Predicate<Person> predicate);

    /**
     * Updates the comparator of the sorted task list to sort by the given {@code comparator}.
     * @param comparator comparator to sort person list by.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedPersonList(Comparator<Person> comparator);

    /**
     * Updates the comparator of the sorted person list to be null, which sorts persons by the time they were added.
     */
    void resetSortedPersonList();

    /** Returns an unmodifiable view of the sorted person list */
    ObservableList<Person> getSortedPersonList();
}
