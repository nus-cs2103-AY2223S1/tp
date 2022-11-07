package seedu.codeConnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codeConnect.logic.commands.CommandTestUtil.DESC_FINISH_TP;
import static seedu.codeConnect.logic.commands.CommandTestUtil.DESC_LAB_2;
import static seedu.codeConnect.logic.commands.CommandTestUtil.VALID_DEADLINE_FINISH_TP;
import static seedu.codeConnect.logic.commands.CommandTestUtil.VALID_MODULE_FINISH_TP;
import static seedu.codeConnect.logic.commands.CommandTestUtil.VALID_TASK_NAME_FINISH_TP;

import org.junit.jupiter.api.Test;

import seedu.codeConnect.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.codeConnect.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_LAB_2);
        assertTrue(DESC_LAB_2.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_LAB_2.equals(DESC_LAB_2));

        // null -> returns false
        assertFalse(DESC_LAB_2.equals(null));

        // different types -> returns false
        assertFalse(DESC_LAB_2.equals(5));

        // different values -> returns false
        assertFalse(DESC_LAB_2.equals(DESC_FINISH_TP));

        // different name -> returns false
        EditTaskDescriptor editedTP = new EditTaskDescriptorBuilder(DESC_LAB_2).withName(VALID_TASK_NAME_FINISH_TP)
                .build();
        assertFalse(DESC_LAB_2.equals(editedTP));

        // different module -> returns false
        editedTP = new EditTaskDescriptorBuilder(DESC_LAB_2).withModule(VALID_MODULE_FINISH_TP).build();
        assertFalse(DESC_LAB_2.equals(editedTP));

        // different deadline -> returns false
        editedTP = new EditTaskDescriptorBuilder(DESC_LAB_2).withDeadline(VALID_DEADLINE_FINISH_TP).build();
        assertFalse(DESC_LAB_2.equals(editedTP));
    }
}
