package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.EditExamDescriptorBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;

public class EditExamDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditExamCommand.EditExamDescriptor descriptorWithSameValues = new EditExamCommand.EditExamDescriptor(DESC_EXAMONE);
        assertTrue(DESC_EXAMONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EXAMONE.equals(DESC_EXAMONE));

        // null -> returns false
        assertFalse(DESC_EXAMONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_EXAMONE.equals(5));

        // different values -> returns false
        assertFalse(DESC_EXAMONE.equals(DESC_EXAMTWO));

        // different description -> returns false
        EditExamCommand.EditExamDescriptor editedExamOne = new EditExamDescriptorBuilder(DESC_EXAMONE).withDescription(VALID_DESCRIPTION_EXAMTWO).build();
        assertFalse(DESC_EXAMONE.equals(editedExamOne));

        // different module -> returns false
        editedExamOne = new EditExamDescriptorBuilder(DESC_EXAMONE).withModule(VALID_MODULE_EXAMTWO).build();
        assertFalse(DESC_EXAMONE.equals(editedExamOne));

        // different date -> returns false
        editedExamOne = new EditExamDescriptorBuilder(DESC_EXAMONE).withDate(VALID_DATE_EXAMTWO).build();
        assertFalse(DESC_EXAMONE.equals(editedExamOne));

    }
}
