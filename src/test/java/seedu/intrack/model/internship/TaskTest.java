package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidTask_throwsIllegalArgumentException() {
        String invalidTaskName = "";
        String invalidTaskTime = "TODAY";
        assertThrows(IllegalArgumentException.class, () -> new Task(invalidTaskName, invalidTaskTime));
    }

    @Test
    public void isValidTask() {
        // null address
        assertThrows(NullPointerException.class, () -> Task.isValidTask(null));

        // invalid addresses
        assertFalse(Task.isValidTask(new Task("", ""))); // empty string
        assertFalse(Task.isValidTask(new Task(" ", " "))); // spaces only

        // valid addresses
        assertTrue(Task.isValidTask(new Task("Online Assessment", "10-10-2023 10:00")));
        assertTrue(Task.isValidTask(new Task("-", "05-11-2022 11:00"))); // one character
        assertTrue(Task.isValidTask(new Task("The quick brown fox jumped over the lazy dog while testing"
                + "the code in his project that is very very long", "20-10-2022 15:00"))); // long taskName
    }
}
