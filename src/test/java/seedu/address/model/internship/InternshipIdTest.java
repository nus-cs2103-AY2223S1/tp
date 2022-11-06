package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InternshipIdTest {

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new InternshipId(invalidId));
    }

    @Test
    public void isValidDatetimeStr() {
        // invalid id
        assertFalse(InternshipId.isValidId("")); // empty string
        assertFalse(InternshipId.isValidId(" ")); // spaces only
        assertFalse(InternshipId.isValidId("a")); // non-numeric string
        assertFalse(InternshipId.isValidId("1.1")); // numeric non-integer string
        assertFalse(InternshipId.isValidId("-2")); // numeric negative integer string
        assertFalse(InternshipId.isValidId(-2)); // negative integer index

        // valid id
        assertTrue(InternshipId.isValidId("0")); // Valid integer string
        assertTrue(InternshipId.isValidId(1)); // Valid integer
        assertTrue(InternshipId.isValidId(Integer.MAX_VALUE)); // Valid integer (MAX_INT)
    }
}
