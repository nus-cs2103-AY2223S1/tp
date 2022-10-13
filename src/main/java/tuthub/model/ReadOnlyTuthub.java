package tuthub.model;

import javafx.collections.ObservableList;
import tuthub.model.tutor.Tutor;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTuthub {

    /**
     * Returns an unmodifiable view of the tutors list.
     * This list will not contain any duplicate tutors.
     */
    ObservableList<Tutor> getTutorList();

}
