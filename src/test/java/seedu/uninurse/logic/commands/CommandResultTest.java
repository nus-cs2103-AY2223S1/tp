package seedu.uninurse.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.TEST);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", CommandType.TEST)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", CommandType.TEST)));

        // different commandType value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.HELP)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.TEST);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.TEST).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", CommandType.TEST).hashCode());

        // different commandType value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.HELP).hashCode());
    }
}
