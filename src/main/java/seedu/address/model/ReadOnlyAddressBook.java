package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the internships list.
     * This list will not contain any duplicate internships.
     */
    ObservableList<Internship> getInternshipList();

}
