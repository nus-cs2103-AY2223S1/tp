package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.supplyItem.SupplyItem;
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
    Predicate<SupplyItem> PREDICATE_SHOW_ALL_SUPPLYITEMS = unused -> true;

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
     * Adds a new task to taskList
     */
    void addTask(Task task);


    /**
     * Adds a new item to inventory
     */
    void addItem(SupplyItem item);

    /**
     * Return true if there is duplicated task in the taskList
     */
    boolean hasTask(Task task);

    /**
     * Return true if there is duplicated item in the inventory
     */
    boolean hasItem(SupplyItem item);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code task} must exist in the task list.
     */
    void setTask(Task task, Index targetIndex);
    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code task} must exist in the task list.
     */
    void setItem(SupplyItem item, Index targetIndex);


    /**
     * Deletes the task at the specified {@code Index}.
     */
    void deleteTask(Index index);

    /**
     * Deletes the item at the specified {@code Index}.
     */
    void deleteItem(Index index);

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


    /** Returns an unmodifiable view of the filtered inventory */
    ObservableList<SupplyItem> getFilteredInventory();

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
     * Updates the filter of the filtered inventory to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInventory(Predicate<SupplyItem> predicate);
}
