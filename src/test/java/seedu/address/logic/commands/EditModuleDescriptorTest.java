package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MODULEONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MODULETWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CREDIT_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditModuleDescriptorBuilder;


public class EditModuleDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditModuleCommand.EditModuleDescriptor descriptorWithSameValues =
                new EditModuleCommand.EditModuleDescriptor(DESC_MODULEONE);
        assertTrue(DESC_MODULEONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_MODULEONE.equals(DESC_MODULEONE));

        // null -> returns false
        assertFalse(DESC_MODULEONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_MODULEONE.equals(3));

        // different values -> returns false
        assertFalse(DESC_MODULEONE.equals(DESC_MODULETWO));

        // different description -> returns false
        EditModuleCommand.EditModuleDescriptor editedModuleOne =
                new EditModuleDescriptorBuilder(DESC_MODULEONE)
                        .withModuleCode(VALID_MODULE_CODE_TWO).build();
        assertFalse(DESC_MODULEONE.equals(editedModuleOne));

        // different module name -> returns false
        editedModuleOne = new EditModuleDescriptorBuilder(DESC_MODULEONE).withModuleName(VALID_MODULE_NAME_TWO).build();
        assertFalse(DESC_MODULEONE.equals(editedModuleOne));

        // different module credit -> returns false
        editedModuleOne = new EditModuleDescriptorBuilder(DESC_MODULEONE)
                .withModuleCredit(VALID_MODULE_CREDIT_TWO).build();
        assertFalse(DESC_MODULEONE.equals(editedModuleOne));

    }
}
