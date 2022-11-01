package jarvis.model.task;

import static jarvis.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import jarvis.model.TaskDeadline;

public class TaskDeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsDateTimeException() {
        assertThrows(DateTimeException.class, () -> new TaskDeadline(LocalDate.of(2022, 2, 30)));
    }

    @Test
    public void equals() {
        LocalDate date1 = LocalDate.of(2022, 10, 12);
        LocalDate date2 = LocalDate.of(2022, 11, 12);

        TaskDeadline deadline1 = new TaskDeadline(date1);
        TaskDeadline deadline2 = new TaskDeadline(date2);

        //same values -> returns true
        TaskDeadline deadline1Copy = new TaskDeadline(date1);
        assertTrue(deadline1.equals(deadline1Copy));

        //same object -> returns true
        assertTrue(deadline1.equals(deadline1));

        // null -> returns false
        assertFalse(deadline1.equals(null));

        // different type -> returns false
        assertFalse(deadline1.equals(5));

        //different values -> returns false
        assertFalse(deadline1.equals(deadline2));
    }
}
