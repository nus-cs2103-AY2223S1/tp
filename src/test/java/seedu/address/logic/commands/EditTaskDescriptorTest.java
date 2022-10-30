package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST_TASK;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SECOND_TASK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SECOND_TASK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor descriptorWithSameValues =
            new EditTaskCommand.EditTaskDescriptor(DESC_FIRST_TASK);
        assertTrue(DESC_FIRST_TASK.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FIRST_TASK.equals(DESC_FIRST_TASK));

        // null -> returns false
        assertFalse(DESC_FIRST_TASK.equals(null));

        // different types -> returns false
        assertFalse(DESC_FIRST_TASK.equals(5));

        // different values -> returns false
        assertFalse(DESC_FIRST_TASK.equals(DESC_SECOND_TASK));

        // different module -> returns false
        EditTaskCommand.EditTaskDescriptor editedFirstTask = new EditTaskDescriptorBuilder(DESC_FIRST_TASK)
            .withModule(VALID_MODULE_SECOND_TASK).build();
        assertFalse(DESC_FIRST_TASK.equals(editedFirstTask));

        // different description -> returns false
        editedFirstTask = new EditTaskDescriptorBuilder(DESC_FIRST_TASK).
            withDescription(VALID_DESCRIPTION_SECOND_TASK).build();
        assertFalse(DESC_FIRST_TASK.equals(editedFirstTask));
    }
}
