package seedu.address.logic;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class used to keep track of the recent commands made by user.
 */
public class CommandHistory {
    private final Integer maxCapacity = 10;
    private Integer currentIndex = 0;
    private final List<String> history = new ArrayList<>(maxCapacity);

    /**
     * Adds the command text into the history and removes the furthest text if applicable.
     *
     * @param command The command text to be added.
     */
    public void add(String command) {
        requireNonNull(command);
        if (history.size() == maxCapacity) {
            history.remove(0);
        }
        history.add(command);
        currentIndex = history.size();
    }

    public String getDecrementCommand() {
        currentIndex -= 1;
        if (currentIndex < 0) {
            currentIndex = 0;
        }
        return history.isEmpty() ? "" : history.get(currentIndex);
    }

    public String getIncrementCommand() {
        currentIndex += 1;
        if (currentIndex >= history.size()) {
            currentIndex = history.size() - 1;
        }
        return history.isEmpty() ? "" : history.get(currentIndex);
    }

    public String getHistoryString() {
        List<String> list = new ArrayList<>(history);
        Collections.reverse(list);
        return String.join("\n", list);
    }

    /**
     * Resets the index to the last element of the list when starting up the application.
     *
     * @return The commandHistory with the index reset.
     */
    public CommandHistory resetIndex() {
        if (history.size() >= 1) {
            this.currentIndex = history.size();
        }
        return this;
    }

    public List<String> getHistory() {
        return history;
    }
}
