package seedu.address.model;

import java.util.List;

/**
 * Unmodifiable view of a command history
 */
public interface ReadOnlyCommandHistory {
    /**
     * Returns an unmodifiable view of the command history list.
     */
    List<String> getCommandHistoryList();
}
