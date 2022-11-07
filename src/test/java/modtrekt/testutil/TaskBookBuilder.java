package modtrekt.testutil;

import modtrekt.model.TaskBook;
import modtrekt.model.task.Task;

/**
 * A utility class to help with building TaskBook objects.
 * Example usage: <br>
 *     {@code TaskBook ab = new TaskBookBuilder().withTask("Do something").build();}
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
    public TaskBookBuilder withTask(Task t) {
        taskBook.addTask(t);
        return this;
    }

    public TaskBook build() {
        return taskBook;
    }
}
