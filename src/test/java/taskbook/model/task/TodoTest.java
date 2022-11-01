package taskbook.model.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taskbook.model.task.enums.Assignment;
import taskbook.testutil.TodoBuilder;
import taskbook.testutil.TypicalTaskBook;

public class TodoTest {

    @Test
    public void isSameTodo() {
        // same object -> returns true
        Assertions.assertTrue(TypicalTaskBook.SLEEPING.isSameTask(TypicalTaskBook.SLEEPING));

        // null -> returns false
        Assertions.assertFalse(TypicalTaskBook.SLEEPING.isSameTask(null));

        // same person, same assignment, same description, same deadline, all other attributes different
        // -> returns true
        Todo editedEating = new TodoBuilder().withPersonName(TypicalTaskBook.BENSON)
                .withAssignment(Assignment.FROM).withDescription("sleep early").withIsDone(false)
                .build();
        Assertions.assertTrue(TypicalTaskBook.SLEEPING.isSameTask(editedEating));

        // different person, all other attributes same
        // -> returns false
        editedEating = new TodoBuilder().withPersonName(TypicalTaskBook.BOB)
                .withAssignment(Assignment.FROM).withDescription("sleep early").withIsDone(false)
                .build();
        Assertions.assertFalse(TypicalTaskBook.SLEEPING.isSameTask(editedEating));
    }
}
