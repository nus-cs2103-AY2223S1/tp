package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isWithinLengthLimit() {
        assertTrue(Id.isWithinLengthLimit("A".repeat(Id.LENGTH_LIMIT - 1)));
        assertTrue(Id.isWithinLengthLimit("A".repeat(Id.LENGTH_LIMIT)));
        assertFalse(Id.isWithinLengthLimit("A".repeat(Id.LENGTH_LIMIT + 1)));
    }

    @Test
    public void isValidId() {
        // null id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // blank id
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only

        // invalid id
        assertFalse(Id.isValidId(" J021392")); // leading space
        assertFalse(Id.isValidId("J021392 ")); // trailing space
        assertFalse(Id.isValidId("J 021392")); // space within alphanumeric
        assertFalse(Id.isValidId("J_021392")); // underscore within alphanumeric
        assertFalse(Id.isValidId("J-021392")); // hyphen within alphanumeric
        assertFalse(Id.isValidId("(J021392)")); // brackets within alphanumeric
        assertFalse(Id.isValidId("j021392")); // 1 lower case letter and numbers
        assertFalse(Id.isValidId("JeWwXkeM")); // multiple lower case and upper case letters
        assertFalse(Id.isValidId("eyhnmkiuygbn")); // all lower case letters

        // valid id
        assertTrue(Id.isValidId("021392")); // numbers only
        assertTrue(Id.isValidId("J021392")); // 1 upper case letter with numbers
        assertTrue(Id.isValidId("J021392K")); // multiple upper case letters with numbers
        assertTrue(Id.isValidId("JACBEIKK")); // multiple upper case letters
    }
}
