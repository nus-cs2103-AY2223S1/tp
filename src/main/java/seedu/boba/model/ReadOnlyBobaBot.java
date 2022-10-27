package seedu.boba.model;

import javafx.collections.ObservableList;
import seedu.boba.model.customer.Customer;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyBobaBot {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Customer> getPersonList();

}
