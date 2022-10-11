package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.UniqueApplicantList;

/**
 * Wraps all data at the TrackAScholar level
 * Duplicates are not allowed (by .isSameApplicant comparison)
 */
public class TrackAScholar implements ReadOnlyTrackAScholar {

    private final UniqueApplicantList applicants;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        applicants = new UniqueApplicantList();
    }

    public TrackAScholar() {}

    /**
     * Creates an TrackAScholar using the Applicants in the {@code toBeCopied}
     */
    public TrackAScholar(ReadOnlyTrackAScholar toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the applicant list with {@code applicants}.
     * {@code applicants} must not contain duplicate applicants.
     */
    public void setApplicants(List<Applicant> applicants) {
        this.applicants.setApplicants(applicants);
    }

    /**
     * Resets the existing data of this {@code TrackAScholar} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackAScholar newData) {
        requireNonNull(newData);

        setApplicants(newData.getApplicantList());
    }

    //// applicant-level operations

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in TrackAScholar.
     */
    public boolean hasApplicant(Applicant applicant) {
        requireNonNull(applicant);
        return applicants.contains(applicant);
    }

    /**
     * Adds an applicant to the TrackAScholar.
     * The applicant must not already exist in TrackAScholar.
     */
    public void addApplicant(Applicant p) {
        applicants.add(p);
    }

    /**
     * Replaces the given applicant {@code target} in the list with {@code editedApplicant}.
     * {@code target} must exist in TrackAScholar.
     * The applicant identity of {@code editedApplicant} must not be
     * the same as another existing applicant in TrackAScholar.
     */
    public void setApplicant(Applicant target, Applicant editedApplicant) {
        requireNonNull(editedApplicant);

        applicants.setApplicant(target, editedApplicant);
    }

    /**
     * Removes {@code key} from this {@code TrackAScholar}.
     * {@code key} must exist in TrackAScholar.
     */
    public void removeApplicant(Applicant key) {
        applicants.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return applicants.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Applicant> getApplicantList() {
        return applicants.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrackAScholar // instanceof handles nulls
                && applicants.equals(((TrackAScholar) other).applicants));
    }

    @Override
    public int hashCode() {
        return applicants.hashCode();
    }
}
