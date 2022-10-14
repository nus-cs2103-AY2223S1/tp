package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.client.Company;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyJeeqTracker {

    /**
     * Returns an unmodifiable view of the companies list.
     * This list will not contain any duplicate companies.
     */
    ObservableList<Company> getCompanyList();

}
