package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.task.Task.PREDICATE_SHOW_NON_ARCHIVED_TASKS;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Tag> filteredTags;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.versionedAddressBook.getPersonList());
        filteredTasks = new FilteredList<>(this.versionedAddressBook.getTaskList());
        filteredTags = new FilteredList<>(this.versionedAddressBook.getTagList());

        // Show only non-archived tasks
        updateFilteredTaskList(PREDICATE_SHOW_NON_ARCHIVED_TASKS);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
        this.versionedAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        versionedAddressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        versionedAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedAddressBook.setPerson(target, editedPerson);
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
        setAddressBook(versionedAddressBook.getCurrentState());
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
        setAddressBook(versionedAddressBook.getCurrentState());
    }

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
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
        return versionedAddressBook.equals(other.versionedAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTasks.equals(other.filteredTasks)
                && filteredTags.equals(other.filteredTags);
    }

    //=========== TaskList =================================================================================

    @Override
    public void addTask(Task task) {
        versionedAddressBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_NON_ARCHIVED_TASKS);
    }

    @Override
    public void deleteTask(Task target) {
        versionedAddressBook.removeTask(target);
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return versionedAddressBook.hasTask(task);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        versionedAddressBook.setTask(target, editedTask);
    }

    @Override
    public void sortByDeadline() {
        versionedAddressBook.sortByDeadline();
    }

    @Override
    public void sortById() {
        versionedAddressBook.sortById();
    }
    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public double getPercentageCompletion(Predicate<Task> predicate) {
        updateFilteredTaskList(predicate);

        double numOfTasks = filteredTasks.size();
        double numCompleted = 0.0;

        if (numOfTasks == 0) {
            return -1;
        }

        for (int i = 0; i < numOfTasks; i++) {
            Task currTask = filteredTasks.get(i);
            if (currTask.getIsCompleted()) {
                numCompleted += 1;
            }
        }

        return (numCompleted / numOfTasks) * 100.0;
    }

    //=========== Tag List Accessors =============================================================
    @Override
    public void addTag(Tag tag) {
        versionedAddressBook.addTag(tag);
    }

    @Override
    public void addTagCount(Tag tag) {
        versionedAddressBook.addTagCount(tag);
    }

    @Override
    public void decreaseTagCount(Tag toDelete) {
        versionedAddressBook.decreaseTagCount(toDelete);
    }

    @Override
    public void deleteTag(Tag target) {
        versionedAddressBook.removeTag(target);
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return versionedAddressBook.hasTag(tag);
    }

    @Override
    public void setTag(Tag target, Tag editedTag) {
        requireAllNonNull(target, editedTag);

        versionedAddressBook.setTag(target, editedTag);
    }

    @Override
    public void updateFilteredTagList(Predicate<Tag> predicate) {
        requireNonNull(predicate);
        filteredTags.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Tag} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Tag> getFilteredTagList() {
        return filteredTags;
    }
}
