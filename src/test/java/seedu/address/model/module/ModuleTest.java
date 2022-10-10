package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA_MODULE_TITLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2106;
import static seedu.address.testutil.TypicalModules.MA2001;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> module.getLinks().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> module.getTasks().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS2106.isSameModule(CS2106));

        // completely different module -> returns false;
        assertFalse(CS2106.isSameModule(MA2001));

        // null -> returns false
        assertFalse(CS2106.isSameModule(null));

        // same module code, module title different -> returns true
        Module editedCs2106 =
                new ModuleBuilder(CS2106).withModuleTitle(VALID_MA_MODULE_TITLE).build();
        assertTrue(CS2106.isSameModule(editedCs2106));

        // module code differs in case, all other attributes same -> returns false
        Module editedMa2001 =
                new ModuleBuilder(MA2001).withModuleCode(VALID_CS_MODULE_CODE.toLowerCase()).build();
        assertFalse(CS2106.isSameModule(editedMa2001));

        // module code has trailing spaces, all other attributes same -> returns false
        String moduleWithTrailingSpaces = VALID_CS_MODULE_CODE + " ";
        editedCs2106 = new ModuleBuilder(CS2106).withModuleCode(moduleWithTrailingSpaces).build();
        assertFalse(CS2106.isSameModule(editedCs2106));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module cs2106Copy = new ModuleBuilder(CS2106).build();
        assertTrue(CS2106.equals(cs2106Copy));

        // same object -> returns true
        assertTrue(CS2106.equals(CS2106));

        // null -> returns false
        assertFalse(CS2106.equals(null));

        // different type -> returns false
        assertFalse(CS2106.equals(5));

        // different module -> returns false
        assertFalse(CS2106.equals(MA2001));

        // different module code -> returns false
        Module editedCs2106 =
                new ModuleBuilder(CS2106).withModuleCode(VALID_MA_MODULE_CODE).build();
        assertFalse(CS2106.equals(editedCs2106));

        // different module title -> returns false
        editedCs2106 = new ModuleBuilder(CS2106).withModuleTitle(VALID_MA_MODULE_TITLE).build();
        assertFalse(CS2106.equals(editedCs2106));
    }
}
