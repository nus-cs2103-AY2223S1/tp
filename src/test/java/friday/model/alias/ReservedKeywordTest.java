package friday.model.alias;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import friday.logic.commands.AddCommand;
import friday.logic.commands.DeleteCommand;

public class ReservedKeywordTest {

    private static final String INVALID_RESERVED_KEYWORD_1 = "";
    private static final String INVALID_RESERVED_KEYWORD_2 = "ad";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReservedKeyword(null));
    }

    @Test
    public void isValidReservedKeyword() {
        // null reserved keyword
        assertThrows(NullPointerException.class, () -> ReservedKeyword.isValidReservedKeyword(null));

        // invalid reserved keyword
        assertFalse(ReservedKeyword.isValidReservedKeyword(INVALID_RESERVED_KEYWORD_1)); // empty string
        assertFalse(ReservedKeyword.isValidReservedKeyword(INVALID_RESERVED_KEYWORD_2)); // non-empty invalid string

        // valid reserved keyword
        assertTrue(ReservedKeyword.isValidReservedKeyword(AddCommand.COMMAND_WORD)); // valid string
        assertTrue(ReservedKeyword.isValidReservedKeyword(DeleteCommand.COMMAND_WORD)); // valid string
    }
}

