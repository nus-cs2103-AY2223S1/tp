package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.project.Project;
import seedu.address.model.staff.Staff;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the project list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectList();

    /**
     * Returns an unmodifiable view of the staff list.
     * This list will not contain any duplicate staff.
     */
    ObservableList<Staff> getStaffList();

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

}
