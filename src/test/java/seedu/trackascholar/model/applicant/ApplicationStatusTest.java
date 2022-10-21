package seedu.trackascholar.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ApplicationStatusTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ApplicationStatus(null));
    }

    @Test
    public void constructor_invalidApplicationStatus_throwsIllegalArgumentException() {
        String invalidApplicationStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new ApplicationStatus(invalidApplicationStatus));
    }

    @Test
    public void isValidApplicationStatus() {
        // null phone number
        assertThrows(NullPointerException.class, () -> ApplicationStatus.isValidApplicationStatus(null));

        // invalid Application Status
        assertFalse(ApplicationStatus.isValidApplicationStatus("")); // empty string
        assertFalse(ApplicationStatus.isValidApplicationStatus(" ")); // spaces only
        assertFalse(ApplicationStatus.isValidApplicationStatus("9011p041")); // alphabets within digits
        assertFalse(ApplicationStatus.isValidApplicationStatus("#$%^&*(*cmiewa;fd")); // gibberish

        // valid Application Status
        assertTrue(ApplicationStatus.isValidApplicationStatus("pending"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("accepted"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("rejected"));
    }
}
