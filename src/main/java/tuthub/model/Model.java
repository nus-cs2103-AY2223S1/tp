package tuthub.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import tuthub.commons.core.GuiSettings;
import tuthub.model.tutor.Tutor;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Tutor> PREDICATE_SHOW_ALL_TUTORS = unused -> true;

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
     * Returns the user prefs' tuthub file path.
     */
    Path getTuthubFilePath();

    /**
     * Sets the user prefs' tuthub file path.
     */
    void setTuthubFilePath(Path tuthubFilePath);

    /**
     * Replaces tuthub data with the data in {@code tuthub}.
     */
    void setTuthub(ReadOnlyTuthub tuthub);

    /** Returns the Tuthub */
    ReadOnlyTuthub getTuthub();

    /**
     * Returns true if a tutor with the same identity as {@code tutor} exists in the tuthub.
     */
    boolean hasTutor(Tutor tutor);

    /**
     * Deletes the given tutor.
     * The tutor must exist in the tuthub.
     */
    void deleteTutor(Tutor target);

    /**
     * Adds the given tutor.
     * {@code tutor} must not already exist in the tuthub.
     */
    void addTutor(Tutor tutor);

    /**
     * Replaces the given tutor {@code target} with {@code editedTutor}.
     * {@code target} must exist in the tuthub.
     * The tutor identity of {@code editedTutor} must not be the same as another existing tutor in the tuthub.
     */
    void setTutor(Tutor target, Tutor editedTutor);

    /** Returns an unmodifiable view of the filtered tutor list */
    ObservableList<Tutor> getFilteredTutorList();

    /**
     * Updates the filter of the filtered tutor list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTutorList(Predicate<Tutor> predicate);
}
