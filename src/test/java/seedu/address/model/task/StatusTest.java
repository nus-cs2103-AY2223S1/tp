package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null address
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid addresses
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("a")); // invalid string

        // valid addresses
        assertTrue(Status.isValidStatus("Complete"));
        assertTrue(Status.isValidStatus("Not complete"));
    }

    @Test
    public void getIsComplete() {
        assertFalse(new Status("Not complete").getIsComplete());
        assertTrue(new Status("Complete").getIsComplete());
        assertFalse(new Status(false).getIsComplete());
        assertTrue(new Status(true).getIsComplete());
    }
}
