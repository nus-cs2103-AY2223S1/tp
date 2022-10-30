package jarvis.model.task;

import static jarvis.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE;
import static jarvis.logic.commands.CommandTestUtil.VALID_TASK_DESC_MISSION1;
import static jarvis.testutil.TypicalTasks.MISSION1;
import static jarvis.testutil.TypicalTasks.STUDIO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
