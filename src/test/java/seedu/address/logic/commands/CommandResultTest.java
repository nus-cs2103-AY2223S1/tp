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
        assertTrue(commandResult.equals((new CommandResult("feedback", false, false,
                false, true, false))));

        assertTrue(commandResult.equals((new CommandResult("feedback", false, false,
                false, false, false))));

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
    public void isShowUserGuide() {
        CommandResult firstCommandResult = new CommandResult("feedback");
        CommandResult secondCommandResult = new CommandResult("feedback", true, false);
        CommandResult thirdCommandResult = new CommandResult("feedback", false, false, true);

        assertEquals(firstCommandResult.isShowUserGuide(), false);
        assertEquals(secondCommandResult.isShowUserGuide(), true);
        assertEquals(thirdCommandResult.isShowUserGuide(), false);
    }

    @Test
    public void isFilteredTransactions() {
        CommandResult firstCommandResult = new CommandResult("feedback");
        CommandResult secondCommandResult = new CommandResult("feedback", true, false);
        CommandResult thirdCommandResult = new CommandResult("feedback", false, false, true);

        assertEquals(firstCommandResult.isFilteredTransactions(), false);
        assertEquals(secondCommandResult.isFilteredTransactions(), false);
        assertEquals(thirdCommandResult.isFilteredTransactions(), true);
    }

    @Test
    public void isSortTransactions() {
        CommandResult firstCommandResult = new CommandResult("feedback");
        CommandResult secondCommandResult = new CommandResult("feedback", true, false, false, false, false);
        CommandResult thirdCommandResult = new CommandResult("feedback", false, false, false, true, false);

        assertEquals(firstCommandResult.isSortedTransactions(), false);
        assertEquals(secondCommandResult.isSortedTransactions(), false);
        assertEquals(thirdCommandResult.isSortedTransactions(), true);
    }

    @Test
    public void hasNoUiChange() {
        CommandResult firstCommandResult = new CommandResult("feedback");
        CommandResult secondCommandResult = new CommandResult("feedback", true);
        CommandResult thirdCommandResult = new CommandResult("feedback", false, false, false, false, true);
        CommandResult fourthCommandResult = new CommandResult("feedback", false, false, true);

        assertEquals(firstCommandResult.hasNoUiChange(), false);
        assertEquals(secondCommandResult.hasNoUiChange(), true);
        assertEquals(thirdCommandResult.hasNoUiChange(), true);
        assertEquals(fourthCommandResult.hasNoUiChange(), false);
    }


    @Test
    public void isExit() {
        CommandResult firstCommandResult = new CommandResult("feedback");
        CommandResult secondCommandResult = new CommandResult("feedback", false, true);
        CommandResult thirdCommandResult = new CommandResult("feedback", false, false, true);

        assertEquals(firstCommandResult.isExit(), false);
        assertEquals(secondCommandResult.isExit(), true);
        assertEquals(thirdCommandResult.isExit(), false);
    }

    @Test
    public void toStringTest() {
        String feedbackToUser = "feedback to user";
        CommandResult commandResult = new CommandResult(feedbackToUser);
        assertEquals(commandResult.toString(), feedbackToUser);
    }

}
