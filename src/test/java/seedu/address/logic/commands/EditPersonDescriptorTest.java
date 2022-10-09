package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASK_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HIGH_PRIORITY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_ALPHA);
        assertTrue(DESC_ALPHA.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ALPHA.equals(DESC_ALPHA));

        // null -> returns false
        assertFalse(DESC_ALPHA.equals(null));

        // different types -> returns false
        assertFalse(DESC_ALPHA.equals(5));

        // different values -> returns false
        assertFalse(DESC_ALPHA.equals(DESC_BETA));

        // different name -> returns false
        EditPersonDescriptor editedAlpha = new EditPersonDescriptorBuilder(DESC_ALPHA)
                .withName(VALID_NAME_TASK_BETA).build();
        assertFalse(DESC_ALPHA.equals(editedAlpha));

        // different module -> returns false
        editedAlpha = new EditPersonDescriptorBuilder(DESC_ALPHA).withModule(VALID_MODULE_BETA).build();
        assertFalse(DESC_ALPHA.equals(editedAlpha));

        // different deadline -> returns false
        editedAlpha = new EditPersonDescriptorBuilder(DESC_ALPHA).withDeadline(VALID_DEADLINE_BETA).build();
        assertFalse(DESC_ALPHA.equals(editedAlpha));

        // different tags -> returns false
        editedAlpha = new EditPersonDescriptorBuilder(DESC_ALPHA).withTags(VALID_TAG_HIGH_PRIORITY).build();
        assertFalse(DESC_ALPHA.equals(editedAlpha));
    }
}
