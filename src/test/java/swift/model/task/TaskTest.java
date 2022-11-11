package swift.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.testutil.TypicalTasks.BUY_MILK;
import static swift.testutil.TypicalTasks.CS2103T;

import org.junit.jupiter.api.Test;

import swift.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void equals() {
        // same values -> returns true
        Task buyMilkCopy = new TaskBuilder(BUY_MILK).build();
        assertTrue(BUY_MILK.equals(buyMilkCopy));

        // same object -> returns true
        assertTrue(BUY_MILK.equals(BUY_MILK));

        // null -> returns false
        assertFalse(BUY_MILK.equals(null));

        // different type -> returns false
        assertFalse(BUY_MILK.equals(5));

        // different person -> returns false
        assertFalse(BUY_MILK.equals(CS2103T));
    }
}
