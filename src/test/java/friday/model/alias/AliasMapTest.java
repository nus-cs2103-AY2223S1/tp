package friday.model.alias;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import friday.logic.commands.ListCommand;
import friday.model.alias.exceptions.AliasNotFoundException;
import friday.model.alias.exceptions.DuplicateAliasException;

public class AliasMapTest {

    private static final Alias VALID_ALIAS = new Alias("ls");
    private static final ReservedKeyword VALID_KEYWORD = new ReservedKeyword(ListCommand.COMMAND_WORD);

    private final AliasMap aliasMap = new AliasMap();

    @Test
    public void contains_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.contains(null));
    }

    @Test
    public void contains_aliasNotInMap_returnsFalse() {
        assertFalse(aliasMap.contains(VALID_ALIAS));
    }

    @Test
    public void contains_aliasInMap_returnsTrue() {
        aliasMap.add(VALID_ALIAS, VALID_KEYWORD);
        assertTrue(aliasMap.contains(VALID_ALIAS));
    }

    @Test
    public void add_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.add(null, VALID_KEYWORD));
        assertThrows(NullPointerException.class, () -> aliasMap.add(VALID_ALIAS, null));
    }

    @Test
    public void add_duplicateAlias_throwsDuplicateAliasException() {
        aliasMap.add(VALID_ALIAS, VALID_KEYWORD);
        assertThrows(DuplicateAliasException.class, () -> aliasMap.add(VALID_ALIAS, VALID_KEYWORD));
    }

    @Test
    public void remove_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.remove(null));
    }

    @Test
    public void remove_aliasDoesNotExist_throwsAliasNotFoundException() {
        assertThrows(AliasNotFoundException.class, () -> aliasMap.remove(VALID_ALIAS));
    }

    @Test
    public void remove_existingAlias_removesAlias() {
        aliasMap.add(VALID_ALIAS, VALID_KEYWORD);
        aliasMap.remove(VALID_ALIAS);
        AliasMap expectedAliasMap = new AliasMap();
        assertEquals(expectedAliasMap, aliasMap);
    }
}

