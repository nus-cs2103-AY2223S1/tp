package swift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.TASKS);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", CommandType.TASKS)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", CommandType.TASKS)));

        // different command type -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.HELP)));

        // different command type -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.EXIT)));

        // different command type -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.CONTACTS)));

        // different command type -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.CLEAR)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.TASKS);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.TASKS).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", CommandType.TASKS).hashCode());

        // different command type -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.HELP).hashCode());

        // different command type -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.EXIT).hashCode());

        // different command type -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.CONTACTS).hashCode());

        // different command type -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.CLEAR).hashCode());
    }
}
