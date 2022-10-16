package seedu.address.model.task;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DatePastException;

public class TaskDeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_null_dateAlreadyPastException() {
        assertThrows(DatePastException.class, () -> new TaskDeadline(LocalDate.MIN));
    }
}
