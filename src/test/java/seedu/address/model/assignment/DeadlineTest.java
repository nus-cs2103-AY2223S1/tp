package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private LocalDateTime time = LocalDateTime.now();
    private Deadline d = new Deadline();
    private Deadline d1 = new Deadline(time);

    @Test
    public void getDateTime_success() {
        assertTrue(d1.getDateTime().equals(time));
    }

    @Test
    public void getDateTime_failure() {
        assertFalse(d1.getDateTime().equals(time.plusDays(10)));
    }

}
