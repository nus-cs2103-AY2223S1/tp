package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents User's command history.
 */
public class CommandHistory implements ReadOnlyCommandHistory {
    private List<String> commandHistoryList = new ArrayList<>();
    private int currentIndex = 0;
    public static final int MAX_COMMAND_HISTORY_SIZE = 20;

    public CommandHistory() {
    }

    /**
     * Creates a CommandHistory using the CommandHistoryList in the {@code toBeCopied}
     */
    public CommandHistory(ReadOnlyCommandHistory toBeCopied) {
        this();
        setCommandHistoryList(new ArrayList<>(toBeCopied.getCommandHistoryList()));
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the commandHistorylist with {@code commandHistoryList}.
     */
    public void setCommandHistoryList(List<String> commandHistoryList) {
        this.commandHistoryList = commandHistoryList;
        resetCurrentIndexToBeyondMaxIndex();
    }


    // command level operations
    public void addToCommandHistory(String commandInput) {
        if (commandHistoryList.size() >= MAX_COMMAND_HISTORY_SIZE) {
            commandHistoryList.remove(0);
        }
        commandHistoryList.add(commandInput);
        resetCurrentIndexToBeyondMaxIndex();
    }

    public String getPrevCommand() {
        if (commandHistoryList.size() == 0) {
            return "";
        }
        if (currentIndex > 0) {
            currentIndex--;
        }
        return commandHistoryList.get(currentIndex);
    }

    public String getNextCommand() {
        int maxZeroBasedIndex = commandHistoryList.size() - 1;

        // String shown at maxZeroBasedIndex + 1 will be empty
        if (currentIndex >= maxZeroBasedIndex) {
            currentIndex = maxZeroBasedIndex + 1;
            return "";
        }

        currentIndex++;
        return commandHistoryList.get(currentIndex);
    }

    // util methods
    public void resetCurrentIndexToBeyondMaxIndex() {
        // reset to max index + 1 to show empty string
        currentIndex = commandHistoryList.size();
    }

    public int getCurrentZeroBasedIndex() {
        return currentIndex;
    }

    public int setCurrentZeroBasedIndex(int i) {
        return currentIndex = i;
    }

    @Override
    public List<String> getCommandHistoryList() {
        return Collections.unmodifiableList(commandHistoryList);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandHistory // instanceof handles nulls
                && commandHistoryList.equals(((CommandHistory) other).commandHistoryList))
                && currentIndex == (((CommandHistory) other).currentIndex);
    }

    @Override
    public int hashCode() {
        return commandHistoryList.hashCode();
    }
}
