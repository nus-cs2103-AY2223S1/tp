package seedu.phu.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    private CommandHistory commandHistory;
    private final int firstCommandIndex = -1;
    private final int firstStatePointer = 0;
    private final int secondStatePointer = 1;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    public void constructor_withCommandHistory_copiesCommandHistory() {
        final CommandHistory commandHistoryWithOneCommand = new CommandHistory();
        commandHistoryWithOneCommand.addCommand(DeleteCommand.COMMAND_WORD);
        commandHistoryWithOneCommand.setLastCommandAsModify();

        assertEquals(commandHistoryWithOneCommand, new CommandHistory(commandHistoryWithOneCommand));
    }

    @Test
    public void addCommand() {
        commandHistory.addCommand(ListCommand.COMMAND_WORD);
        commandHistory.addCommand(DeleteCommand.COMMAND_WORD);
        assertEquals(Arrays.asList(ListCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD),
                commandHistory.getHistory());
    }

    @Test
    public void setLastCommandAsModify_existsCommandInHistory_success() {
        commandHistory.addCommand(DeleteCommand.COMMAND_WORD);
        commandHistory.setLastCommandAsModify();
        int lastIndex = commandHistory.getHistory().size() - 1;

        assertEquals(Arrays.asList(firstCommandIndex, lastIndex), commandHistory.getModifyCommandIndexes());
    }

    @Test
    public void setLastCommandAsModify_noCommandInHistory_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> commandHistory.setLastCommandAsModify());
    }

    @Test
    public void getPreviousModifyCommand_existsPreviousModifyCommandInHistory_success() {
        commandHistory.addCommand(DeleteCommand.COMMAND_WORD);
        commandHistory.setLastCommandAsModify();

        String actualCommand = commandHistory.getPreviousModifyCommand();
        assertEquals(DeleteCommand.COMMAND_WORD, actualCommand);
        assertEquals(firstStatePointer, commandHistory.getCurrentStatePointer());
    }

    @Test
    public void getPreviousModifyCommand_noPreviousModifyCommandInHistory_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> commandHistory.getPreviousModifyCommand());

        commandHistory.addCommand(DeleteCommand.COMMAND_WORD);
        assertThrows(AssertionError.class, () -> commandHistory.getPreviousModifyCommand());

        commandHistory.setLastCommandAsModify();
        commandHistory.getPreviousModifyCommand();
        assertThrows(AssertionError.class, () -> commandHistory.getPreviousModifyCommand());
    }

    @Test
    public void getNextModifyCommand_existsNextCommandInHistory_success() {
        commandHistory.addCommand(DeleteCommand.COMMAND_WORD);
        commandHistory.setLastCommandAsModify();
        commandHistory.getPreviousModifyCommand();

        String actualCommand = commandHistory.getNextModifyCommand();
        assertEquals(DeleteCommand.COMMAND_WORD, actualCommand);
        assertEquals(secondStatePointer, commandHistory.getCurrentStatePointer());
    }

    @Test
    public void getNextModifyCommand_noNextModifyCommandInHistory_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> commandHistory.getNextModifyCommand());

        commandHistory.addCommand(DeleteCommand.COMMAND_WORD);
        assertThrows(AssertionError.class, () -> commandHistory.getNextModifyCommand());

        commandHistory.setLastCommandAsModify();
        assertThrows(AssertionError.class, () -> commandHistory.getNextModifyCommand());
    }

    @Test
    public void equals() {
        final CommandHistory commandHistoryWithDelete = new CommandHistory();
        commandHistoryWithDelete.addCommand(DeleteCommand.COMMAND_WORD);
        final CommandHistory anotherCommandHistoryWithDelete = new CommandHistory();
        anotherCommandHistoryWithDelete.addCommand(DeleteCommand.COMMAND_WORD);
        final CommandHistory commandHistoryWithList = new CommandHistory();
        commandHistoryWithList.addCommand(ListCommand.COMMAND_WORD);

        // same object -> returns true
        assertTrue(commandHistoryWithDelete.equals(commandHistoryWithDelete));

        // same values and state -> returns true
        assertTrue(commandHistoryWithDelete.equals(anotherCommandHistoryWithDelete));

        // null -> returns false
        assertFalse(commandHistoryWithDelete.equals(null));

        // different types -> returns false
        assertFalse(commandHistoryWithDelete.equals(5.0f));

        // different values -> returns false
        assertFalse(commandHistoryWithDelete.equals(commandHistoryWithList));

        // same values but different state -> returns false
        anotherCommandHistoryWithDelete.setLastCommandAsModify();
        assertFalse(commandHistoryWithDelete.equals(anotherCommandHistoryWithDelete));
    }

}
