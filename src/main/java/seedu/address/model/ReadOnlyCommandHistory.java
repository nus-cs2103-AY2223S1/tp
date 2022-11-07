package seedu.address.model;

import java.util.List;

/**
 * Unmodifiable view of a command history
 *
 * @author Gerald Teo Jin Wei
 * @version 1.4
 * @since 2022-11-07
 */
public interface ReadOnlyCommandHistory {
    /**
     * Returns an unmodifiable view of the command history list.
     */
    List<String> getCommandHistoryList();
}
