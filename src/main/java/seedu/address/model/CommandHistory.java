package seedu.address.model;

import java.util.List;

/**
 * Represents User's command history.
 */
public class CommandHistory implements ReadOnlyCommandHistory{
    private List<String> commandHistoryList;
    private int currentIndex;
    private static final int MAX_COMMAND_HISTORY_SIZE = 20;

    public CommandHistory(List<String> commandHistoryList) {
        this.commandHistoryList = commandHistoryList;
        currentIndex = 0;
    }

    @Override
    public List<String> getCommandHistoryList() {
        return commandHistoryList;
    }

    public void addToCommandHistory(String validCommandInput) {
        if (commandHistoryList.size() >= MAX_COMMAND_HISTORY_SIZE) {
           commandHistoryList.remove(MAX_COMMAND_HISTORY_SIZE - 1);
        }
        commandHistoryList.add(validCommandInput);
    }

    public String getPrev() {
        if (currentIndex - 1 < 0) {
            return commandHistoryList.get(0);
        }
        return commandHistoryList.get(currentIndex - 1);
    }

    public String getNext() {
        if (currentIndex - 1 < 0) {
            return commandHistoryList.get(0);
        }
        return commandHistoryList.get(currentIndex - 1);
    }
}
