package seedu.classify.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        // null id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid id
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only
        assertFalse(Id.isValidId("91")); // less than 3 numbers
        assertFalse(Id.isValidId("phone")); // non-numeric
        assertFalse(Id.isValidId("9011p041")); // alphabets within digits
        assertFalse(Id.isValidId("9312 1534")); // spaces within digits

        // valid id
        assertTrue(Id.isValidId("911A")); // exactly 3 numbers and 1 character
        assertTrue(Id.isValidId("932B"));
        assertTrue(Id.isValidId("123C"));
    }
}
