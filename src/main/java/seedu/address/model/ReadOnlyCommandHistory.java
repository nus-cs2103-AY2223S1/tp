package seedu.address.model;

import java.util.List;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyCommandHistory {
    /**
     * Returns an unmodifiable view of the command history list.
     * This list will not contain any duplicate persons.
     */
    List<String> getCommandHistoryList();
}
