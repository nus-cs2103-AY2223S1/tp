package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<SupplyItem> PREDICATE_SHOW_ALL_SUPPLY_ITEMS = unused -> true;

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

    /** Returns the TaskList */
    ReadOnlyTaskList getTaskList();

    /** Returns the Inventory */
    ReadOnlyInventory getInventory();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book,
     * ignoring {@code excludedPerson}.
     */
    boolean hasPersonExcluding(Person person, Person excludedPerson);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds a new supply {@code item} to inventory.
     */
    void addSupplyItem(SupplyItem item);

    /**
     * Returns true if there is a duplicated supply item in the inventory.
     */
    boolean hasSupplyItem(SupplyItem item);

    /**
     * Replaces the given supply item {@code target} with {@code editedSupplyItem}.
     * {@code item} must exist in the inventory.
     */

    void setSupplyItem(SupplyItem item, Index targetIndex);

    /**
     * Deletes the supply item at the specified {@code index}.
     */
    void deleteSupplyItem(Index index);

    /**
     * Adds a new task to taskList
     */
    void addTask(Task task);

    /**
     * Returns true if there is duplicated task in the taskList
     */
    boolean hasTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code task} must exist in the task list.
     */
    void setTask(Task task, Index targetIndex);

    /**
     * Deletes the task at the specified {@code index}.
     */
    void deleteTask(Index index);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /** Returns an unmodifiable view of the filtered supplyItem list */
    ObservableList<SupplyItem> getFilteredSupplyItemList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the filter of the filtered supply item to filter by the given {@code predicate}.
     * @throws NullPointerException if (@code predicate) is null.
     */
    void updateFilteredSupplyItemList(Predicate<SupplyItem> predicate);
}
