package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106_DELETE_TASK_ONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106_DELETE_TASK_TWO;
import static seedu.address.logic.commands.DeleteTaskCommand.DeleteTaskFromModuleDescriptor;
import static seedu.address.testutil.TypicalModules.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeleteTaskFromModuleDescriptorBuilder;

public class DeleteTaskFromModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        DeleteTaskFromModuleDescriptor descriptorWithSameValues =
                new DeleteTaskFromModuleDescriptor(DESC_CS2106_DELETE_TASK_ONE);
        assertTrue(DESC_CS2106_DELETE_TASK_ONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2106_DELETE_TASK_ONE.equals(DESC_CS2106_DELETE_TASK_ONE));

        // null -> returns false
        assertFalse(DESC_CS2106_DELETE_TASK_ONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2106_DELETE_TASK_ONE.equals(5));

        // different task number -> returns false
        assertFalse(DESC_CS2106_DELETE_TASK_ONE.equals(DESC_CS2106_DELETE_TASK_TWO));

        // different module code -> returns false
        DeleteTaskFromModuleDescriptor descriptorWithDifferentModuleCode =
                new DeleteTaskFromModuleDescriptorBuilder(CS2103T).build();
        assertFalse(DESC_CS2106_DELETE_TASK_ONE.equals(descriptorWithDifferentModuleCode));
    }
}
