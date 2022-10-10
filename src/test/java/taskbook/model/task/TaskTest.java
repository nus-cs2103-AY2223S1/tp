package taskbook.model.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskbook.model.task.enums.Assignment;
import taskbook.testutil.TaskBuilder;
import taskbook.testutil.TypicalPersons;
import taskbook.testutil.TypicalTasks;

public class TaskTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        Assertions.assertTrue(TypicalTasks.EATING.isSameTask(TypicalTasks.EATING));

        // null -> returns false
        Assertions.assertFalse(TypicalTasks.EATING.isSameTask(null));

        // same person, same assignment, same description, all other attributes different
        // -> returns true
        Task editedEating = new TaskBuilder().withPerson(TypicalPersons.ALICE)
                .withAssignment(Assignment.TO).withDescription("eat fruit").withIsDone(false)
                .build();
        Assertions.assertTrue(TypicalTasks.EATING.isSameTask(editedEating));

        // different person, all other attributes same
        // -> returns false
        editedEating = new TaskBuilder().withPerson(TypicalPersons.BOB)
                .withAssignment(Assignment.TO).withDescription("eat fruit").withIsDone(false)
                .build();
        Assertions.assertFalse(TypicalTasks.EATING.isSameTask(editedEating));
    }
}
