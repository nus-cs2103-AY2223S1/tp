package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    }

    @Test
    public void addToFullCommandHistoryList() {

    }

    @Test
    public void getPrevCommand_onEmptyCommandList() {

    }

    @Test
    public void getNextCommand_onEmptyCommandList() {

    }

    // Get previous and next commands when index is at limit of command history list
    @Test
    public void getPrevCommand_atZeroIndex_onNonEmptyCommandList() {

    }

    @Test
    public void getPrevCommand_atNonZeroIndex_onNonEmptyCommandList() {

    }

    // Get previous and next commands when index is not at limit of command history list
    @Test
    public void getNextCommand_atMaxIndex_onNonEmptyCommandList() {

    }

    @Test
    public void getNextCommand_atNonMaxIndex_onNonEmptyCommandList() {

    }

    @Test
    public void resetCurrentIndexToBeyondMaxIndex() {

    }


    @Test
    public void getCommandHistoryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> commandHistory.getCommandHistoryList().remove(0));
    }

}
