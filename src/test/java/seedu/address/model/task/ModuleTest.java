package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;

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

        // invalid modules
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only
        assertFalse(Module.isValidModule("A B")); // has spaces
        assertFalse(Module.isValidModule("-")); // one character
        assertFalse(Module.isValidModule("USGCSDSA13495798735729475238945273858gf_ewt3wi4rw34")); // long module

        // valid modules
        assertTrue(Module.isValidModule("CS2103T"));
        assertTrue(Module.isValidModule("CFG1003"));

    }

    @Test
    public void toString_roundtrip_works() {
        String sampleModuleCode = "CS2103T";
        assertEquals(new Module(sampleModuleCode).toString(), sampleModuleCode);
    }
}
