package jarvis.model.util;

import java.time.LocalDate;

import jarvis.model.ReadOnlyTaskBook;
import jarvis.model.Task;
import jarvis.model.TaskBook;
import jarvis.model.TaskDeadline;
import jarvis.model.TaskDesc;

/**
 * Contains utility methods for populating {@code JARVIS} with sample tasks.
 */
public class SampleTaskUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskDesc("Prepare tutorial slides"), new TaskDeadline(LocalDate.parse("2022-11-01"))),
            new Task(new TaskDesc("Mark missions"), null),
            new Task(new TaskDesc("Consultation"), new TaskDeadline(LocalDate.parse("2022-11-01")))
        };
    }

    public static ReadOnlyTaskBook getSampleTaskBook() {
        TaskBook sampleTaskBook = new TaskBook();
        for (Task sampleTask : getSampleTasks()) {
            sampleTaskBook.addTask(sampleTask);
        }
        return sampleTaskBook;
    }
}
