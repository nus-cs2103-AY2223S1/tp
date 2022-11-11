package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.model.module.ModuleCode.isValidModuleCode;

import org.junit.jupiter.api.Test;

public class ModuleCodeTest {
    private static final String MODULE_CODE_MIX_CASE = "mA2001";
    private static final String MODULE_CODE_WITH_SPACES = "MA2 001";
    private static final String EMPTY_MODULE_CODE = "";

    @Test
    public void isValidModuleCode_validModuleCode_returnsTrue() {
        assertTrue(isValidModuleCode(VALID_MA2001_MODULE_CODE));
        assertTrue(isValidModuleCode(VALID_CS2106_MODULE_CODE));
        assertTrue(isValidModuleCode(VALID_MA2001_MODULE_CODE.toLowerCase()));
        assertTrue(isValidModuleCode(VALID_CS2106_MODULE_CODE.toLowerCase()));
        assertTrue(isValidModuleCode(MODULE_CODE_MIX_CASE));
    }

    @Test
    public void isValidModuleCode_inValidModuleCode_returnsFalse() {
        assertFalse(isValidModuleCode(MODULE_CODE_WITH_SPACES));
        assertFalse(isValidModuleCode(INVALID_MODULE_CODE));
        assertFalse(isValidModuleCode(EMPTY_MODULE_CODE));
        assertFalse(isValidModuleCode(null));
    }

    @Test
    public void compareTo() {
        ModuleCode cs2106 = new ModuleCode(VALID_CS2106_MODULE_CODE);
        ModuleCode ma2001 = new ModuleCode(VALID_MA2001_MODULE_CODE);

        assertTrue(cs2106.compareTo(ma2001) < 0); // lexicographically smaller module code against larger

        assertTrue(cs2106.compareTo(cs2106) == 0); // Same module

        assertTrue(ma2001.compareTo(cs2106) > 0); // lexicographically larger module code against smaller
    }
}
