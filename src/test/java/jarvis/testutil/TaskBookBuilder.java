package jarvis.testutil;

import jarvis.model.Task;
import jarvis.model.TaskBook;

/**
 * A utility class to help with building TaskBook objects.
 * Example usage: <br>
 *     {@code TaskBook tb = new TaskBookBuilder().withTask("Mark quest 1").build();}
 */
public class TaskBookBuilder {

    private TaskBook taskBook;

    public TaskBookBuilder() {
        taskBook = new TaskBook();
    }

    public TaskBookBuilder(TaskBook taskBook) {
        this.taskBook = taskBook;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskBook} that we are building.
     */
    public TaskBookBuilder withTask(Task task) {
        taskBook.addTask(task);
        return this;
    }

    public TaskBook build() {
        return taskBook;
    }
}