package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    @Test void constructor() {
        assertThrows(NullPointerException.class, () -> new CommandResult(null));
    }

    @Test
    public void getFeedbackToUser() {
        // blank string
        CommandResult cmdResA = new CommandResult("");
        assertEquals("", cmdResA.getFeedbackToUser());

        // normal string
        CommandResult commandResult = new CommandResult("feedback");
        assertEquals("feedback", commandResult.getFeedbackToUser());
    }

    @Test
    public void isShowHelp() {
        CommandResult cmdResA = new CommandResult("feedback", true, true);
        assertTrue(cmdResA.isShowHelp());

        CommandResult cmdResB = new CommandResult("feedback", true, false);
        assertTrue(cmdResB.isShowHelp());

        CommandResult cmdResC = new CommandResult("feedback", false, true);
        assertFalse(cmdResC.isShowHelp());

        CommandResult cmdResD = new CommandResult("feedback", false, false);
        assertFalse(cmdResD.isShowHelp());

        CommandResult cmdResE = new CommandResult("feedback");
        assertFalse(cmdResE.isShowHelp());
    }

    @Test
    public void isExit() {
        CommandResult cmdResA = new CommandResult("feedback", true, true);
        assertTrue(cmdResA.isExit());

        CommandResult cmdResB = new CommandResult("feedback", false, true);
        assertTrue(cmdResB.isExit());

        CommandResult cmdResC = new CommandResult("feedback", true, false);
        assertFalse(cmdResC.isExit());

        CommandResult cmdResD = new CommandResult("feedback", false, false);
        assertFalse(cmdResD.isExit());

        CommandResult cmdResE = new CommandResult("feedback");
        assertFalse(cmdResE.isExit());
    }

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
}
