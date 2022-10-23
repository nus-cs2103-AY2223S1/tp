package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.CommandHistory.MAX_COMMAND_HISTORY_SIZE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    private final CommandHistory commandHistory = new CommandHistory();


    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), commandHistory.getCommandHistoryList());
    }

    @Test
    public void setCommandHistoryList_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandHistory.setCommandHistoryList(null));
    }

    @Test
    public void setCommandHistoryList_withValidList_replacesData() {
        List<String> commandHistoryList = new ArrayList<>();
        commandHistoryList.add("list");
        commandHistoryList.add("find benny");
        CommandHistory newData = new CommandHistory();
        newData.setCommandHistoryList(commandHistoryList);

        commandHistory.setCommandHistoryList(commandHistoryList);
        assertEquals(newData, commandHistory);
    }

    @Test
    public void addToEmptyCommandHistoryList() {
        List<String> commandHistoryList = new ArrayList<>();
        commandHistoryList.add("list");
        CommandHistory expected = new CommandHistory();
        expected.setCommandHistoryList(commandHistoryList);

        CommandHistory actual = new CommandHistory();
        actual.addToCommandHistory("list");
        assertEquals(expected, actual);
    }

    @Test
    public void addToFullCommandHistoryList() {
        List<String> commandHistoryList = new ArrayList<>();
        CommandHistory expected = new CommandHistory();
        for (int i = 1; i <= MAX_COMMAND_HISTORY_SIZE; i++) {
            commandHistoryList.add("find " + i);
        }
        expected.setCommandHistoryList(commandHistoryList);

        List<String> actualCommandHistoryList = new ArrayList<>();
        CommandHistory actual = new CommandHistory();
        for (int i = 0; i <= MAX_COMMAND_HISTORY_SIZE; i++) {
            actualCommandHistoryList.add("find " + i);
        }
        actual.setCommandHistoryList(commandHistoryList);

        assertEquals(expected, actual);
    }

    @Test
    public void getPrevCommand_onEmptyCommandList() {
        CommandHistory actual = new CommandHistory();
        assertEquals("", actual.getPrevCommand());
    }

    @Test
    public void getNextCommand_onEmptyCommandList() {
        CommandHistory actual = new CommandHistory();
        assertEquals("", actual.getNextCommand());
    }

    // Get previous and next commands when index is at/beyond limit of command history list
    @Test
    public void getPrevCommand_atZeroIndex_onNonEmptyCommandList() {
        CommandHistory actual = new CommandHistory();
        List<String> commandHistoryList = new ArrayList<>();
        for (int i = 0; i < MAX_COMMAND_HISTORY_SIZE / 2; i++) {
            commandHistoryList.add("find " + i);
        }
        actual.setCommandHistoryList(commandHistoryList);
        actual.setCurrentZeroBasedIndex(0);

        assertEquals("find 0", actual.getPrevCommand());
    }
    @Test
    public void getNextCommand_atMaxIndex_onNonEmptyCommandList() {
        CommandHistory actual = new CommandHistory();
        List<String> commandHistoryList = new ArrayList<>();
        int listSize = MAX_COMMAND_HISTORY_SIZE / 2;
        int maxIndex = listSize - 1;
        for (int i = 0; i < listSize; i++) {
            commandHistoryList.add("find " + i);
        }
        actual.setCommandHistoryList(commandHistoryList);
        actual.setCurrentZeroBasedIndex(maxIndex);

        assertEquals("", actual.getNextCommand());
        assertEquals(listSize, actual.getCurrentZeroBasedIndex());
    }

    @Test
    public void getNextCommand_beyondMaxIndex_onNonEmptyCommandList() {
        CommandHistory actual = new CommandHistory();
        List<String> commandHistoryList = new ArrayList<>();
        int listSize = MAX_COMMAND_HISTORY_SIZE / 2;
        int beyondMaxIndex = listSize;
        for (int i = 0; i < listSize; i++) {
            commandHistoryList.add("find " + i);
        }
        actual.setCommandHistoryList(commandHistoryList);
        actual.setCurrentZeroBasedIndex(beyondMaxIndex);

        assertEquals("", actual.getNextCommand());
        assertEquals(listSize, actual.getCurrentZeroBasedIndex());
    }



    // Get previous and next commands when index is not at limit of command history list
    @Test
    public void getPrevCommand_atNonZeroIndex_onNonEmptyCommandList() {
        int testZeroBasedIndex = 5;

        CommandHistory actual = new CommandHistory();
        List<String> commandHistoryList = new ArrayList<>();
        for (int i = 0; i <= MAX_COMMAND_HISTORY_SIZE / 2; i++) {
            commandHistoryList.add("find " + i);
        }
        actual.setCommandHistoryList(commandHistoryList);
        actual.setCurrentZeroBasedIndex(testZeroBasedIndex);

        assertEquals("find " + (testZeroBasedIndex - 1), actual.getPrevCommand());
    }

    @Test
    public void getNextCommand_atNonMaxIndex_onNonEmptyCommandList() {
        int testZeroBasedIndex = 5;

        CommandHistory actual = new CommandHistory();
        List<String> commandHistoryList = new ArrayList<>();
        for (int i = 0; i <= MAX_COMMAND_HISTORY_SIZE / 2; i++) {
            commandHistoryList.add("find " + i);
        }
        actual.setCommandHistoryList(commandHistoryList);
        actual.setCurrentZeroBasedIndex(testZeroBasedIndex);

        assertEquals("find " + (testZeroBasedIndex + 1), actual.getNextCommand());
    }

    @Test
    public void resetCurrentIndexToBeyondMaxIndex() {
        CommandHistory actual = new CommandHistory();
        List<String> commandHistoryList = new ArrayList<>();
        for (int i = 0; i < MAX_COMMAND_HISTORY_SIZE; i++) {
            commandHistoryList.add("find " + i);
        }
        actual.setCommandHistoryList(commandHistoryList);
        actual.setCurrentZeroBasedIndex(0);
        actual.resetCurrentIndexToBeyondMaxIndex();

        assertEquals(actual.getCurrentZeroBasedIndex(), MAX_COMMAND_HISTORY_SIZE);
    }


    @Test
    public void getCommandHistoryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> commandHistory.getCommandHistoryList().remove(0));
    }

}
