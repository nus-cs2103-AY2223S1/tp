package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.company.Company;

/**
 * Unmodifiable view of a client
 */
public interface ReadOnlyClient {

    /**
     * Returns an unmodifiable view of the companies list.
     * This list will not contain any duplicate companies.
     */
    ObservableList<Company> getCompanyList();

}
