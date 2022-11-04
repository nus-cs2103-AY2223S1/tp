package friday.model.alias;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import friday.logic.commands.AddCommand;
import friday.logic.commands.DeleteCommand;

public class AliasTest {

    private static final String VALID_ALIAS_1 = "ls";
    private static final String VALID_ALIAS_2 = "a";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Alias(null));
    }

    @Test
    public void isValidAlias() {
        // null alias
        assertThrows(NullPointerException.class, () -> Alias.isValidAlias(null));

        // invalid alias
        assertFalse(Alias.isValidAlias(AddCommand.COMMAND_WORD)); // alias in reserved keyword
        assertFalse(Alias.isValidAlias(DeleteCommand.COMMAND_WORD)); // alias in reserved keyword
        assertFalse(Alias.isValidAlias("")); // alias is an empty string
        assertFalse(Alias.isValidAlias("hello world")); // alias contains more than 1 word

        // valid name
        assertTrue(Alias.isValidAlias(VALID_ALIAS_1)); // valid alias
        assertTrue(Alias.isValidAlias(VALID_ALIAS_2)); // valid alias
    }
}

