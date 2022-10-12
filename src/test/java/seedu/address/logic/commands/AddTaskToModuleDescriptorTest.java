package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddTaskCommand.AddTaskToModuleDescriptor;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106_ADD_TASK_A;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2106_ADD_TASK_B;
import static seedu.address.testutil.TypicalModules.MA2001;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AddTaskToModuleDescriptorBuilder;

public class AddTaskToModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        AddTaskToModuleDescriptor descriptorWithSameValues =
                new AddTaskToModuleDescriptor(DESC_CS2106_ADD_TASK_A);
        assertTrue(DESC_CS2106_ADD_TASK_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2106_ADD_TASK_A.equals(DESC_CS2106_ADD_TASK_A));

        // null -> returns false
        assertFalse(DESC_CS2106_ADD_TASK_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2106_ADD_TASK_A.equals(5));

        // different taskDescription -> returns false
        assertFalse(DESC_CS2106_ADD_TASK_A.equals(DESC_CS2106_ADD_TASK_B));

        // different module code -> returns false
        AddTaskToModuleDescriptor descriptorWithDifferentModuleCode =
                new AddTaskToModuleDescriptorBuilder(DESC_CS2106_ADD_TASK_A).build();
        descriptorWithDifferentModuleCode.setModuleCodeOfModuleToAddTaskTo(MA2001.getModuleCode());
        assertFalse(DESC_CS2106_ADD_TASK_A.equals(descriptorWithDifferentModuleCode));
    }
}
