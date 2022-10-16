package taskbook.model.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskbook.model.task.enums.Assignment;
import taskbook.testutil.DeadlineBuilder;
import taskbook.testutil.TypicalTaskBook;

public class DeadlineTest {

    @Test
    public void isSameDeadline() {
        // same object -> returns true
        Assertions.assertTrue(TypicalTaskBook.EATING.isSameTask(TypicalTaskBook.EATING));

        // null -> returns false
        Assertions.assertFalse(TypicalTaskBook.EATING.isSameTask(null));

        // same person, same assignment, same description, same deadline, all other attributes different
        // -> returns true
        Deadline editedEating = new DeadlineBuilder().withPerson(TypicalTaskBook.ALICE)
                .withAssignment(Assignment.TO).withDescription("eat fruit").withIsDone(false)
                .withDeadlineDate(LocalDate.of(2022, 11, 9)).build();
        Assertions.assertTrue(TypicalTaskBook.EATING.isSameTask(editedEating));

        // different person, all other attributes same
        // -> returns false
        editedEating = new DeadlineBuilder().withPerson(TypicalTaskBook.BOB)
                .withAssignment(Assignment.TO).withDescription("eat fruit").withIsDone(false)
                .withDeadlineDate(LocalDate.of(2022, 11, 9)).build();
        Assertions.assertFalse(TypicalTaskBook.EATING.isSameTask(editedEating));
    }
}
