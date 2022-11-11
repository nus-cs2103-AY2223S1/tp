package longtimenosee.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", false, false, false);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false)));

        assertTrue(commandResult.equals(
                new CommandResult("feedback", false, false, false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", false, false, false)));

        // different showHelp value -> returns false


        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false,
                false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true,
                false, false, false, false)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", false, false, false);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", false, false, false).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", false, false, false).hashCode());

        // different showHelp value -> returns different hashcode

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false,
                false, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true,
                false, true, false, false).hashCode());
    }
}
