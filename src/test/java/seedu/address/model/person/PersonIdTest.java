package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonIdTest {

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonId(invalidId));
    }

    @Test
    public void isValidDatetimeStr() {
        // invalid id
        assertFalse(PersonId.isValidId("")); // empty string
        assertFalse(PersonId.isValidId(" ")); // spaces only
        assertFalse(PersonId.isValidId("a")); // non-numeric string
        assertFalse(PersonId.isValidId("1.1")); // numeric non-integer string
        assertFalse(PersonId.isValidId("-2")); // numeric negative integer string
        assertFalse(PersonId.isValidId(-2)); // negative integer index

        // valid id
        assertTrue(PersonId.isValidId("0")); // Valid integer string
        assertTrue(PersonId.isValidId(1)); // Valid integer
        assertTrue(PersonId.isValidId(Integer.MAX_VALUE)); // Valid integer (MAX_INT)
    }
}
