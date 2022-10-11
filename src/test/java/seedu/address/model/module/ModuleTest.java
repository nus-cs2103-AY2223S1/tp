package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_DESCRIPTION_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CYBERSEC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MODULE_COORDINATOR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS2107;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> module.getTags().remove(0));
    }

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS2103T.isSameModule(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.isSameModule(null));

        // same code, all other attributes different -> returns true
        Module editedCS2103T = new ModuleBuilder(CS2103T).withName(VALID_MODULE_NAME_CYBERSEC)
                .withModuleDescription(VALID_MODULE_DESCRIPTION_CYBERSEC)
                .withTags(VALID_TAG_MODULE_COORDINATOR).build();
        assertTrue(CS2103T.isSameModule(editedCS2103T));

        // different code, all other attributes same -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T)
                .withModuleCode(VALID_MODULE_CODE_CYBERSEC).build();
        assertFalse(CS2103T.isSameModule(editedCS2103T));

        // code differs in case, all other attributes same -> returns false
        Module editedCS2107 = new ModuleBuilder(CS2107)
                .withModuleCode(VALID_MODULE_CODE_CYBERSEC.toLowerCase()).build();
        assertFalse(CS2107.isSameModule(editedCS2107));

        // code has trailing spaces, all other attributes same -> returns false
        String codeWithTrailingSpaces = VALID_MODULE_CODE_CYBERSEC + " ";
        editedCS2107 = new ModuleBuilder(CS2107).withModuleCode(codeWithTrailingSpaces).build();
        assertFalse(CS2107.isSameModule(editedCS2107));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module cs2103tCopy = new ModuleBuilder(CS2103T).build();
        assertTrue(CS2103T.equals(cs2103tCopy));

        // same object -> returns true
        assertTrue(CS2103T.equals(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.equals(null));

        // different type -> returns false
        assertFalse(CS2103T.equals(5));

        // different module -> returns false
        assertFalse(CS2103T.equals(CS2107));

        // different code -> returns false
        Module editedCS2103T = new ModuleBuilder(CS2103T).withModuleCode(VALID_MODULE_CODE_CYBERSEC).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different name  -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withName(VALID_MODULE_NAME_CYBERSEC).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different description -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T)
                .withModuleDescription(VALID_MODULE_DESCRIPTION_CYBERSEC).build();
        assertFalse(CS2103T.equals(editedCS2103T));

        // different tags -> returns false
        editedCS2103T = new ModuleBuilder(CS2103T).withTags(VALID_TAG_MODULE_COORDINATOR).build();
        assertFalse(CS2103T.equals(editedCS2103T));
    }
}
