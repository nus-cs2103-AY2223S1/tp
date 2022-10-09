package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_validDate_success() {
        assertDoesNotThrow(() -> new Deadline(LocalDate.parse("2022-05-05")));
    }

    @Test
    public void equals() {
        // Deadline should be equal even with different instance of LocalDate.
        assertEquals(new Deadline(LocalDate.parse("2022-05-05")),
                new Deadline(LocalDate.parse("2022-05-05")));
        assertNotEquals(new Deadline(LocalDate.parse("2022-05-05")),
                new Deadline(LocalDate.parse("2022-06-06")));
    }
}
