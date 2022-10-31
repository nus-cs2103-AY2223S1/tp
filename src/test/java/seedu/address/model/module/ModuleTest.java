package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidModule = "_";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModule));
    }

    @Test
    public void isValidModule() {
        // null module
        assertThrows(NullPointerException.class, () -> Module.isValidModuleName(null));

        // blank module
        assertFalse(Module.isValidModuleName(" ")); // spaces only

        // invalid parts
        assertFalse(Module.isValidModuleName("CS_2040")); // underscore in module
        assertFalse(Module.isValidModuleName("CS.2040")); // period in module
        assertFalse(Module.isValidModuleName("CS+2040")); // pluses in module
        assertFalse(Module.isValidModuleName("CS 2040")); // spaces in module
        assertFalse(Module.isValidModuleName("CS-2040")); // hyphen in module
        assertFalse(Module.isValidModuleName("CS@2040")); // '@' symbol in module
        assertFalse(Module.isValidModuleName("peter--jack")); // consecutive hyphens
        assertFalse(Module.isValidModuleName("C2040")); // module prefix only 1 letter
        assertFalse(Module.isValidModuleName("ABCDE2040")); // module prefix more than 4 letters
        assertFalse(Module.isValidModuleName("2040")); // missing module prefix
        assertFalse(Module.isValidModuleName("CS204")); // module code less than 4 digits
        assertFalse(Module.isValidModuleName("CS20405")); // module code more than 4 digits
        assertFalse(Module.isValidModuleName("CS20A0")); // module code contains letters
        assertFalse(Module.isValidModuleName("CS2040SG")); // module suffix more than 1 letter

        // valid module
        assertTrue(Module.isValidModuleName("CS2040")); // 2 letter prefix module name
        assertTrue(Module.isValidModuleName("UTR1000")); // 3 letter prefix module name
        assertTrue(Module.isValidModuleName("GESS1025")); // 4 letter prefix module name
        assertTrue(Module.isValidModuleName("CS2040S")); // 2 letter prefix module name with suffix
        assertTrue(Module.isValidModuleName("UTR1000A")); // 3 letter prefix module name with suffix
        assertTrue(Module.isValidModuleName("GESS1025R")); // 4 letter prefix module name with suffix
        assertTrue(Module.isValidModuleName("cs2040s")); // completely un-capitalised module name
        assertTrue(Module.isValidModuleName("Cs2040s")); // first letter capitalised module name
        assertTrue(Module.isValidModuleName("CS2040s")); // prefix capitalised module name
        assertTrue(Module.isValidModuleName("cs2040S")); // suffix capitalised module name
        assertTrue(Module.isValidModuleName("dAo1047X")); // random letters capitalised module name
    }
}
