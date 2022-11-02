package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        //assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

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
    public void isHelpShown() {
        CommandResult helpShown = new CommandResult("feedback", true, true);
        CommandResult helpNotShown = new CommandResult("feedback", false, true);
        assertTrue(helpShown.isHelpShown());
        assertFalse(helpNotShown.isHelpShown());
    }

    @Test
    public void isExit() {
        CommandResult exit = new CommandResult("feedback", true, true);
        CommandResult notExit = new CommandResult("feedback", true, false);
        assertTrue(exit.isExit());
        assertFalse(notExit.isExit());
    }

    @Test
    public void isList() {
        CommandResult list = CommandResult.createListCommandResult("feedback", "Buyer");
        assertTrue(list.isList());
    }

    @Test
    public void isAddedByPopup() {
        CommandResult popupCommandResult = CommandResult.createAddByPopupCommandResult("feedback",
                "Buyer");
        assertTrue(popupCommandResult.isAddedByPopup());
    }

    @Test
    public void isCheck() {
        CommandResult checkCommandResult = CommandResult.createCheckCommandResult("feedback",
                "Supplier", INDEX_FIRST);
        assertTrue(checkCommandResult.isCheck());
    }

    @Test
    public void getCheckType() {
        CommandResult checkCommandResult = CommandResult.createCheckCommandResult("feedback",
                "Supplier", INDEX_FIRST);
        assertEquals(checkCommandResult.getCheckType(), "Supplier");
    }

    @Test
    public void getIndex() {
        CommandResult checkCommandResult = CommandResult.createCheckCommandResult("feedback",
                "Supplier", INDEX_FIRST);
        assertEquals(checkCommandResult.getIndex(), INDEX_FIRST);
    }

    @Test
    public void getListType() {
        CommandResult list = CommandResult.createListCommandResult("feedback", "Buyer");
        assertEquals(list.getListType(), "Buyer");
    }

    @Test
    public void getAddType() {
        CommandResult addCommandResult = CommandResult.createListCommandResult("feedback", "Pet");
        assertEquals(addCommandResult.getListType(), "Pet");
    }
}
