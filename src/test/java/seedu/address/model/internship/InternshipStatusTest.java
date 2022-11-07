package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InternshipStatusTest {

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> InternshipStatus.isValidStatus(null));

        // invalid status
        assertFalse(InternshipStatus.isValidStatus("")); // empty string
        assertFalse(InternshipStatus.isValidStatus(" ")); // spaces only
        assertFalse(InternshipStatus.isValidStatus("REJECTE")); // incomplete status
        assertFalse(InternshipStatus.isValidStatus("rejected")); // valid full form but lower-case
        assertFalse(InternshipStatus.isValidStatus("r")); // valid shortcut form but lower-case

        // valid status
        assertTrue(InternshipStatus.isValidStatus("REJECTED")); // valid full form for REJECTED
        assertTrue(InternshipStatus.isValidStatus("R")); // valid shortcut form for REJECTED
        assertTrue(InternshipStatus.isValidStatus("BOOKMARKED")); // valid full form for BOOKMARKED
        assertTrue(InternshipStatus.isValidStatus("B")); // valid shortcut form for BOOKMARKED
    }
}
