package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidName));
    }

    @Test
    public void isValidModule() {
        // null module
        assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // invalid module
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only
        assertFalse(Module.isValidModule("^")); // only non-alphanumeric characters
        assertFalse(Module.isValidModule("CS*100")); // contains non-alphanumeric characters
        assertFalse(Module.isValidModule("C1000")); // less than 2 letter
        assertFalse(Module.isValidModule("CSSSS1000")); // more than 4 letters
        assertFalse(Module.isValidModule("CS100")); // less than 4 numbers
        assertFalse(Module.isValidModule("CS10000")); // more than 4 numbers

        // valid module
        assertTrue(Module.isValidModule("CS2103")); // 2 letters, no letter behind
        assertTrue(Module.isValidModule("CS2103T")); // 2 letters, letter behind
        assertTrue(Module.isValidModule("CS2103AB")); // 2 letters, 2 letter behind
        assertTrue(Module.isValidModule("ACC1703")); // 3 letters, no letter behind
        assertTrue(Module.isValidModule("ACC1703X")); // 3 letters, letter behind
        assertTrue(Module.isValidModule("ACC1703AB")); // 2 letters, 2 letter behind
        assertTrue(Module.isValidModule("GESS1025")); // 4 letters, no letter behind
        assertTrue(Module.isValidModule("GESS1025A")); // 4 letters, letter behind
        assertTrue(Module.isValidModule("GESS1025AB")); // 4 letters, 2 letter behind

    }
}
