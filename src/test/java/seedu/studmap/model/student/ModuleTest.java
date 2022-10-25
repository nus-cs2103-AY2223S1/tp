package seedu.studmap.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studmap.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModule));
    }

    @Test
    public void isValidModule() {
        // null module
        assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // invalid module
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only
        assertFalse(Module.isValidModule("CS919")); // less than 3 numbers
        assertFalse(Module.isValidModule("cs2103")); // not capitalized
        assertFalse(Module.isValidModule("2121S")); // no prefix

        // valid module
        assertTrue(Module.isValidModule("CS2103"));
        assertTrue(Module.isValidModule("AAC2010T"));
    }
}
