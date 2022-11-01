package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_WATCH_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CS2040;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains unit tests for {@code EditTaskDescriptor}.
 */
public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor descriptorWithSameValues =
            new EditTaskCommand.EditTaskDescriptor(DESC_TUTORIAL);
        assertTrue(DESC_TUTORIAL.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TUTORIAL.equals(DESC_TUTORIAL));

        // null -> returns false
        assertFalse(DESC_TUTORIAL.equals(null));

        // different types -> returns false
        assertFalse(DESC_TUTORIAL.equals(5));

        // different values -> returns false
        assertFalse(DESC_TUTORIAL.equals(DESC_LECTURE));

        // different module -> returns false
        EditTaskCommand.EditTaskDescriptor editedTask = new EditTaskDescriptorBuilder(DESC_TUTORIAL)
            .withModule(VALID_MODULE_CS2040).build();
        assertFalse(DESC_TUTORIAL.equals(editedTask));

        // different description -> returns false
        editedTask = new EditTaskDescriptorBuilder(DESC_TUTORIAL)
            .withDescription(VALID_DESCRIPTION_WATCH_LECTURE).build();
        assertFalse(DESC_TUTORIAL.equals(editedTask));
    }
}
