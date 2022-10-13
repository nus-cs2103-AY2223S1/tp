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
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidModule));
    }

    @Test
    public void isValidModuleCode() {
        // null module code
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid module code
        assertFalse(ModuleCode.isValidModuleCode("")); // empty string
        assertFalse(ModuleCode.isValidModuleCode(" ")); // spaces only
        assertFalse(ModuleCode.isValidModuleCode("^")); // only non-alphanumeric characters
        assertFalse(ModuleCode.isValidModuleCode("peter*")); // contains non-alphanumeric characters

        // valid module code
        assertTrue(ModuleCode.isValidModuleCode("CS2100"));
        assertTrue(ModuleCode.isValidModuleCode("CS1101S"));
        assertTrue(ModuleCode.isValidModuleCode("MA1521"));
        assertTrue(ModuleCode.isValidModuleCode("GEA1000"));
        assertTrue(ModuleCode.isValidModuleCode("CS101"));
    }
}
