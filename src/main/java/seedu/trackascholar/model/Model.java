package seedu.trackascholar.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.trackascholar.commons.core.GuiSettings;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.ApplicationStatus;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;
    Predicate<Applicant> PREDICATE_SHOW_PINNED_APPLICANTS = applicant -> applicant.getHasPinned();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' TrackAScholar file path.
     */
    Path getTrackAScholarFilePath();

    /**
     * Sets the user prefs' TrackAScholar file path.
     */
    void setTrackAScholarFilePath(Path trackAScholarFilePath);

    /**
     * Replaces TrackAScholar data with the data in {@code TrackAScholar}.
     */
    void setTrackAScholar(ReadOnlyTrackAScholar trackAScholarData);

    /** Returns the TrackAScholar. */
    ReadOnlyTrackAScholar getTrackAScholar();

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in TrackAScholar.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Returns the applicant with the same identity as {@code applicant}.
     * The applicant must exist in TrackAScholar.
     */
    Applicant findSimilarApplicant(Applicant applicant);

    /**
     * Appends the imported {@code applicantList} to the current applicant list stored in TrackAScholar.
     * Duplicate applicants conflict is resolved by replacing the current applicant in TrackAScholar with
     * the data of the imported applicant.
     */
    void importWithReplace(ObservableList<Applicant> applicantList);

    /**
     * Appends the imported {@code applicantList} to the current applicant list stored in TrackAScholar.
     * Duplicate applicants conflict is resolved by keeping the current applicant in TrackAScholar and
     * disregarding the imported applicant data.
     */
    void importWithoutReplace(ObservableList<Applicant> applicantList);

    /**
     * Deletes the given applicant.
     * The applicant must exist in TrackAScholar.
     */
    void deleteApplicant(Applicant target);

    /**
     * Removes all applicants whose application status is completed (accepted/pending).
     */
    void removeApplicant(ApplicationStatus applicationStatus);

    /**
     * Adds the given applicant.
     * {@code applicant} must not already exist in TrackAScholar.
     */
    void addApplicant(Applicant applicant);

    /**
     * Replaces the given applicant {@code target} with {@code editedApplicant}.
     * {@code target} must exist in TrackAScholar.
     * The applicant identity of {@code editedApplicant} must not be
     * the same as another existing applicant in TrackAScholar.
     */
    void setApplicant(Applicant target, Applicant editedApplicant);

    /**
     * Sorts the applicant list with the given {@code comparator}.
     */
    void sortApplicants(Comparator<Applicant> comparator);

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /** Returns an unmodifiable view of the pinned applicant list */
    ObservableList<Applicant> getPinnedApplicantList();

    /** Returns an unmodifiable view of all applicants */
    ObservableList<Applicant> getAllApplicants();

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);
}
