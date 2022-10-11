package modtrekt.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import modtrekt.commons.core.GuiSettings;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;
    Predicate<Task> PREDICATE_HIDE_ARCHIVED_TASKS = task -> !task.isArchived();
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = task -> true;

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
    Path getTaskBookFilePath();
    Path getModuleListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setTaskBookFilePath(Path addressBookFilePath);
    void setModuleListFilePath(Path addressBookFilePath);

    /**
     * Replaces task book data with the data in {@code taskBook}.
     */
    void setTaskBook(ReadOnlyTaskBook addressBook);

    /** Returns the taskBook */
    ReadOnlyTaskBook getTaskBook();
    void setModuleList(ReadOnlyModuleList addressBook);

    /** Returns the ModuleList */
    ReadOnlyModuleList getModuleList();

    /**
     * Deletes the given task.
     * The task must exist in the address book.
     */
    void deleteTask(Task target);

    /** Check if given module exists within module list */
    boolean hasModule(Module module);

    /** Check if given module exists within module list based on module code */
    boolean hasModuleWithModCode(ModCode code);


    /** Updates module list after task removal */
    void updateModuleRemoveTask(Task t);

    /** Updates module list after adding task */
    void updateModuleAddTask(Task t);

    /** Updates task list after module removal */
    void deleteTasksOfModule(Module target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task book.
     */
    void addTask(Task t);

    /** Deletes the given module, must exist within the module list */
    void deleteModule(Module target);

    /** Parses module within module list from a given module code */
    Module parseModuleFromCode(ModCode code);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the address book.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the task book.
     */
    void addModule(Module module);

    /**
     * Replaces the given person {@code target} with {@code editedModule}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedModule} must not be the same as another existing person in the address book.
     */
    void setModule(Module target, Module editedModule);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);
}
