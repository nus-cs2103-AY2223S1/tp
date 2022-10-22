package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.remark.Remark;
import seedu.address.model.transaction.Transaction;

/**
 * Unmodifiable view of a client
 */
public interface ReadOnlyClient {

    /**
     * Returns an unmodifiable view of the companies list.
     * This list will not contain any duplicate companies.
     */
    ObservableList<Remark> getRemarkList();

    /**
     * Returns an unmodifiable view of the transactions list.
     */
    ObservableList<Transaction> getTransactionList();
}
