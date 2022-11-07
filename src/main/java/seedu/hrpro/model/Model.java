package seedu.hrpro.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.hrpro.commons.core.GuiSettings;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.UniqueStaffList;
import seedu.hrpro.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Staff> PREDICATE_SHOW_ALL_STAFF = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

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
     * Returns the user prefs' hr pro file path.
     */
    Path getHrProFilePath();

    /**
     * Sets the user prefs' hr pro file path.
     */
    void setHrProFilePath(Path hrProFilePath);

    /**
     * Replaces hr pro data with the data in {@code hrPro}.
     */
    void setHrPro(ReadOnlyHrPro hrPro);

    /**
     * Returns the HrPro
     */
    ReadOnlyHrPro getHrPro();

    //=========== Projects ================================================================================

    /**
     * Returns true if a project with the same identity as {@code project} exists in hr pro.
     */
    boolean hasProject(Project project);

    /**
     * Deletes the given project.
     * The project must exist in hr pro.
     */
    void deleteProject(Project target);

    /**
     * Sorts the project list.
     */
    void sortProjects();

    /**
     * Adds the given project.
     * {@code project} must not already exist in hr pro.
     */
    void addProject(Project project);

    /**
     * Replaces the given project {@code target} with {@code editedProject}.
     * {@code target} must exist in hr pro.
     * The project identity of {@code editedProject} must not be the same as
     * another existing project in hr pro.
     */
    void setProject(Project target, Project editedProject);

    /**
     * Updates the filter of the filtered project list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProjectList(Predicate<Project> predicate);

    /**
     * Returns an unmodifiable view of the filtered project list
     */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Returns an Optional that may or may not contain a Project with given {@code projectName}.
     */
    Optional<Project> getProjectWithName(ProjectName projectName);

    /**
     * Returns an Optional that may or may not contain a Project with given {@code projectIndex}.
     */
    Optional<Project> getProjectWithIndex(Index projectIndex);

    /**
     * Checks if the project in the model contains the staff {@code toAdd}.
     */
    boolean targetProjectContainsStaff(Index projectIndex, Staff toAdd);

    /**
     * Checks if the project with the given {@code projectName} contains a duplicate staff
     * once the staff {@code toEdit} is edited with {@code editWith}
     */
    boolean projectHasDuplicateStaff(ProjectName projectName, Staff toEdit, Staff editWith);

    //=========== Staff ================================================================================

    /**
     * Returns an unmodifiable view of the filtered staff list
     */
    ObservableList<Staff> getFilteredStaffList();

    /**
     * Updates the filter of the filtered staff list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStaffList(Predicate<Staff> predicate);

    /** Sets filtered Staff list to be {@code uniqueStaffList} specified by the view command */
    void setFilteredStaffList(UniqueStaffList uniqueStaffList);

    /**
     * Returns an Optional that contains a staff that is at the {@code index} of the project with the
     * {@code projectName}
     */
    Optional<Staff> getStaffFromProjectAtIndex(ProjectName projectName, Index index);

    /**
     * Returns if it can delete the staff at {@code index} from the staff list of the project with {@code projectName}.
     * If it can delete, it will delete in this method.
     */
    boolean isSuccessStaffDelete(Project targetProject, Index index);

    /**
     * Adds the staff {@code toAdd} to the staff list of the project with {@code projectName}.
     */
    void addStaffToProject(Index projectIndex, Staff toAdd);

    /**
     * Return if it can edit the Staff {@code toEdit} in the staff list of the project with {@code projectName}
     * with the Staff {@code editWith}. If it can edit, it will edit in this method.
     */
    boolean isSuccessStaffEdit(Project targetProject, Staff toEdit, Staff editWith);

    //=========== Tasks ================================================================================
    /**
     * Returns true if a task with the same identity as {@code task} exists in hr pro.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in hr pro.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in hr pro.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in hr pro.
     * The task identity of {@code editedTask} must not be the same as
     * another existing task in hr pro.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Updates the tasks to show the uncompleted tasks at the top and the rest at the bottom.
     */
    void sortComplete();

    /**
     * Updates the task of the filtered task list to set it to be a completed task.
     *
     * @param targetIndex The index of the task in the filtered task list.
     */
    void markTask(Index targetIndex);

    /**
     * Updates the task of the filtered task list to set it to be a uncompleted task.
     *
     * @param targetIndex The index of the task in the filtered task list.
     */
    void unmarkTask(Index targetIndex);

    /**
     * Sorts the task list.
     */
    void sortTasks();

    /**
     * Returns an Optional that may or may not contain a Task with given {@code taskIndex}.
     */
    Optional<Task> getTaskWithIndex(Index taskIndex);

    /**
     * Returns true if the {@code taskIndex} is within the filtered task list.
     * @param taskIndex
     * @return boolean
     */
    boolean isValidTaskIndex(Index taskIndex);
}
