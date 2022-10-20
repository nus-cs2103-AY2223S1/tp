package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult.CommandType;

public class CommandResultTest {

    @Test
    public void constructor_typeShow_throwsAssertionError() {
        // not allowed to pass in command type of show without specifying the index
        assertThrows(AssertionError.class, () -> new CommandResult("feedback", CommandType.SHOW));
    }

    @Test
    public void isShowHelp_helpCommandType_assertTrue() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.HELP);
        assertTrue(commandResult.isShowHelp());
    }

    @Test
    public void isExit_exitCommandType_assertTrue() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.EXIT);
        assertTrue(commandResult.isExit());
    }

    @Test
    public void isList_listCommandType_assertTrue() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.LIST);
        assertTrue(commandResult.isList());
    }

    @Test
    public void isShow_indexOfShownEntity_assertTrue() {
        CommandResult commandResult = new CommandResult("feedback", 0);
        assertTrue(commandResult.isShow());
    }

    @Test
    public void isUpdateListView_assignUnassignCommandType_assertTrue() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.ASSIGN);
        assertTrue(commandResult.isUpdateListView());
        commandResult = new CommandResult("feedback", CommandType.UNASSIGN);
        assertTrue(commandResult.isUpdateListView());
    }

    @Test
    public void isUpdateDescription_assignUnassignEditCommandType_assertTrue() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.ASSIGN);
        assertTrue(commandResult.isUpdateDescription());
        commandResult = new CommandResult("feedback", CommandType.UNASSIGN);
        assertTrue(commandResult.isUpdateDescription());
        commandResult = new CommandResult("feedback", CommandType.EDIT);
        assertTrue(commandResult.isUpdateDescription());
    }

    @Test
    public void isShowHelp_notHelpType_assertFalse() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.OTHER);
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    public void isExit_notExitCommandType_assertFalse() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.OTHER);
        assertFalse(commandResult.isExit());
    }

    @Test
    public void isList_notListCommandType_assertFalse() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.OTHER);
        assertFalse(commandResult.isList());
    }

    @Test
    public void isShow_notIndexOfShownEntity_assertFalse() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.OTHER);
        assertFalse(commandResult.isShow());
    }

    @Test
    public void isUpdateListView_notAssignUnassignCommandType_assertFalse() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.OTHER);
        assertFalse(commandResult.isUpdateListView());
    }

    @Test
    public void isUpdateDescription_notAssignUnassignEditCommandType_assertFalse() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.OTHER);
        assertFalse(commandResult.isUpdateDescription());
    }

    @Test
    public void getFeedbackToUser_feedback_EqualToFeedback() {
        String feedback = "feedback";
        CommandResult commandResult = new CommandResult(feedback);
        assertEquals(feedback, commandResult.getFeedbackToUser());
    }

    @Test
    public void getIndex_notShowType_throwsAssertionError() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.OTHER);
        assertThrows(AssertionError.class, () -> commandResult.getIndex());
    }

    @Test
    public void getIndex_indexOfShownEntity_EqualToIndex() {
        CommandResult commandResult = new CommandResult("feedback", 0);
        assertEquals(0, commandResult.getIndex());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different type -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.HELP)));

        // different show value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", 0)));

        commandResult = new CommandResult("feedback", 0);

        //different index of show command -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", 1)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different type -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", CommandType.HELP).hashCode());

        // different show value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", 0).hashCode());

        commandResult = new CommandResult("feedback, 1");

        //different index of show command -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", 0)));
    }
}
