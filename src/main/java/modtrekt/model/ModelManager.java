package modtrekt.model;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Filter;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import modtrekt.commons.core.GuiSettings;
import modtrekt.commons.core.LogsCenter;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;

/**
 * Represents the in-memory model of the task book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModuleList moduleList;
    private final TaskBook taskBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Module> filteredModules;
    private ModCode currentModule;

    /**
     * Initializes a ModelManager with the given taskBook and userPrefs.
     */
    public ModelManager(ReadOnlyModuleList moduleList, ReadOnlyTaskBook taskBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(moduleList, taskBook, userPrefs);

        logger.fine("Initializing with task book: " + taskBook + " and user prefs " + userPrefs);

        this.moduleList = new ModuleList(moduleList);
        this.taskBook = new TaskBook(taskBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.taskBook.getTaskList());
        filteredModules = new FilteredList<>(this.moduleList.getModuleList());
        currentModule = null;
    }

    public ModelManager() {
        this(new ModuleList(), new TaskBook(), new UserPrefs());
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

    @Override
    public Path getModuleListFilePath() {
        return userPrefs.getModuleListFilePath();
    }

    @Override
    public void setModuleListFilePath(Path moduleListFilePath) {
        requireNonNull(moduleListFilePath);
        userPrefs.setModuleListFilePath(moduleListFilePath);
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
        updateModuleTask(target);
    }

    @Override
    public void addTask(Task t) {
        taskBook.addTask(t);
        updateModuleTask(t);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        taskBook.setTask(target, editedTask);
    }

    @Override
    public void updateModuleTask(Task t) {
        Module toUpdate = parseModuleFromCode(t.getModule());
        FilteredList<Task> tempList = new FilteredList<>(this.taskBook.getTaskList());
        Predicate<Task> newPredicate = task -> task.getModule().equals(toUpdate.getCode());
        tempList.setPredicate(newPredicate);
        toUpdate.updateTaskCount(tempList.size());
        setModule(toUpdate, toUpdate);
    }

    //=========== ModuleList ================================================================================

    @Override
    public void setModuleList(ReadOnlyModuleList moduleList) {
        this.moduleList.resetData(moduleList);
    }

    @Override
    public ReadOnlyModuleList getModuleList() {
        return moduleList;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleList.hasModule(module);
    }

    @Override
    public boolean hasModuleWithModCode(ModCode code) {
        requireNonNull(code);
        return moduleList.hasModuleWithModCode(code);
    }

    @Override
    public void deleteModule(Module target) {
        moduleList.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        moduleList.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public Module parseModuleFromCode(ModCode code) {
        requireNonNull(code);
        return moduleList.getModuleFromCode(code);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);
        moduleList.setModule(target, editedModule);
    }

    @Override
    public void deleteTasksOfModule(Module target) {
        requireAllNonNull(target);
        taskBook.removeTasksWithModCode(target.getCode());
    }

    @Override
    public ModCode getCurrentModule() {
        return currentModule;
    }

    @Override
    public void setCurrentModule(ModCode code) {
        currentModule = code;
        if (currentModule == null) {
            updateFilteredModuleList(Model.PREDICATE_SHOW_ALL_MODULES);
            updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        } else {
            updateFilteredModuleList(model -> model.getCode().equals(code));
            updateFilteredTaskList(task -> task.getModule().equals(code));
        }
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
        if (currentModule == null) {
            filteredTasks.setPredicate(predicate);
        } else {
            Predicate<Task> newPredicate = task -> predicate.test(task)
                    && task.getModule().equals(currentModule);
            filteredTasks.setPredicate(newPredicate);
        }
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
                && filteredTasks.equals(other.filteredTasks)
                && moduleList.equals(other.moduleList)
                && filteredModules.equals(other.filteredModules);
    }

    //=========== Filtered Module List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedModuleList}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        if (currentModule == null) {
            filteredModules.setPredicate(predicate);
        } else {
            Predicate<Module> newPredicate = module -> predicate.test(module)
                    && module.getCode().equals(currentModule);
            filteredModules.setPredicate(newPredicate);
        }
    }

}
