package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;

/**
 * Unmodifiable view of TrackAScholar
 */
public interface ReadOnlyTrackAScholar {

    /**
     * Returns an unmodifiable view of the applicant list.
     * This list will not contain any duplicate applicants.
     */
    ObservableList<Applicant> getApplicantList();

}
