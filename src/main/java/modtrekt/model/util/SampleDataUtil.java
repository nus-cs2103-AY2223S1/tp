package modtrekt.model.util;

import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.TaskBook;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * Contains utility methods for populating {@code TaskBook} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Description("Assignment 1")),
            new Task(new Description("Attend lecture")),
            new Task(new Description("Pay school fees")),
            new Task(new Description("Attend exam")),
            new Task(new Description("Study for exam")),
            new Task(new Description("Buy groceries"))

        };
    }

    public static ReadOnlyTaskBook getSampleTaskBook() {
        TaskBook sampleAb = new TaskBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleAb.addTask(sampleTask);
        }
        return sampleAb;
    }

}
