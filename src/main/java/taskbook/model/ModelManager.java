package taskbook.model;

import static java.util.Objects.requireNonNull;
import static taskbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

    private final TaskBook taskBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given taskBook and userPrefs.
     */
    public ModelManager(ReadOnlyTaskBook taskBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(taskBook, userPrefs);

        logger.fine("Initializing with task book: " + taskBook + " and user prefs " + userPrefs);

        this.taskBook = new TaskBook(taskBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.taskBook.getPersonList());
        filteredTasks = new FilteredList<>(this.taskBook.getTaskList());
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
    public void setTaskBook(ReadOnlyTaskBook taskBook) {
        this.taskBook.resetData(taskBook);
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return taskBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return taskBook.hasPerson(person);
    }

    @Override
    public Person findPerson(Name name) {
        requireNonNull(name);
        return taskBook.findPerson(name);
    }

    /**
     * {@inheritDoc}
     * A person cannot be deleted if there are tasks associated with that person.
     */
    @Override
    public boolean canDeletePerson(Person person) {
        return taskBook.canDeletePerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        taskBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        taskBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        taskBook.setPerson(target, editedPerson);
    }

    @Override
    public void addTask(Task task) {
        taskBook.addTask(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskBook.deleteTask(task);
    }

    //=========== Filtered Person & Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedTaskBook}
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

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
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
        return taskBook.equals(other.taskBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTasks.equals(other.filteredTasks);
    }

}
