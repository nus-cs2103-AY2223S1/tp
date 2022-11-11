package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.buyer.Buyer;

/**
 * Unmodifiable view of a Buyer list
 */
public interface ReadOnlyBuyerBook {

    /**
     * Returns an unmodifiable view of the buyers' list.
     * This list will not contain any duplicate buyers.
     */
    ObservableList<Buyer> getBuyerList();

}
