package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.applicant.Applicant;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_APPLICANTS = unused -> true;

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

    /** Returns the TrackAScholar */
    ReadOnlyTrackAScholar getTrackAScholar();

    /**
     * Returns true if an applicant with the same identity as {@code applicant} exists in TrackAScholar.
     */
    boolean hasApplicant(Applicant applicant);

    /**
     * Deletes the given applicant.
     * The applicant must exist in TrackAScholar.
     */
    void deleteApplicant(Applicant target);

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

    /** Returns an unmodifiable view of the filtered applicant list */
    ObservableList<Applicant> getFilteredApplicantList();

    /**
     * Updates the filter of the filtered applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicantList(Predicate<Applicant> predicate);
}
