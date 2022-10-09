package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.poc.Poc;

/**
 * Unmodifiable view of a company
 */
public interface ReadOnlyCompany {

    /**
     * Returns an unmodifiable view of the pocs list.
     * This list will not contain any duplicate pocs.
     */
    ObservableList<Poc> getPocList();

}
