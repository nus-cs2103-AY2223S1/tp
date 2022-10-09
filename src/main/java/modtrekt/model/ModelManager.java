package modtrekt.model;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import modtrekt.commons.core.GuiSettings;
import modtrekt.commons.core.LogsCenter;
import modtrekt.model.task.Task;

/**
 * Represents the in-memory model of the task book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskBook taskBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given taskBook and userPrefs.
     */
    public ModelManager(ReadOnlyTaskBook taskBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(taskBook, userPrefs);

        logger.fine("Initializing with task book: " + taskBook + " and user prefs " + userPrefs);

        this.taskBook = new TaskBook(taskBook);
        this.userPrefs = new UserPrefs(userPrefs);
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
    public void deleteTask(Task target) {
        taskBook.removeTask(target);
    }

    @Override
    public void addTask(Task t) {
        taskBook.addTask(t);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskBook.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskBook}
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
                && filteredTasks.equals(other.filteredTasks);
    }

}
