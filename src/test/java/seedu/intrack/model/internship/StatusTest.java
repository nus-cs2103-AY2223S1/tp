package seedu.intrack.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null name
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid name
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("^")); // only non-alphanumeric characters
        assertFalse(Status.isValidStatus("peter*")); // contains non-alphanumeric characters
        assertFalse(Status.isValidStatus("peter")); // contains alphanumeric word not a status keyword

        // valid name
        assertTrue(Status.isValidStatus("ProGresS")); // alphabets only
        assertTrue(Status.isValidStatus("Offered")); // numbers only
        assertTrue(Status.isValidStatus("rejected")); // alphanumeric characters
        assertTrue(Status.isValidStatus("PROGRESS")); // with capital letters
        assertTrue(Status.isValidStatus("oFFeRed")); // long names
    }
}
