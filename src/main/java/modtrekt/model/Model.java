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
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = module -> true;
    Predicate<Module> PREDICATE_HIDE_DONE_MODULES = module -> !module.isDone();
    Predicate<Task> PREDICATE_HIDE_DONE_TASKS = task -> !task.isDone();
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
     * Returns the user prefs' task book file path.
     */
    Path getTaskBookFilePath();

    /**
     * Returns the user prefs' module list file path.
     */
    Path getModuleListFilePath();

    /**
     * Sets the user prefs' task book/module list file path.
     */
    void setTaskBookFilePath(Path taskBookFilePath);
    void setModuleListFilePath(Path moduleListFilePath);

    /**
     * Replaces task book data with the data in {@code taskBook}.
     */
    void setTaskBook(ReadOnlyTaskBook taskBook);

    /** Returns the taskBook */
    ReadOnlyTaskBook getTaskBook();

    /**
     * Replaces module list data with the data in {@code moduleList}.
     */
    void setModuleList(ReadOnlyModuleList moduleList);

    /** Returns the ModuleList */
    ReadOnlyModuleList getModuleList();

    /**
     * Deletes the given task.
     * The task must exist in the task book.
     */
    void deleteTask(Task target);

    /** Check if given module exists within module list */
    boolean hasModule(Module module);

    /** Check if given module exists within module list based on module code */
    boolean hasModuleWithModCode(ModCode code);


    /** Updates module list after task removal */
    void updateModuleTaskCount(Task t);

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

    /** Gets the current module code, if any */
    ModCode getCurrentModule();

    /** Sets the current module code */
    void setCurrentModule(ModCode code);

    /**
     * Updates the ModCodes of tasks after an edit to module.
     */
    void updateTaskModule(ModCode oldCode, ModCode newCode);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task book.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task book.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Marks all the tasks in the done module as done.
     *
     * @param code the code of the module that is marked as done.
     */
    void setDoneModuleTasksAsDone(ModCode code);

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
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the module list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the module list.
     */
    void setModule(Module target, Module editedModule);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);
}
