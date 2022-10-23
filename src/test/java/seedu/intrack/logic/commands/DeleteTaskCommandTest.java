package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {
    @Test
    public void equals() {
        DeleteTaskCommand deleteTaskFirstCommand = new DeleteTaskCommand(INDEX_FIRST_INTERNSHIP);
        DeleteTaskCommand deleteTaskSecondCommand = new DeleteTaskCommand(INDEX_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(deleteTaskFirstCommand.equals(deleteTaskFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteTaskFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_INTERNSHIP);
        assertTrue(deleteTaskFirstCommand.equals(deleteTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTaskFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(deleteTaskFirstCommand.equals(deleteTaskSecondCommand));
    }
}
