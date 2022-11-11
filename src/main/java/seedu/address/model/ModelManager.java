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

    private final TaskList addressBook;
    private final ArchivedTaskList archivedTaskList;
    private final UserPrefs userPrefs;
    private FilteredList<Task> filteredTasks;
    private String filterStatus = "";
    private final FilteredList<Task> filteredArchivedTasks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTaskList addressBook,
                        ReadOnlyTaskList archivedTaskList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, archivedTaskList, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + "Archived Task Book: " + archivedTaskList + " and user prefs " + userPrefs);

        this.addressBook = new TaskList(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.archivedTaskList = new ArchivedTaskList(archivedTaskList);
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
        filteredArchivedTasks = new FilteredList<>(this.archivedTaskList.getTaskList());
    }

    public ModelManager() {
        this(new TaskList(), new ArchivedTaskList(), new UserPrefs());
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
    public Path getArchivedTaskListFilePath() {
        return userPrefs.getArchivedTaskListFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setArchivedTaskListFilePath(Path archivedTaskListFilePath) {
        requireNonNull(archivedTaskListFilePath);
        userPrefs.setArchivedTaskListFilePath(archivedTaskListFilePath);
    }

    //=========== TaskList ================================================================================

    @Override
    public void setAddressBook(ReadOnlyTaskList addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyTaskList getAddressBook() {
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
        updateFilteredTaskList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        addressBook.setPerson(target, editedTask);
    }


    //=========== ArchivedTaskBook ================================================================================

    @Override
    public ReadOnlyTaskList getArchivedTaskList() {
        return archivedTaskList;
    }

    @Override
    public void archivedTask(Task task) {
        addressBook.removePerson(task);
        archivedTaskList.addTask(task);
    }

    @Override
    public boolean hasTaskInArchives(Task task) {
        requireAllNonNull(task);
        return archivedTaskList.hasTask(task);
    }



    @Override
    public void setArchivedTaskList(ReadOnlyTaskList taskList) {
        this.archivedTaskList.resetData(taskList);
    }

    @Override
    public String getArchivedTasks() {
        return archivedTaskList.toString();
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
    public ObservableList<Task> getObservableArchivedTaskList() {
        return this.archivedTaskList.getTaskList();
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }
    @Override
    public void updateFilterStatus(String filter) {
        requireNonNull(filter);
        if (this.filterStatus.equalsIgnoreCase("Showing all tasks") || this.filterStatus.equals("")) {
            this.filterStatus = filter;
        } else {
            this.filterStatus += ", " + filter;
        }
    }

    @Override
    public void updateFilterStatus(String filter, boolean isNewFilterSet) {
        requireNonNull(filter);
        if (isNewFilterSet) {
            this.filterStatus = filter;
        } else {
            this.filterStatus += ", " + filter;
        }
    }

    @Override
    public String getFilterStatus() {
        return this.filterStatus;
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
                && archivedTaskList.equals(other.archivedTaskList)
                && filteredTasks.equals(other.filteredTasks)
                && filteredArchivedTasks.equals(other.filteredArchivedTasks);
    }

}
