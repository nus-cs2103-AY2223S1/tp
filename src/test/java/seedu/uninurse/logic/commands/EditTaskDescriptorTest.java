package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DATETIME_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DATETIME_RECURRENCE_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DESCRIPTION_DATETIME_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DESCRIPTION_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_DESCRIPTION_RECURRENCE_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_INSULIN;
import static seedu.uninurse.logic.commands.CommandTestUtil.DESC_TASK_RECURRENCE_INSULIN;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.EditTaskCommand.EditTaskDescriptor;

public class EditTaskDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(
                DESC_TASK_INSULIN.getDescription(),
                DESC_TASK_INSULIN.getDateTime(),
                DESC_TASK_INSULIN.getRecurrenceAndFrequency());
        assertEquals(DESC_TASK_INSULIN, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_TASK_INSULIN, DESC_TASK_INSULIN);

        // null -> returns false
        assertNotEquals(null, DESC_TASK_INSULIN);

        // different types -> returns false
        assertNotEquals(5, DESC_TASK_INSULIN);

        // different values -> returns false
        assertNotEquals(DESC_TASK_DESCRIPTION_INSULIN, DESC_TASK_INSULIN);

        // different task description, same date time, same recurrence -> returns false
        assertNotEquals(DESC_TASK_INSULIN, DESC_TASK_DATETIME_RECURRENCE_INSULIN);

        // same task description, different date time, same recurrence -> returns false
        assertNotEquals(DESC_TASK_INSULIN, DESC_TASK_DESCRIPTION_RECURRENCE_INSULIN);

        // same task description, same date time, different recurrence -> returns false
        assertNotEquals(DESC_TASK_INSULIN, DESC_TASK_DESCRIPTION_DATETIME_INSULIN);

        // different task description, different date time, same recurrence -> returns false
        assertNotEquals(DESC_TASK_INSULIN, DESC_TASK_RECURRENCE_INSULIN);

        // different task description, same date time, different recurrence -> returns false
        assertNotEquals(DESC_TASK_INSULIN, DESC_TASK_DATETIME_INSULIN);

        // same task description, different date time, different recurrence -> returns false
        assertNotEquals(DESC_TASK_INSULIN, DESC_TASK_DESCRIPTION_INSULIN);
    }
}
