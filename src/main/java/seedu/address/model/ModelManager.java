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
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final ArchivedTaskBook archivedTaskBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Task> filteredArchivedTasks;;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyAddressBook archivedTaskBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, archivedTaskBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook +
                "Archived Task Book: " + archivedTaskBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.archivedTaskBook = new ArchivedTaskBook(archivedTaskBook);
        filteredTasks = new FilteredList<>(this.addressBook.getPersonList());
        filteredArchivedTasks = new FilteredList<>(this.archivedTaskBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new ArchivedTaskBook(), new UserPrefs());
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
    public Path getArchivedTaskBookFilePath() {
        return userPrefs.getArchivedTaskBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setArchivedTaskBookFilePath(Path archivedTaskBookFilePath) {
        requireNonNull(archivedTaskBookFilePath);
       userPrefs.setArchivedTaskBookFilePath(archivedTaskBookFilePath);
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
    public boolean hasPerson(Task task) {
        requireNonNull(task);
        return addressBook.hasPerson(task);
    }

    @Override
    public void deletePerson(Task target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Task task) {
        addressBook.addPerson(task);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        addressBook.setPerson(target, editedTask);
    }


    //=========== ArchivedTaskBook ================================================================================

    @Override
    public ReadOnlyAddressBook getArchivedAddressBook() {
        return archivedTaskBook;
    }

    @Override
    public void archivedTask(Task task) {
        addressBook.removePerson(task);
        archivedTaskBook.addTask(task);
    }

    @Override
    public boolean hasTaskInArchives(Task task) {
        requireAllNonNull(task);
        return archivedTaskBook.hasTask(task);
    }

    @Override
    public ObservableList<Task> getArchivedTaskList() {
        return archivedTaskBook.getPersonList();
    }

    @Override
    public void setArchivedTaskBook(ReadOnlyAddressBook addressBook) {
        this.archivedTaskBook.resetData(addressBook);
    }

    @Override
    public String getArchivedTasks() {
        return archivedTaskBook.toString();
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredPersonList() {
        return filteredTasks;
    }

    @Override
    public ObservableList<Task> getFilteredArchivedTaskList() {
        return filteredArchivedTasks;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateFilteredArchivedTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredArchivedTasks.setPredicate(predicate);
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
                && archivedTaskBook.equals(other.archivedTaskBook)
                && filteredTasks.equals(other.filteredTasks)
                && filteredArchivedTasks.equals(other.filteredArchivedTasks);
    }

}
