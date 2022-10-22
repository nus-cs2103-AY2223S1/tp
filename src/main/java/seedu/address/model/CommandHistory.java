package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents User's command history.
 */
public class CommandHistory implements ReadOnlyCommandHistory {
    private List<String> commandHistoryList = new ArrayList<>();
    private int currentIndex =  0;
    private static final int MAX_COMMAND_HISTORY_SIZE = 20;

    public CommandHistory() {}

    public CommandHistory(ReadOnlyCommandHistory commandHistory) {
        this();
        setCommandHistoryList(commandHistory.getCommandHistoryList());
        currentIndex = commandHistoryList.size();
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCommandHistoryList(List<String> commandHistoryList) {
        this.commandHistoryList = commandHistoryList;
    }


    /**
     * used for testing
     * @return
     */
    @Override
    public List<String> getCommandHistoryList() {
        return commandHistoryList;
    }

    public void addToCommandHistory(String commandInput) {
        if (commandHistoryList.size() >= MAX_COMMAND_HISTORY_SIZE) {
           commandHistoryList.remove(0);
        }
        commandHistoryList.add(commandInput);
    }

    public void resetCurrentIndexToLatest() {
        // reset to past max index
        currentIndex = commandHistoryList.size();
//        System.out.println("Resetting one-based index to " + (currentIndex + 1));
    }

    public String getPrevCommand() {
        if (commandHistoryList.size() == 0) {
            return "";
        }
        if (currentIndex > 0) {
            currentIndex--;
        }
//        System.out.println("Current one-based index: " + (currentIndex + 1));
        return commandHistoryList.get(currentIndex);
    }

    public String getNextCommand() {
        int maxZeroBasedIndex = commandHistoryList.size() - 1;

        // String shown at maxZeroBasedIndex + 1 will be empty
        if (currentIndex >= maxZeroBasedIndex) {
            currentIndex = maxZeroBasedIndex + 1;
//            System.out.println("Current one-based index: " + (currentIndex + 1));
            return "";
        }

//        System.out.println("Current one-based index: " + (currentIndex + 1));
        currentIndex++;
        return commandHistoryList.get(currentIndex);
    }
}
