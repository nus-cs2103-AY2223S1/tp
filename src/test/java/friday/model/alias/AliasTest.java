package friday.model.alias;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import friday.logic.commands.AddCommand;
import friday.logic.commands.DeleteCommand;
import org.junit.jupiter.api.Test;

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
        assertFalse(Alias.isValidAlias(new Alias(AddCommand.COMMAND_WORD))); // alias in reserved keyword
        assertFalse(Alias.isValidAlias(new Alias(DeleteCommand.COMMAND_WORD))); // alias in reserved keyword

        // valid name
        assertTrue(Alias.isValidAlias(new Alias(VALID_ALIAS_1))); // valid alias
        assertTrue(Alias.isValidAlias(new Alias(VALID_ALIAS_2))); // valid alias
    }
}

