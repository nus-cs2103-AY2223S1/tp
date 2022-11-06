package modtrekt.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import modtrekt.model.module.ModCode;
import modtrekt.model.task.Deadline;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;
import modtrekt.testutil.TaskBuilder;

public class TaskAndDeadlineTest {

    @Test
    public void prioritizeTask_isTask_returnsTask() {
        Task task = new TaskBuilder().build();
        Task newTask = task.setPriority(Task.Priority.HIGH);
        assertTrue(newTask instanceof Task);
    }

    @Test
    public void prioritizeTask_isDeadline_returnsDeadline() {
        Task deadline = new Deadline(new Description("desc"), new ModCode("CS2102"),
                LocalDate.of(2022, 4, 12), false,
                Task.Priority.NONE);
        Task newDeadline = deadline.setPriority(Task.Priority.HIGH);
        assertTrue(newDeadline instanceof Deadline);
    }
}
