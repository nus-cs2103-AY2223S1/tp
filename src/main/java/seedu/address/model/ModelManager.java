package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.deadline.Deadline;
import seedu.address.model.project.Project;
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

    //=========== Staff ================================================================================

    @Override
    public boolean hasStaff(Staff staff) {
        requireNonNull(staff);
        return addressBook.hasStaff(staff);
    }

    @Override
    public void deleteStaff(Staff target) {
        addressBook.removeStaff(target);
    }

    @Override
    public void addStaff(Staff staff) {
        addressBook.addStaff(staff);
        updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFF);
    }

    @Override
    public void setStaff(Staff target, Staff editedStaff) {
        requireAllNonNull(target, editedStaff);

        addressBook.setStaff(target, editedStaff);
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

    // TODO: 19/10/2022 might not need this??
    @Override
    public void setTargetTask(Task target) {
        requireNonNull(target);
        if (this.targetTask.isEmpty()) {
            this.targetTask.add(target);
        } else {
            this.targetTask.set(0, target);
        }
    }

    @Override
    public ArrayList<Task> getTargetTask() {
        return targetTask;
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
    public void setFilteredStaffList(Project project) {
        if (project == null) {
            addressBook.setStaffList(new UniqueStaffList());
        } else {
            addressBook.setStaffList(project.getStaffList());
        }
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
