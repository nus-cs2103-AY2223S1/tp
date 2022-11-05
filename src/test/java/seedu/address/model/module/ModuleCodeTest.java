package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode("a"));
    }

    @Test
    public void testIsValidModuleCode() {

        //Valid Module Code
        assertTrue(ModuleCode.isValidModuleCode("CS2103T"));

        //Invalid length for module code(Less than 6)
        assertFalse(ModuleCode.isValidModuleCode("abc"));

        //Invalid characters for module code
        assertFalse(ModuleCode.isValidModuleCode("CS2103/T"));

        //First two characters are not alphabetical
        assertFalse(ModuleCode.isValidModuleCode("C2103L"));
    }

    @Test
    public void testEquals() {
        ModuleCode moduleCodeCS2100 = new ModuleCode("CS2100");
        ModuleCode moduleCodeCS2103 = new ModuleCode("CS2103");
        ModuleCode moduleCodeCS2100Copy = new ModuleCode("CS2100");

        //Same ModuleCode object
        assertTrue(moduleCodeCS2100.equals(moduleCodeCS2100));

        //Different ModuleCode object but same module code
        assertTrue(moduleCodeCS2100.equals(moduleCodeCS2100Copy));

        //Different ModuleCode object but different module code
        assertFalse(moduleCodeCS2100.equals(moduleCodeCS2103));

        //Different object type
        assertFalse(moduleCodeCS2100.equals(921));

        //Comparing with null
        assertFalse(moduleCodeCS2100.equals(null));
    }

    @Test
    public void testToString() {
        ModuleCode moduleCodeCS2100 = new ModuleCode("CS2100");
        assertEquals("CS2100", moduleCodeCS2100.toString());
    }
}

