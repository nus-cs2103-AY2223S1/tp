package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.poc.Poc;

/**
 * Unmodifiable view of a client
 */
public interface ReadOnlyClient {

    /**
     * Returns an unmodifiable view of the pocs list.
     * This list will not contain any duplicate pocs.
     */
    ObservableList<Poc> getPocList();

}
