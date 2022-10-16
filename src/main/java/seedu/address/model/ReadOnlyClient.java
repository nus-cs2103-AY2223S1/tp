package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.company.Company;
import seedu.address.model.transaction.Transaction;

/**
 * Unmodifiable view of a client
 */
public interface ReadOnlyClient {

    /**
     * Returns an unmodifiable view of the companies list.
     * This list will not contain any duplicate companies.
     */
    ObservableList<Company> getCompanyList();

    /**
     * Returns an unmodifiable view of the transactions list.
     */
    ObservableList<Transaction> getTransactionList();
}
