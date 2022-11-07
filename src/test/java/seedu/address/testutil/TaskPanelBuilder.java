package seedu.address.testutil;

import seedu.address.model.TaskPanel;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building TaskPanel objects.
 * Example usage: <br>
 *     {@code TaskPanel tp = new TaskPanelBuilder().withTask(TASK_ONE).build();}
 */
public class TaskPanelBuilder {

    private final TaskPanel taskPanel;

    public TaskPanelBuilder() {
        taskPanel = new TaskPanel();
    }

    /**
     * Adds a new {@code Task} to the {@code TaskPanel} that we are building.
     */
    public TaskPanelBuilder withTask(Task task) {
        taskPanel.addTask(task);
        return this;
    }

    public TaskPanel build() {
        return taskPanel;
    }
}
