package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidCode = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidCode));
    }

    @Test
    public void isValidCode() {
        // null module code
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidCode(null));

        // invalid module codes
        assertFalse(ModuleCode.isValidCode("")); // empty string
        assertFalse(ModuleCode.isValidCode(" ")); // spaces only

        // valid module codes
        assertTrue(ModuleCode.isValidCode("CS2106"));
        assertTrue(ModuleCode.isValidCode("CS2103T"));
        assertTrue(ModuleCode.isValidCode("2100"));
        assertTrue(ModuleCode.isValidCode("CS2103R"));
        assertTrue(ModuleCode.isValidCode("C")); // one character
        assertTrue(ModuleCode.isValidCode("CS2106ABCDEFGHIJKLMNOPQRSTUVWXYZ")); // long address
    }
}
