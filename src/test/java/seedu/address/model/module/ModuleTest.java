package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CS2106_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MA2001_MODULE_TITLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;
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
    public void getModuleTitleAsUpperCaseString_validModuleTitle_success() {
        String expectedResult = VALID_MA2001_MODULE_CODE.toUpperCase();
        assertEquals(expectedResult, MA2001.getModuleCodeAsUpperCaseString());
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
                new ModuleBuilder(CS2106).withModuleTitle(VALID_MA2001_MODULE_TITLE).build();
        assertTrue(CS2106.isSameModule(editedCs2106));

        // module code differs in case, all other attributes different -> returns true because
        // module code is not case-sensitive
        Module editedMa2001 =
                new ModuleBuilder(MA2001).withModuleCode(VALID_CS2106_MODULE_CODE.toLowerCase()).build();
        assertTrue(CS2106.isSameModule(editedMa2001));

        // module code differs in case, all other attributes same -> returns true because
        // module code is not case-sensitive
        editedCs2106 =
                new ModuleBuilder(CS2106).withModuleCode(VALID_CS2106_MODULE_CODE.toLowerCase()).build();
        assertTrue(CS2106.isSameModule(editedCs2106));

        // Module code should have neither leading nor trailing spaces.
        // When a module code with leading or trailing spaces is input by the user, then Plannit
        // should remove those spaces in the user input before processing.

        // module code has trailing spaces, all other attributes same -> returns true
        String moduleWithTrailingSpaces = VALID_CS2106_MODULE_CODE + " ";
        editedCs2106 = new ModuleBuilder(CS2106).withModuleCode(moduleWithTrailingSpaces).build();
        assertTrue(CS2106.isSameModule(editedCs2106));

        // module code has leading spaces, all other attributes same -> returns true
        String moduleWithLeadingSpaces = " " + VALID_CS2106_MODULE_CODE;
        editedCs2106 = new ModuleBuilder(CS2106).withModuleCode(moduleWithLeadingSpaces).build();
        assertTrue(CS2106.isSameModule(editedCs2106));
    }

    @Test
    public void compareTo() {
        assertTrue(CS2103T.compareTo(CS2106) < 0); // lexicographically smaller module against larger

        assertTrue(CS2106.compareTo(CS2106) == 0); // Same module

        assertTrue(MA2001.compareTo(CS2106) > 0); // lexicographically larger module against smaller
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
                new ModuleBuilder(CS2106).withModuleCode(VALID_MA2001_MODULE_CODE).build();
        assertFalse(CS2106.equals(editedCs2106));

        // same module code but different casing -> returns true
        editedCs2106 =
                new ModuleBuilder(CS2106).withModuleCode(VALID_CS2106_MODULE_CODE.toLowerCase()).build();
        assertTrue(CS2106.equals(editedCs2106));

        // different module title -> returns false
        editedCs2106 = new ModuleBuilder(CS2106).withModuleTitle(VALID_MA2001_MODULE_TITLE).build();
        assertFalse(CS2106.equals(editedCs2106));
    }
}
