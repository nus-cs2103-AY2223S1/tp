package seedu.address.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents User's command history.
 *
 * @author Gerald Teo Jin Wei
 * @version 1.4
 * @since 2022-11-07
 */
public class CommandHistory implements ReadOnlyCommandHistory {
    public static final int MAX_COMMAND_HISTORY_SIZE = 20;
    private List<String> commandHistoryList = new ArrayList<>();
    private int currentIndex = 0;

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

    /**
     * Add latest valid command entered by user to CommandHistory
     * @param commandInput latest valid command entered bu user
     */
    public void addToCommandHistory(String commandInput) {
        int size = commandHistoryList.size();
        int latestIndex = size - 1;

        // prevent saving of consecutive duplicate commands
        if (!commandHistoryList.isEmpty()
                && commandHistoryList.get(latestIndex).equals(commandInput)) {
            resetCurrentIndexToBeyondMaxIndex();
            return;
        }
        if (size >= MAX_COMMAND_HISTORY_SIZE) {
            commandHistoryList.remove(0);
        }
        commandHistoryList.add(commandInput);
        resetCurrentIndexToBeyondMaxIndex();
    }

    /**
     * Gets the previous command in the command history list
     * @return String of previous command
     */
    public String getPrevCommand() {
        if (commandHistoryList.size() == 0) {
            return "";
        }
        if (currentIndex > 0) {
            currentIndex--;
        }
        return commandHistoryList.get(currentIndex);
    }

    /**
     * Gets the next command in the command history list
     * @return String of next command
     */
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

    /**
     * Reset current index to its max index + 1
     */
    public void resetCurrentIndexToBeyondMaxIndex() {
        // reset to max index + 1 to show empty string
        currentIndex = commandHistoryList.size();
    }

    /**
     * Gets the current index
     * @return current index of command history
     */
    public int getCurrentZeroBasedIndex() {
        return currentIndex;
    }

    /**
     * Sets the current index to a new value
     * @param i new value to set the current index to
     * @return current index of command history
     */
    public int setCurrentZeroBasedIndex(int i) {
        return currentIndex = i;
    }

    /**
     * Returns an unmodifiable command history list
     * @return Unmodifiable commandhistory list
     */
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
