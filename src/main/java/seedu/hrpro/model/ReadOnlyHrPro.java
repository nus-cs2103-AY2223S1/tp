package seedu.hrpro.model;

import javafx.collections.ObservableList;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.task.Task;

/**
 * Unmodifiable view of an hr pro
 */
public interface ReadOnlyHrPro {

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
