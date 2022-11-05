package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;


public class PriorityTest {

    private Optional<PriorityEnum> priorityEnum1 = PriorityEnum.getFromString("low");
    private Optional<PriorityEnum> priorityEnum2 = PriorityEnum.getFromString("medium");
    private Optional<PriorityEnum> priorityEnum3 = PriorityEnum.getFromString("high");
    private Priority priority1 = new Priority(priorityEnum1.get());
    private Priority priority2 = new Priority(priorityEnum2.get());
    private Priority priority3 = new Priority(priorityEnum3.get());
    private Priority priority4 = new Priority(priorityEnum1.get());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertEquals(priority1, priority1);

        // null -> returns false
        assertNotEquals(null, priority1);

        // different types -> returns false
        assertNotEquals(priority1, priorityEnum1);

        // same priorityEnum -> returns true
        assertEquals(priority1, priority4);

        // different priorityEnum -> returns false
        assertNotEquals(priority1, priority2);
        assertNotEquals(priority1, priority3);
    }

    @Test
    public void isValidTest() {
        // invalid priority
        assertFalse(Priority.isValidTaskPriority("")); // empty string
        assertFalse(Priority.isValidTaskPriority(" ")); // spaces only
        assertFalse(Priority.isValidTaskPriority("^")); // only non-alphanumeric characters
        assertFalse(Priority.isValidTaskPriority("peter*")); // contains non-alphanumeric characters
        assertFalse(Priority.isValidTaskPriority("homework")); // contains non valid task category
        assertFalse(Priority.isValidTaskPriority("null")); // contains null

        // valid priority
        assertTrue(Priority.isValidTaskPriority("low"));
        assertTrue(Priority.isValidTaskPriority("medium"));
        assertTrue(Priority.isValidTaskPriority("high"));

    }
}
