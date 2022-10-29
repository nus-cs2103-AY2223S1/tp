package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

public class MatchCommandTest {
    @Test
    public void equals() {
        MatchCommand firstCommand = new MatchCommand(INDEX_FIRST);
        MatchCommand secondCommand = new MatchCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same fields -> returns true
        MatchCommand firstCommandCopy = new MatchCommand(INDEX_FIRST);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> return false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different indexes -> return false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
