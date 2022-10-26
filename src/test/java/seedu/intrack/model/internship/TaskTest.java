package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private String invalidTaskName = "";
    private String invalidTaskTime = "TODAY";

    @Test
    public void constructor_nullTaskName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null, "15-12-2022 10:00"));
    }

    @Test
    public void constructor_nullTaskTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task("Technical Interview", null));
    }

    @Test
    public void constructor_invalidTaskName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Task(invalidTaskName, "15-12-2022 10:00"));
    }

    @Test
    public void constructor_invalidTaskTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Task("HR Interview", invalidTaskTime));
    }

    @Test
    public void isValidTask() {
        // null task
        assertThrows(NullPointerException.class, () -> Task.isValidTask(null));

        // valid tasks
        assertTrue(Task.isValidTask(new Task("Online Assessment", "10-10-2023 10:00")));
        assertTrue(Task.isValidTask(new Task("-", "05-11-2022 11:00"))); // one character
        assertTrue(Task.isValidTask(new Task("The quick brown fox jumped over the lazy dog while testing"
                + "the code in his project that is very very long", "20-10-2022 15:00"))); // long taskName
    }
}
