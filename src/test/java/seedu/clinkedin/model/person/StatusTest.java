package seedu.clinkedin.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
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

        // valid name
        assertTrue(Status.isValidStatus("application received")); // alphabets only
        assertTrue(Status.isValidStatus("12345")); // numbers only
        assertTrue(Status.isValidStatus("2nd round oa")); // alphanumeric characters
        assertTrue(Status.isValidStatus("Application Received")); // with capital letters
        assertTrue(Status.isValidStatus("Accepted but applicant wants to negotiate compensation package"));
        // long names
    }
}
