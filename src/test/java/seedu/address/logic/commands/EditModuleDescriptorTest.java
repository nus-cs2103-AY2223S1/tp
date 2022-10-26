package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_10;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_11;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_12;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_9;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        ModuleCommand.EditModuleDescriptor descriptorWithSameValues =
                new ModuleCommand.EditModuleDescriptor(MODULE_DESC_AMY);
        assertTrue(MODULE_DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(MODULE_DESC_AMY.equals(MODULE_DESC_AMY));

        // null -> returns false
        assertFalse(MODULE_DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(MODULE_DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(MODULE_DESC_AMY.equals(MODULE_DESC_BOB));

        // different current modules -> returns false
        ModuleCommand.EditModuleDescriptor editedAmy = new EditModuleDescriptorBuilder(MODULE_DESC_AMY)
                .withCurrentModules(VALID_MODULE_9).build();
        assertFalse(MODULE_DESC_AMY.equals(editedAmy));

        // different previous modules -> returns false
        editedAmy = new EditModuleDescriptorBuilder(MODULE_DESC_AMY).withPreviousModules(VALID_MODULE_10).build();
        assertFalse(MODULE_DESC_AMY.equals(editedAmy));

        // different planned modules -> returns false
        editedAmy = new EditModuleDescriptorBuilder(MODULE_DESC_AMY).withPlannedModules(VALID_MODULE_11).build();
        assertFalse(MODULE_DESC_AMY.equals(editedAmy));

        // different modules to remove -> returns false
        editedAmy = new EditModuleDescriptorBuilder(MODULE_DESC_AMY).withModToRemove(VALID_MODULE_12).build();
        assertFalse(MODULE_DESC_AMY.equals(editedAmy));
    }
}
