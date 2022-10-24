package seedu.workbook.model;

import javafx.collections.ObservableList;
import seedu.workbook.commons.core.GuiSettings;
import seedu.workbook.model.internship.Internship;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

    /** Replaces user prefs data with the data in {@code userPrefs}. */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /** Returns the user prefs. */
    ReadOnlyUserPrefs getUserPrefs();

    /** Returns the user prefs' GUI settings. */
    GuiSettings getGuiSettings();

    /** Sets the user prefs' GUI settings. */
    void setGuiSettings(GuiSettings guiSettings);

    /** Returns the user prefs' work book file path. */
    Path getWorkBookFilePath();

    /** Sets the user prefs' work book file path. */
    void setWorkBookFilePath(Path workBookFilePath);

    /** Replaces work book data with the data in {@code workBook}. */
    void setWorkBook(ReadOnlyWorkBook workBook);

    /** Returns the WorkBook. */
    ReadOnlyWorkBook getWorkBook();

    /**
     * Returns true if an Internship with the same identity as {@code internship}
     * exists in WorkBook.
     */
    boolean hasInternship(Internship internship);

    /**
     * Returns true if an Internship stage has no tips.
     */
    boolean internshipStageHasNoTips(Internship internship);

    /**
     * Deletes the given Internship.
     * The internship must exist in WorkBook.
     */
    void deleteInternship(Internship target);

    /**
     * Adds the given Internship.
     * {@code internship} must not already exist in the WorkBook.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in WorkBook.
     * The Internship identity of {@code editedInternship} must not be the same as
     * another existing internship in WorkBook.
     */
    void setInternship(Internship target, Internship editedInternship);

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Returns true if the model has undone WorkBook states to restore.
     */
    boolean canUndoWorkBook();

    /**
     * Returns true if the model has undone WorkBook states to restore.
     */
    boolean canRedoWorkBook();

    /**
     * Restores the model's WorkBook back to its previous state.
     */
    void undoWorkBook();

    /**
     * Restores the model's WorkBook to its previously undone state.
     */
    void redoWorkBook();

    /**
     * Saves the current WorkBook state for undo.
     */
    void commitWorkBook();

    /**
     * Updates the filter of the filtered internship list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);


}
