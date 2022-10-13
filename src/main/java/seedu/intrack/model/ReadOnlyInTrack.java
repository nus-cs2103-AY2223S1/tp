package seedu.intrack.model;

import javafx.collections.ObservableList;
import seedu.intrack.model.internship.Internship;

/**
 * Unmodifiable view of an internship tracker.
 */
public interface ReadOnlyInTrack {

    /**
     * Returns an unmodifiable view of the internships list.
     * This list will not contain any duplicate internships.
     */
    ObservableList<Internship> getInternshipList();

}
