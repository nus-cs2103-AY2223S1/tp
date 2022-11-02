package taskbook.model;

import static java.util.Objects.requireNonNull;
import static taskbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import taskbook.commons.core.GuiSettings;
import taskbook.commons.core.LogsCenter;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.Task;

/**
 * Represents the in-memory model of the task book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedTaskBook versionedTaskBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Person> sortedPersons;
    private final FilteredList<Task> filteredTasks;
    private final SortedList<Task> sortedTasks;

    /**
     * Initializes a ModelManager with the given taskBook and userPrefs.
     */
    public ModelManager(ReadOnlyTaskBook taskBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(taskBook, userPrefs);

        logger.fine("Initializing with task book: " + taskBook + " and user prefs " + userPrefs);

        versionedTaskBook = new VersionedTaskBook(new TaskBook(taskBook));
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(versionedTaskBook.getPersonList());
        sortedPersons = new SortedList<>(filteredPersons);
        filteredTasks = new FilteredList<>(versionedTaskBook.getTaskList());
        sortedTasks = new SortedList<>(filteredTasks);
    }

    public ModelManager() {
        this(new TaskBook(), new UserPrefs());
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
    public Path getTaskBookFilePath() {
        return userPrefs.getTaskBookFilePath();
    }

    @Override
    public void setTaskBookFilePath(Path taskBookFilePath) {
        requireNonNull(taskBookFilePath);
        userPrefs.setTaskBookFilePath(taskBookFilePath);
    }

    //=========== TaskBook ================================================================================

    @Override
    public void commitTaskBook() {
        versionedTaskBook.commit();
    }

    @Override
    public boolean canUndoTaskBook() {
        return versionedTaskBook.canUndo();
    }

    @Override
    public void undoTaskBook() {
        versionedTaskBook.undo();
    }

    @Override
    public boolean canRedoTaskBook() {
        return versionedTaskBook.canRedo();
    }

    @Override
    public void redoTaskBook() {
        versionedTaskBook.redo();
    }

    @Override
    public void setTaskBook(ReadOnlyTaskBook taskBook) {
        versionedTaskBook.resetData(taskBook);
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return versionedTaskBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return versionedTaskBook.hasPerson(person);
    }

    @Override
    public Person findPerson(Name name) {
        requireNonNull(name);
        return versionedTaskBook.findPerson(name);
    }

    /**
     * {@inheritDoc}
     * A person cannot be deleted if there are tasks associated with that person.
     */
    @Override
    public boolean canDeletePerson(Person person) {
        return versionedTaskBook.canDeletePerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        versionedTaskBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        versionedTaskBook.addPerson(person);
        updateFilteredPersonListPredicate(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        versionedTaskBook.setPerson(target, editedPerson);
    }

    @Override
    public void addTask(Task task) {
        versionedTaskBook.addTask(task);
        updateFilteredTaskListPredicate(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void deleteTask(Task task) {
        versionedTaskBook.deleteTask(task);
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return versionedTaskBook.hasTask(task);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        versionedTaskBook.setTask(target, editedTask);
    }

    //=========== Filtered Person & Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code TaskBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonListPredicate(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Person> getSortedPersonList() {
        return sortedPersons;
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        sortedPersons.setComparator(comparator);
    }

    @Override
    public void resetSortedPersonList() {
        sortedPersons.setComparator(null);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code TaskBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskListPredicate(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }


    /**
     * Returns an unmodifiable view of the sorted list of {@code Task} backed by the internal list of
     * {@code TaskBook}
     */
    @Override
    public ObservableList<Task> getSortedTaskList() {
        return sortedTasks;
    }

    @Override
    public void updateSortedTaskList(Comparator<Task> comparator) {
        requireNonNull(comparator);
        sortedTasks.setComparator(comparator);
    }

    @Override
    public void resetSortedTaskList() {
        sortedTasks.setComparator(null);
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
        return versionedTaskBook.equals(other.versionedTaskBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTasks.equals(other.filteredTasks)
                && sortedTasks.equals(other.sortedTasks);
    }

}
