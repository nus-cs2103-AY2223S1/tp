package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalDateTimes;

public class AppliedDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppliedDate(null));
    }

    @Test
    public void constructor_invalidAppliedDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AppliedDate(TypicalDateTimes.SECOND_INVALID_DATE));
    }


    @Test
    public void isValidAppliedDate() {
        // null applied date
        assertThrows(NullPointerException.class, () -> AppliedDate.isValidAppliedDate(null));

        // invalid applied dates
        assertFalse(AppliedDate.isValidAppliedDate(TypicalDateTimes.FIRST_INVALID_DATE)); // only MMM or M
        assertFalse(AppliedDate.isValidAppliedDate(TypicalDateTimes.SECOND_INVALID_DATE)); // only uuuu
        assertFalse(AppliedDate.isValidAppliedDate(TypicalDateTimes.THIRD_INVALID_DATE)); // 29 Feb on non-leap years
        assertFalse(AppliedDate.isValidAppliedDate(TypicalDateTimes.FIRST_NONSTANDARD_DATE)); // does not exist
        assertFalse(AppliedDate.isValidAppliedDate(TypicalDateTimes.SECOND_NONSTANDARD_DATE)); // does not exist

        // empty applied date
        assertFalse(AppliedDate.isValidAppliedDate(" "));

        // valid applied dates
        assertTrue(AppliedDate.isValidAppliedDate(TypicalDateTimes.FIRST_VALID_DATE)); // d MMM uuuu
        assertTrue(AppliedDate.isValidAppliedDate(TypicalDateTimes.SECOND_VALID_DATE)); // d MMM
        assertTrue(AppliedDate.isValidAppliedDate(TypicalDateTimes.THIRD_VALID_DATE)); // d/M/uuuu
        assertTrue(AppliedDate.isValidAppliedDate(TypicalDateTimes.FOURTH_VALID_DATE)); // d/M
        assertTrue(AppliedDate.isValidAppliedDate(TypicalDateTimes.FIFTH_VALID_DATE)); // 29 Feb on leap year
    }
}
