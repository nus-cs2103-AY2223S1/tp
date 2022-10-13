package seedu.intrack.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.intrack.commons.core.GuiSettings;
import seedu.intrack.model.internship.Internship;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

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
     * Returns the user prefs' internship tracker file path.
     */
    Path getInTrackFilePath();

    /**
     * Sets the user prefs' internship tracker file path.
     */
    void setInTrackFilePath(Path inTrackFilePath);

    /**
     * Replaces internship tracker data with the data in {@code inTrack}.
     */
    void setInTrack(ReadOnlyInTrack inTrack);

    /** Returns the InTrack */
    ReadOnlyInTrack getInTrack();

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in the internship tracker.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in the internship tracker.
     */
    void deleteInternship(Internship target);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in the internship tracker.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in the internship tracker.
     * The internship identity of {@code editedInternship} must not be the same as another existing internship in the
     * internship tracker.
     */
    void setInternship(Internship target, Internship editedInternship);

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);
}
