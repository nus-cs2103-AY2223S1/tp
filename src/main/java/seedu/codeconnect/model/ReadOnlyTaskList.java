package seedu.codeconnect.model;

import javafx.collections.ObservableList;
import seedu.codeconnect.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTaskList {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Task> getTaskList();

}
