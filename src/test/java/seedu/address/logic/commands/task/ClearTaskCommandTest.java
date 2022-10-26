package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ClearTaskCommand.
 */
public class ClearTaskCommandTest {

    @Test
    public void equals() {
        ClearTaskCommand firstCommand = new ClearTaskCommand();
        ClearTaskCommand secondCommand = new ClearTaskCommand();

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ClearTaskCommand firstCommandCopy = new ClearTaskCommand();
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

    }

}
