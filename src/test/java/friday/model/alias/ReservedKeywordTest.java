package friday.model.alias;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReservedKeywordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReservedKeyword(null));
    }

    @Test
    public void isValidReservedKeyword() {
        // null reserved keyword
        assertThrows(NullPointerException.class, () -> ReservedKeyword.isValidReservedKeyword(null));

        // invalid reserved keyword
        assertFalse(ReservedKeyword.isValidReservedKeyword("")); // empty string
        assertFalse(ReservedKeyword.isValidReservedKeyword("ad")); // non-empty invalid string

        // valid reserved keyword
        assertTrue(ReservedKeyword.isValidReservedKeyword("add")); // valid string
        assertTrue(ReservedKeyword.isValidReservedKeyword("delete")); // valid string
    }
}

