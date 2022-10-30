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
}
