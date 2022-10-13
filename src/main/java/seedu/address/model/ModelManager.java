package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.SupplyItem;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final TaskList taskList;
    private final Inventory inventory;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<SupplyItem> filteredSupplyItems;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyTaskList taskList, ReadOnlyInventory inventory) {
        requireAllNonNull(addressBook, userPrefs, taskList, inventory);

        logger.fine("Initializing with address book: " + addressBook + " , user prefs " + userPrefs
                + " , tasks " + taskList + " and inventory " + inventory);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.taskList = new TaskList(taskList);
        this.inventory = new Inventory(inventory);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.taskList.getTaskList());
        filteredSupplyItems = new FilteredList<>(this.inventory.getSupplyItems());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new TaskList(), new Inventory());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== Inventory ==================================================================================
    @Override
    public void addSupplyItem(SupplyItem item) {
        inventory.addSupplyItem(item);
        updateFilteredSupplyItemList(PREDICATE_SHOW_ALL_SUPPLY_ITEMS);
    }

    @Override
    public boolean hasSupplyItem(SupplyItem item) {
        return inventory.hasSupplyItem(item);
    }

    @Override
    public void setSupplyItem(SupplyItem item, Index targetIndex) {
        inventory.setSupplyItem(item, targetIndex);
    }

    @Override
    public void deleteSupplyItem(Index index) {
        inventory.deleteTask(index);
    }

    @Override
    public ReadOnlyInventory getInventory() {
        return this.inventory;
    }

    //=========== TaskList ===================================================================================
    @Override
    public void addTask(Task task) {
        taskList.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public boolean hasTask(Task task) {
        return taskList.hasTask(task);
    }

    @Override
    public void setTask(Task task, Index targetIndex) {
        taskList.setTask(task, targetIndex);
    }

    @Override
    public void deleteTask(Index index) {
        taskList.deleteTask(index);
    }

    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasPersonExcluding(Person person, Person excludedPerson) {
        requireNonNull(person);
        requireNonNull(excludedPerson);
        return addressBook.hasPersonExcluding(person, excludedPerson);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Task List Accessors =============================================================
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    //=========== Filtered SupplyItem List Accessors =======================================================
    @Override
    public ObservableList<SupplyItem> getFilteredSupplyItemList() {
        return filteredSupplyItems;
    }

    @Override
    public void updateFilteredSupplyItemList(Predicate<SupplyItem> predicate) {
        requireNonNull(predicate);
        filteredSupplyItems.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTasks.equals(other.filteredTasks)
                && filteredSupplyItems.equals(other.filteredSupplyItems);
    }

}
