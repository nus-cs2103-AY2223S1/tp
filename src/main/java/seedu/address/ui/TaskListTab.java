package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import seedu.address.model.task.Task;

/**
 * Tab containing the list of tasks.
 */
public class TaskListTab extends Tab {
    private static final String TAB_NAME = "tasks";

    /**
     * Creates a {@code TaskListTab} with the given {@code ObservableList}.
     */
    public TaskListTab(ObservableList<Task> taskList) {
        super(TAB_NAME, new TaskListPanel(taskList).getRoot());
    }
}
