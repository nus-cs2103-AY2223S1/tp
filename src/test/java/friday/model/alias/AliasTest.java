package friday.model.alias;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AliasTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Alias(null));
    }

    @Test
    public void isValidAlias() {
        // null alias
        assertThrows(NullPointerException.class, () -> Alias.isValidAlias(null));

        // invalid alias
        assertFalse(Alias.isValidAlias(new Alias("add"))); // alias in reserved keyword
        assertFalse(Alias.isValidAlias(new Alias("delete"))); // alias in reserved keyword

        // valid name
        assertTrue(Alias.isValidAlias(new Alias("ls"))); // valid alias
        assertTrue(Alias.isValidAlias(new Alias("a"))); // valid alias
    }
}

