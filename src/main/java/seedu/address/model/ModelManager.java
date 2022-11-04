package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.deadline.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.UniqueStaffList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskMark;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Project> filteredProjects;
    private final FilteredList<Staff> filteredStaff;
    private final FilteredList<Task> filteredTasks;
    private final ArrayList<Task> targetTask = new ArrayList<>();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredProjects = new FilteredList<>(this.addressBook.getProjectList());
        filteredStaff = new FilteredList<Staff>(this.addressBook.getStaffList());
        filteredTasks = new FilteredList<>(this.addressBook.getTaskList());
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
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //=========== Projects ================================================================================

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return addressBook.hasProject(project);
    }

    @Override
    public void deleteProject(Project target) {
        addressBook.removeProject(target);
    }

    @Override
    public void sortProjects() {
        addressBook.sortProjects();
    }

    @Override
    public void addProject(Project project) {
        addressBook.addProject(project);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        addressBook.setProject(target, editedProject);
    }

    @Override
    public Optional<Project> getProjectWithName(ProjectName projectName) {
        if (!isValidProjectName(projectName)) {
            return Optional.empty();
        }
        for (Project project : this.filteredProjects) {
            if (project.getProjectName().toString().equalsIgnoreCase(projectName.toString())) {
                return Optional.<Project>of(project);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Project> getProjectWithIndex(Index projectIndex) {
        if (!isValidProjectIndex(projectIndex)) {
            return Optional.empty();
        }
        return Optional.<Project>of(this.filteredProjects.get(projectIndex.getZeroBased()));
    }

    private boolean isValidProjectName(ProjectName projectName) {
        requireNonNull(projectName);
        return this.filteredProjects.stream()
                .anyMatch(project ->
                        project.getProjectName().toString().equalsIgnoreCase(projectName.toString()));
    }

    private boolean isValidProjectIndex(Index projectIndex) {
        requireNonNull(projectIndex);
        return projectIndex.getZeroBased() < this.filteredProjects.size();
    }

    @Override
    public boolean projectHasDuplicateStaff(ProjectName projectName, Staff toEdit, Staff editWith) {
        requireNonNull(projectName);
        requireNonNull(toEdit);
        requireNonNull(editWith);

        assert isValidProjectName(projectName);

        Optional<Project> projectOptional = getProjectWithName(projectName);
        Project project = projectOptional.get();

        if (!toEdit.isSameStaff(editWith) && project.getStaffList().contains(editWith)) {
            return true;
        }
        return false;
    }

    //=========== Staff ================================================================================


    private boolean isValidStaffIndexInProject(ProjectName projectName, Index index) {
        requireNonNull(projectName);
        requireNonNull(index);
        if (!isValidProjectName(projectName)) {
            return false;
        }
        return index.getZeroBased() < this.filteredStaff.size();
    }

    @Override
    public Optional<Staff> getStaffFromProjectAtIndex(ProjectName projectName, Index index) {
        requireNonNull(projectName);
        requireNonNull(index);

        if (!isValidStaffIndexInProject(projectName, index)) {
            return Optional.empty();
        }

        return Optional.<Staff>of(this.filteredStaff.get(index.getZeroBased()));
    }

    @Override
    public void removeStaffFromProject(ProjectName projectName, Index index) {
        requireNonNull(projectName);
        requireNonNull(index);

        for (Project currentProject : this.filteredProjects) {
            if (currentProject.getProjectName().toString().equalsIgnoreCase(projectName.toString())) {
                if (index.getZeroBased() >= currentProject.getStaffList().size()) {
                    return;
                } else {
                    Staff toRemove = this.filteredStaff.get(index.getZeroBased());
                    currentProject.getStaffList().remove(toRemove);
                    break;
                }
            }
        }
    }

    @Override
    public boolean targetProjectContainsStaff(Index projectIndex, Staff toAdd) {
        requireNonNull(projectIndex);
        requireNonNull(toAdd);
        return this.filteredProjects.get(projectIndex.getZeroBased()).getStaffList().contains(toAdd);
    }

    @Override
    public void addStaffToProject(Index projectIndex, Staff staff) {
        requireNonNull(projectIndex);
        requireNonNull(staff);
        // Guaranteed that staff and project are valid
        this.filteredProjects.get(projectIndex.getZeroBased()).getStaffList().add(staff);
    }

    @Override
    public void editStaffInProject(ProjectName projectName, Staff toEdit, Staff editWith) {
        requireNonNull(projectName);
        requireNonNull(toEdit);

        Optional<Project> projectOptional = getProjectWithName(projectName);
        Project project = projectOptional.get();
        project.getStaffList().setStaff(toEdit, editWith);
    }

    //=========== Tasks ================================================================================

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return addressBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        addressBook.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        addressBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        addressBook.setTask(target, editedTask);
    }

    @Override
    public void sortComplete() {
        addressBook.sortComplete();
    }

    @Override
    public void markTask(Index index) {
        Task task = getFilteredTaskList().get(index.getZeroBased());
        TaskDescription taskDescription = task.getTaskDescription();
        Deadline deadline = task.getTaskDeadline();
        TaskMark newTaskMark = new TaskMark("true");
        Task newTask = new Task(deadline, taskDescription, newTaskMark);
        setTask(task, newTask);
    }

    @Override
    public void unmarkTask(Index index) {
        Task task = getFilteredTaskList().get(index.getZeroBased());
        TaskDescription taskDescription = task.getTaskDescription();
        Deadline deadline = task.getTaskDeadline();
        TaskMark newTaskMark = new TaskMark("false");
        Task newTask = new Task(deadline, taskDescription, newTaskMark);
        setTask(task, newTask);
    }

    public void sortTasks() {
        addressBook.sortTasks();
    }

    //=========== Filtered Project List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Project} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
    }

    //=========== Filtered Staff List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Project} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Staff> getFilteredStaffList() {
        return filteredStaff;
    }

    @Override
    public void updateFilteredStaffList(Predicate<Staff> predicate) {
        requireNonNull(predicate);
        filteredStaff.setPredicate(predicate);
    }

    @Override
    public void setFilteredStaffList(UniqueStaffList staffList) {
        requireNonNull(staffList);
        addressBook.setStaffList(staffList);
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
                && filteredProjects.equals(other.filteredProjects)
                && filteredTasks.equals(other.filteredTasks)
                && filteredStaff.equals(other.filteredStaff)
                && targetTask.equals(other.targetTask);
    }

}
