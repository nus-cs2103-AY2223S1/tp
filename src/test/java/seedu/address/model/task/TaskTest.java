package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalToDos.FIRST;
import static seedu.address.testutil.TypicalToDos.SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ToDoBuilder;

public class TaskTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(FIRST.equals(FIRST));

        // null -> returns false
        assertFalse(FIRST.equals(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task firstCopy = new ToDoBuilder(FIRST).build();
        assertTrue(FIRST.equals(firstCopy));

        // same object -> returns true
        assertTrue(FIRST.equals(FIRST));

        // null -> returns false
        assertFalse(FIRST.equals(null));

        // different type -> returns false
        assertFalse(FIRST.equals(5));

        // different task -> returns false
        assertFalse(FIRST.equals(SECOND));
    }

}
