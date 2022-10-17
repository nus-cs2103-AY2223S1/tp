package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static taskbook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.tasks.TaskUnmarkCommand;

public class TaskUnmarkCommandTest {
    @Test
    public void equals() {
        TaskUnmarkCommand taskUnmarkFirstCommand = new TaskUnmarkCommand(INDEX_FIRST_PERSON);
        TaskUnmarkCommand taskUnmarkSecondCommand = new TaskUnmarkCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(taskUnmarkFirstCommand.equals(taskUnmarkFirstCommand));

        // same values -> returns true
        TaskUnmarkCommand taskUnmarkFirstCommandCopy = new TaskUnmarkCommand(INDEX_FIRST_PERSON);
        assertTrue(taskUnmarkFirstCommand.equals(taskUnmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(taskUnmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(taskUnmarkFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(taskUnmarkFirstCommand.equals(taskUnmarkSecondCommand));
    }
}
