package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppliedDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppliedDate(null));
    }

    @Test
    public void constructor_invalidAppliedDate_throwsIllegalArgumentException() {
        String invalidAppliedDate = "03 Oct 22";
        assertThrows(IllegalArgumentException.class, () -> new AppliedDate(invalidAppliedDate));
    }


    @Test
    public void isValidAppliedDate() {
        // null applied date
        assertThrows(NullPointerException.class, () -> AppliedDate.isValidAppliedDate(null));

        // invalid applied dates
        assertFalse(AppliedDate.isValidAppliedDate("23 October 2022")); // only MMM or M
        assertFalse(AppliedDate.isValidAppliedDate("23 Oct 22")); // only yyyy

        // valid applied dates
        assertTrue(AppliedDate.isValidAppliedDate("23 Oct 2022")); // d MMM yyyy
        assertTrue(AppliedDate.isValidAppliedDate("23 Oct")); // d MMM
        assertTrue(AppliedDate.isValidAppliedDate("23/10/2022")); // d/M/yyyy
        assertTrue(AppliedDate.isValidAppliedDate("23/10")); // d/M
    }
}
