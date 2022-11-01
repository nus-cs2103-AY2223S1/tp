package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static taskbook.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskDeleteCommand;

public class TaskDeleteCommandTest {
    @Test
    public void equals() {
        TaskDeleteCommand deleteFirstCommand = new TaskDeleteCommand(INDEX_FIRST_TASK);
        TaskDeleteCommand deleteSecondCommand = new TaskDeleteCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        TaskDeleteCommand deleteFirstCommandCopy = new TaskDeleteCommand(INDEX_FIRST_TASK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}

