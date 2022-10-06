package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of a TaskList
 */
public interface ReadOnlyTaskList {

    /**
     * Returns an unmodifiable view of the task list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getTaskList();
}
