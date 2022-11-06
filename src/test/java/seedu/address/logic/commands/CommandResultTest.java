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
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

    @Test
    public void isShowHelp_showHelpTrue_true() {
        CommandResult cr = new CommandResult("", true, false);
        assertTrue(cr.isShowHelp());

        CommandResult cr2 = new CommandResult("", false, false);
        assertFalse(cr2.isShowHelp());
    }

    @Test
    public void isExit() {
        CommandResult cr = new CommandResult("", false, true);
        assertTrue(cr.isExit());

        CommandResult cr2 = new CommandResult("", false, false);
        assertFalse(cr2.isExit());
    }

    @Test
    public void getCommandSpecific() {
        CommandResult cr = new CommandResult("", CommandSpecific.CLIENT);
        assertEquals(cr.getCommandSpecific(), CommandSpecific.CLIENT);

        CommandResult cr2 = new CommandResult("", CommandSpecific.DETAILED_MEETING);
        assertEquals(cr2.getCommandSpecific(), CommandSpecific.DETAILED_MEETING);

        // defaults to nonspecific
        CommandResult cr3 = new CommandResult("");
        assertEquals(cr3.getCommandSpecific(), CommandSpecific.NONSPECIFIC);
    }
}
