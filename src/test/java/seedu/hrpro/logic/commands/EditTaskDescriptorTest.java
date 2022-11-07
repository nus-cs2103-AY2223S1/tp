package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.DESC_ALPHA;
import static seedu.hrpro.logic.commands.CommandTestUtil.DESC_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDEADLINE_BRAVO;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TASKDESCRIPTION_BRAVO;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.hrpro.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_ALPHA);
        assertTrue(DESC_ALPHA.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ALPHA.equals(DESC_ALPHA));

        // null -> returns false
        assertFalse(DESC_ALPHA.equals(null));

        // different types -> returns false
        assertFalse(DESC_ALPHA.equals(5));

        // different values -> returns false
        assertFalse(DESC_ALPHA.equals(DESC_BRAVO));

        // different description -> returns false
        EditTaskDescriptor editedAlpha = new EditTaskDescriptorBuilder(DESC_ALPHA)
                .withDescription(VALID_TASKDESCRIPTION_BRAVO).build();
        assertFalse(DESC_ALPHA.equals(editedAlpha));

        // different deadline -> returns false
        editedAlpha = new EditTaskDescriptorBuilder(DESC_ALPHA).withDeadline(VALID_TASKDEADLINE_BRAVO).build();
        assertFalse(DESC_ALPHA.equals(editedAlpha));
    }
}
