package swift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.logic.commands.CommandTestUtil.DESC_TASK_1;
import static swift.logic.commands.CommandTestUtil.DESC_TASK_2;
import static swift.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import org.junit.jupiter.api.Test;

import swift.logic.commands.EditTaskCommand.EditTaskDescriptor;
import swift.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_TASK_1);
        assertTrue(DESC_TASK_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TASK_1.equals(DESC_TASK_1));

        // null -> returns false
        assertFalse(DESC_TASK_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_TASK_1.equals(5));

        // different values -> returns false
        assertFalse(DESC_TASK_1.equals(DESC_TASK_2));

        // different name -> returns false
        EditTaskDescriptor editedAmy = new EditTaskDescriptorBuilder(
                DESC_TASK_1).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_TASK_1.equals(editedAmy));
    }
}
