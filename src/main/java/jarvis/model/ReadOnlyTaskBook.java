package jarvis.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a task book
 */
public interface ReadOnlyTaskBook {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

}
