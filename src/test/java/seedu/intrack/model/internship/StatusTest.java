package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void equals() {
        Status status = new Status("Progress");

        // same object -> returns true
        assertTrue(status.equals(status));

        // same values -> returns true
        Status statusCopy = new Status(status.value);
        assertTrue(status.equals(statusCopy));

        // different types -> returns false
        assertFalse(status.equals(1));

        // null -> returns false
        assertFalse(status.equals(null));

        // different status -> returns false
        Status differentStatus = new Status("Offered");
        assertFalse(status.equals(differentStatus));
    }
}
