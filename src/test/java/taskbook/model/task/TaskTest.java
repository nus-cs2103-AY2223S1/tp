package taskbook.model.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskbook.model.task.enums.Assignment;
import taskbook.testutil.TaskBuilder;
import taskbook.testutil.TypicalTaskBook;

public class TaskTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        Assertions.assertTrue(TypicalTaskBook.EATING.isSameTask(TypicalTaskBook.EATING));

        // null -> returns false
        Assertions.assertFalse(TypicalTaskBook.EATING.isSameTask(null));

        // same person, same assignment, same description, all other attributes different
        // -> returns true
        Task editedEating = new TaskBuilder().withPerson(TypicalTaskBook.ALICE)
                .withAssignment(Assignment.TO).withDescription("eat fruit").withIsDone(false)
                .build();
        Assertions.assertTrue(TypicalTaskBook.EATING.isSameTask(editedEating));

        // different person, all other attributes same
        // -> returns false
        editedEating = new TaskBuilder().withPerson(TypicalTaskBook.BOB)
                .withAssignment(Assignment.TO).withDescription("eat fruit").withIsDone(false)
                .build();
        Assertions.assertFalse(TypicalTaskBook.EATING.isSameTask(editedEating));
    }
}
