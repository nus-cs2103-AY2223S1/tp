package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static taskbook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskMarkCommand;

public class TaskMarkCommandTest {
    @Test
    public void equals() {
        TaskMarkCommand taskMarkFirstCommand = new TaskMarkCommand(INDEX_FIRST_PERSON);
        TaskMarkCommand taskMarkSecondCommand = new TaskMarkCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(taskMarkFirstCommand.equals(taskMarkFirstCommand));

        // same values -> returns true
        TaskMarkCommand taskMarkFirstCommandCopy = new TaskMarkCommand(INDEX_FIRST_PERSON);
        assertTrue(taskMarkFirstCommand.equals(taskMarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(taskMarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(taskMarkFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(taskMarkFirstCommand.equals(taskMarkSecondCommand));
    }
}
