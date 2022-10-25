package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;

/**
 * Unmodifiable view of a Buyer list
 */
public interface ReadOnlyPersonBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Buyer> getPersonList();

}
