package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ApplicationStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ApplicationStatus.parse(null));
    }

    @Test
    public void constructor_invalidApplicationStatus_throwsParseException() {
        String invalidApplicationStatus = "";
        assertThrows(ParseException.class, () -> ApplicationStatus.parse(invalidApplicationStatus));
    }

    @Test
    public void isValidApplicationStatus() {
        // null address
        assertThrows(NullPointerException.class, () -> ApplicationStatus.isValidApplicationStatus(null));

        // invalid addresses
        assertFalse(ApplicationStatus.isValidApplicationStatus("")); // empty string
        assertFalse(ApplicationStatus.isValidApplicationStatus(" ")); // spaces only
        assertFalse(ApplicationStatus.isValidApplicationStatus("passed")); // invalid status

        // valid addresses
        assertTrue(ApplicationStatus.isValidApplicationStatus("applied"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("accepted"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("shortlisted"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("interviewed"));
        assertTrue(ApplicationStatus.isValidApplicationStatus("rejected"));
    }
}
