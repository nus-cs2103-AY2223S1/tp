package jarvis.model.task;

import static jarvis.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE;
import static jarvis.logic.commands.CommandTestUtil.VALID_TASK_DESC_MISSION1;
import static jarvis.testutil.TypicalTasks.MISSION1;
import static jarvis.testutil.TypicalTasks.STUDIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import jarvis.model.Task;
import jarvis.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void equals() {
        // same values -> returns true
        Task mission1Copy = new TaskBuilder(MISSION1).build();
        assertTrue(MISSION1.equals(mission1Copy));

        // same object -> returns true
        assertTrue(MISSION1.equals(MISSION1));

        // null -> returns false
        assertFalse(MISSION1.equals(null));

        // different type -> returns false
        assertFalse(MISSION1.equals(5));

        // different desc and deadline -> returns false
        assertFalse(MISSION1.equals(STUDIO));

        // same desc but different deadline -> returns false
        Task editedMission = new TaskBuilder(MISSION1).withDeadline(VALID_TASK_DEADLINE).build();
        assertFalse(MISSION1.equals(editedMission));

        // different desc but same deadline -> returns false
        editedMission = new TaskBuilder(STUDIO).withDesc(VALID_TASK_DESC_MISSION1).build();
        assertFalse(MISSION1.equals(editedMission));
    }

    @Test
    public void markAsDone_isTaskDone_returnsTrue() {
        Task newTask = new TaskBuilder().build();
        newTask.markAsDone();
        assertTrue(newTask.isDone());

        newTask.markAsDone(); // task that is already done remains done
        assertTrue(newTask.isDone());
    }

    @Test
    public void markAsNotDone_isTaskDone_returnsFalse() {
        Task newTask = new TaskBuilder().build();
        newTask.markAsNotDone();
        assertFalse(newTask.isDone()); //task that is not done remains not done

        newTask.markAsDone();
        assertTrue(newTask.isDone());
        newTask.markAsNotDone();
        assertFalse(newTask.isDone()); // task that is done will become not done
    }

    @Test
    public void getDeadline() {
        //no deadline
        Task taskWithoutDeadline = new TaskBuilder().withoutDeadline().build();
        assertEquals(null, taskWithoutDeadline.getDeadline());

        //correct deadline
        Task taskWithDeadline = new TaskBuilder().withDeadline(VALID_TASK_DEADLINE).build();
        assertEquals(VALID_TASK_DEADLINE, taskWithDeadline.getDeadline());
    }

    @Test
    public void getDeadlineString() {
        //no deadline
        Task taskWithoutDeadline = new TaskBuilder().withoutDeadline().build();
        assertEquals("", taskWithoutDeadline.getDeadlineString());

        //has deadline
        Task taskWithDeadline = new TaskBuilder().withDeadline(VALID_TASK_DEADLINE).build();
        String expectedDeadline = VALID_TASK_DEADLINE.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        assertEquals(expectedDeadline, taskWithDeadline.getDeadlineString());
    }
}
