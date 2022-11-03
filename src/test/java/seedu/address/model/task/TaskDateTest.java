package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskDateTest {
    private TaskDate date1 = new TaskDate(LocalDate.of(2023,03,03));
    private TaskDate date2 = new TaskDate(LocalDate.of(2023,03,03));
    private TaskDate date3 = new TaskDate(LocalDate.of(2023,04,04));
    @Test
    public void equals() {

        // same object -> returns true
        assertEquals(date1, date1);

        // null -> returns false
        assertNotEquals(null, date1);

        // different types -> returns false
        assertNotEquals(date1, LocalDate.of(2022,12,12));

        // same deadline -> returns true
        assertEquals(date1, date2);

        // different date -> returns false
        assertNotEquals(date1, date3);
    }

    @Test
    public void testAfter() {
        assertTrue(date3.isAfter(date1.getDate()));
        assertTrue(date3.isAfter(date2.getDate()));
        assertFalse(date1.isAfter(date2.getDate()));
    }

    @Test
    public void testBefore() {
        assertFalse(date3.isBefore(date1.getDate()));
        assertTrue(date1.isBefore(date3.getDate()));
        assertFalse(date3.isBefore(date2.getDate()));
    }

    @Test
    public void isValidTest() {
        // invalid deadline
        assertFalse(TaskDate.isValidTaskDate("")); // empty string
        assertFalse(TaskDate.isValidTaskDate(" ")); // spaces only
        assertFalse(TaskDate.isValidTaskDate("^")); // only non-alphanumeric characters
        assertFalse(TaskDate.isValidTaskDate("peter*")); // contains non-alphanumeric characters
        assertFalse(TaskDate.isValidTaskDate("homework")); // contains non valid task category
        assertFalse(TaskDate.isValidTaskDate("null")); // contains null

        // valid deadline
        assertTrue(TaskDate.isValidTaskDate("2022-12-12"));
        assertTrue(TaskDate.isValidTaskDate("2021-12-12"));
    }
}
