package friday.model.alias;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import friday.model.alias.exceptions.AliasNotFoundException;
import friday.model.alias.exceptions.DuplicateAliasException;

public class AliasMapTest {

    private final AliasMap aliasMap = new AliasMap();

    @Test
    public void contains_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.contains(null));
    }

    @Test
    public void contains_aliasNotInMap_returnsFalse() {
        assertFalse(aliasMap.contains(""));
    }

    @Test
    public void contains_aliasInMap_returnsTrue() {
        aliasMap.add(new Alias("ls"), new ReservedKeyword("list"));
        assertTrue(aliasMap.contains("ls"));
    }

    @Test
    public void add_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.add(null, new ReservedKeyword("list")));
        assertThrows(NullPointerException.class, () -> aliasMap.add(new Alias("ls"), null));
    }

    @Test
    public void add_duplicateAlias_throwsDuplicateAliasException() {
        aliasMap.add(new Alias("ls"), new ReservedKeyword("list"));
        assertThrows(DuplicateAliasException.class, () -> aliasMap.add(new Alias("ls"), new ReservedKeyword("list")));
    }

    @Test
    public void remove_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> aliasMap.remove(null));
    }

    @Test
    public void remove_aliasDoesNotExist_throwsAliasNotFoundException() {
        assertThrows(AliasNotFoundException.class, () -> aliasMap.remove(new Alias("a")));
    }

    @Test
    public void remove_existingAlias_removesAlias() {
        aliasMap.add(new Alias("ls"), new ReservedKeyword("list"));
        aliasMap.remove(new Alias("ls"));
        AliasMap expectedAliasMap = new AliasMap();
        assertEquals(expectedAliasMap, aliasMap);
    }
}

