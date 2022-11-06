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
import seedu.address.model.teammate.Teammate;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final TaskPanel taskPanel;
    private final UserPrefs userPrefs;
    private final FilteredList<Teammate> filteredTeammates;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given addressBook, taskPanel and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyTaskPanel taskPanel, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, taskPanel, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", task panel: " + taskPanel
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.taskPanel = new TaskPanel(taskPanel);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTeammates = new FilteredList<>(this.addressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.taskPanel.getTaskList(), PREDICATE_INCOMPLETE_TASKS);
    }

    public ModelManager() {
        this(new AddressBook(), new TaskPanel(), new UserPrefs());
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
    public boolean hasPerson(Teammate teammate) {
        requireNonNull(teammate);
        return addressBook.hasPerson(teammate);
    }

    @Override
    public void deletePerson(Teammate target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Teammate teammate) {
        addressBook.addPerson(teammate);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Teammate target, Teammate editedTeammate) {
        requireAllNonNull(target, editedTeammate);

        addressBook.setPerson(target, editedTeammate);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Teammate> getFilteredPersonList() {
        return filteredTeammates;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskPanel}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Teammate> predicate) {
        requireNonNull(predicate);
        filteredTeammates.setPredicate(predicate);
    }

    /** Replaces the given new task list {@code newTasks} with {@code editedTasks}. */
    @Override
    public void setFilteredTaskList(ObservableList<Task> newTasks) {
        taskPanel.setTasks(newTasks);
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
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
                && taskPanel.equals(other.taskPanel)
                && userPrefs.equals(other.userPrefs)
                && filteredTeammates.equals(other.filteredTeammates);
    }

    //=========== TaskPanel ================================================================================

    @Override
    public ReadOnlyTaskPanel getTaskPanel() {
        return taskPanel;
    }

    @Override
    public void setTaskPanel(ReadOnlyTaskPanel taskPanel) {
        this.taskPanel.resetData(taskPanel);
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskPanel.hasTask(task);
    }

    @Override
    public void addTask(Task task) {
        taskPanel.addTask(task);
        // TODO: updateFilteredTaskList
    }

    @Override
    public void deleteTask(Task deletedTask) {
        taskPanel.removeTask(deletedTask);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        taskPanel.setTask(target, editedTask);
    }
}
