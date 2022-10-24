package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new Module((invalidModule)));
    }

    @Test
    void isValidModule() {
        // null module
        assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // invalid module
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only
        assertFalse(Module.isValidModule("^")); // only non-alphanumeric characters
        assertFalse(Module.isValidModule("peter*")); // contains non-alphanumeric characters
        assertFalse(Module.isValidModule("CS 123")); // contains spaces

        // valid module
        assertTrue(Module.isValidModule("CSASDQWE")); // alphabets only
        assertTrue(Module.isValidModule("12345")); // numbers only
        assertTrue(Module.isValidModule("CS2103T")); // alphanumeric characters
        assertTrue(Module.isValidModule("cs1231S")); // with capital letters
        assertTrue(Module.isValidModule("verylongmodulenamewithnumbers123haha")); // long names
    }

    @Test
    public void compareTo() {
        Module alice = new Module("CS1231S");
        Module ben = new Module("CS2040");
        Module charles = new Module("MA2001");
        Module charles2 = new Module("MA2001");

        // null comparison
        assertThrows(NullPointerException.class, () -> alice.compareTo(null));

        // Lexicographically smaller
        assertTrue(alice.compareTo(ben) < 0);
        assertTrue(alice.compareTo(charles) < 0);
        assertTrue(ben.compareTo(charles) < 0);

        // Lexicographically bigger
        assertTrue(charles.compareTo(alice) > 0);
        assertTrue(charles.compareTo(ben) > 0);
        assertTrue(ben.compareTo(alice) > 0);

        // Equal
        assertEquals(charles, charles2);
    }
}
