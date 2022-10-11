package seedu.condonery.model;

import javafx.collections.ObservableList;
import seedu.condonery.model.client.Client;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyClientDirectory {

    /**
     * Returns an unmodifiable view of the properties list.
     * This list will not contain any duplicate properties.
     */
    ObservableList<Client> getClientList();

}
