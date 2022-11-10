package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DatePastException;

public class TaskDeadlineTest {

    private TaskDeadline deadline1 = new TaskDeadline(LocalDate.of(2023, 03, 03));
    private TaskDeadline deadline2 = new TaskDeadline(LocalDate.of(2023, 03, 03));
    private TaskDeadline deadline3 = new TaskDeadline(LocalDate.of(2023, 04, 04));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_null_dateAlreadyPastException() {
        assertThrows(DatePastException.class, () -> new TaskDeadline(LocalDate.MIN));
    }

    @Test
    public void equals() {

        // same object -> returns true
        assertEquals(deadline1, deadline1);

        // null -> returns false
        assertNotEquals(null, deadline1);

        // different types -> returns false
        assertNotEquals(deadline1, LocalDate.of(2022, 12, 12));

        // same deadline -> returns true
        assertEquals(deadline1, deadline2);

        // different taskCategoryType -> returns false
        assertNotEquals(deadline1, deadline3);
    }

    @Test
    public void testAfter() {
        assertTrue(deadline3.isAfter(deadline1.getDeadline()));
        assertTrue(deadline3.isAfter(deadline2.getDeadline()));
        assertFalse(deadline1.isAfter(deadline2.getDeadline()));
    }

    @Test
    public void testBefore() {
        assertFalse(deadline3.isBefore(deadline1.getDeadline()));
        assertTrue(deadline1.isBefore(deadline3.getDeadline()));
        assertFalse(deadline1.isBefore(deadline2.getDeadline()));
    }

    @Test
    public void isValidTest() {
        // invalid deadline
        assertFalse(TaskDeadline.isValidTaskDeadline("")); // empty string
        assertFalse(TaskDeadline.isValidTaskDeadline(" ")); // spaces only
        assertFalse(TaskDeadline.isValidTaskDeadline("^")); // only non-alphanumeric characters
        assertFalse(TaskDeadline.isValidTaskDeadline("peter*")); // contains non-alphanumeric characters
        assertFalse(TaskDeadline.isValidTaskDeadline("homework")); // contains non valid task category
        assertFalse(TaskDeadline.isValidTaskDeadline("null")); // contains null

        // valid deadline
        assertTrue(TaskDeadline.isValidTaskDeadline("2022-12-12"));
        assertTrue(TaskDeadline.isValidTaskDeadline("2021-12-12"));
    }
}
