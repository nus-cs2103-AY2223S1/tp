package seedu.condonery.model.property;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyPropertyDirectory {

    /**
     * Returns an unmodifiable view of the properties list.
     * This list will not contain any duplicate properties.
     */
    ObservableList<Property> getPropertyList();

}
