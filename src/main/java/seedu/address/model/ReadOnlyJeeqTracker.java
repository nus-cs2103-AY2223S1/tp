package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.client.Client;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyJeeqTracker {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

}
