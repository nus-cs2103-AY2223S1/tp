package seedu.application.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void isValidStatus() {
        // null Status
        assertFalse(Status.isValidStatus(null));

        // invalid Status
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("applied")); // not one of the allowed values

        // valid Status
        assertTrue(Status.isValidStatus("interview"));
        assertTrue(Status.isValidStatus("pending"));
        assertTrue(Status.isValidStatus("offered"));
        assertTrue(Status.isValidStatus("rejected"));
    }

    @Test
    public void getStatus() {
        // invalid status
        assertThrows(IllegalArgumentException.class, () -> Status.getStatus("applied"));

        assertEquals(Status.INTERVIEW, Status.getStatus("interview"));
        assertEquals(Status.PENDING, Status.getStatus("pending"));
        assertEquals(Status.OFFERED, Status.getStatus("offered"));
        assertEquals(Status.REJECTED, Status.getStatus("rejected"));
    }
}
