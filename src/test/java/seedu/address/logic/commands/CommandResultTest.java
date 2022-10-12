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

    // -------------------------- Dependent ------------------------------------ //
    @Test
    public void isShowHelp_showHelpTrue_true() {
        CommandResult cr = new CommandResult("", true, false);
        assertTrue(cr.isShowHelp());
    }

    @Test
    public void isShowHelp_showHelpFalse_false() {
        CommandResult cr = new CommandResult("", false, false);
        assertFalse(cr.isShowHelp());
    }

    @Test
    public void isExit_exitTrue_true() {
        CommandResult cr = new CommandResult("", false, true);
        assertTrue(cr.isExit());
    }

    @Test
    public void isExit_exitFalse_false() {
        CommandResult cr = new CommandResult("", false, false);
        assertFalse(cr.isExit());
    }

    @Test
    public void isClientSpecific() {
        CommandResult cr = new CommandResult("", CommandSpecific.CLIENT);
        assertTrue(cr.isClientSpecific());
        CommandResult cr2 = new CommandResult("", CommandSpecific.DETAILED_CLIENT);
        assertFalse(cr2.isClientSpecific());
        CommandResult cr3 = new CommandResult("");
        assertFalse(cr3.isClientSpecific());
    }

    @Test
    public void isDetailedClientSpecific() {
        CommandResult cr = new CommandResult("", CommandSpecific.DETAILED_CLIENT);
        assertTrue(cr.isDetailedClientSpecific());
        CommandResult cr2 = new CommandResult("", CommandSpecific.DETAILED_MEETING);
        assertFalse(cr2.isDetailedClientSpecific());
        CommandResult cr3 = new CommandResult("");
        assertFalse(cr3.isDetailedClientSpecific());
    }

    @Test
    public void isMeetingSpecific() {
        CommandResult cr = new CommandResult("", CommandSpecific.MEETING);
        assertTrue(cr.isMeetingSpecific());
        CommandResult cr2 = new CommandResult("", CommandSpecific.DETAILED_MEETING);
        assertFalse(cr2.isDetailedClientSpecific());
        CommandResult cr3 = new CommandResult("");
        assertFalse(cr3.isDetailedClientSpecific());
    }

    @Test
    public void isDetailedMeetingSpecific() {
        CommandResult cr = new CommandResult("", CommandSpecific.DETAILED_MEETING);
        assertTrue(cr.isDetailedMeetingSpecific());
        CommandResult cr2 = new CommandResult("", CommandSpecific.DETAILED_CLIENT);
        assertFalse(cr2.isDetailedMeetingSpecific());
        CommandResult cr3 = new CommandResult("");
        assertFalse(cr3.isDetailedMeetingSpecific());
    }
}
