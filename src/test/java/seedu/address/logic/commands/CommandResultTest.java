package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false,
                false, false, false, false,
                false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true,
                false, false, false,
                false, false, false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false,
                true, false, false,
                false, false, false, false, false)));

        // different showModuleList value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false,
                false, true, false,
                false, false, false, false, false)));

        // different showStudentList value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, true, false,
                false, false, false, false)));

        // different showTargetModule value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false, true,
                false, false, false, false)));

        // different showModule value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false,
                false, true, false, false, false)));

        // different showScheduleList value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false,
                false, false,
                false, false, true, false, false)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true,
                false, false,
                false, false,
                false, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                true, false,
                false, false, false,
                false, false, false).hashCode());

        // different showModuleList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, true,
                false, false, false,
                false, false, false).hashCode());

        // different showStudentList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, false,
                true, false, false,
                false, false, false).hashCode());

        // different showTargetModule value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, false,
                false, true, false,
                false, false, false).hashCode());

        // different showModule value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, false,
                false, false, true,
                false, false, false).hashCode());

        // different showScheduleList value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, false,
                false, false, false,
                true, false, false).hashCode());

    }
}
