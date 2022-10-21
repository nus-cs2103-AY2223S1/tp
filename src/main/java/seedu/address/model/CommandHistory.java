package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents User's command history.
 */
public class CommandHistory implements ReadOnlyCommandHistory{
    private List<String> commandHistoryList = new ArrayList<>();
    private int currentIndex = 0;
    private static final int MAX_COMMAND_HISTORY_SIZE = 20;

    public CommandHistory() {}

    public CommandHistory(ReadOnlyCommandHistory toBeCopied) {
        this.commandHistoryList = commandHistoryList;
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCommandHistoryList(List<String> commandHistoryList) {
        this.commandHistoryList = commandHistoryList;
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyCommandHistory newData) {
        requireNonNull(newData);

        setCommandHistoryList(newData.getCommandHistoryList());
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
        if (currentIndex <= 0) {
            return commandHistoryList.get(0);
        }
        currentIndex--;
        return commandHistoryList.get(currentIndex);
    }

    public String getNext() {
        if (currentIndex + 1 >= MAX_COMMAND_HISTORY_SIZE) {
            return commandHistoryList.get(0);
        }
        currentIndex++;
        return commandHistoryList.get(currentIndex);
    }
}
