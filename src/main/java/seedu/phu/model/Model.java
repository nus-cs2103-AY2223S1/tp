package seedu.phu.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.phu.commons.core.GuiSettings;
import seedu.phu.model.internship.ComparableCategory;
import seedu.phu.model.internship.Internship;

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
     * Returns the user prefs' internship book file path.
     */
    Path getInternshipBookFilePath();

    /**
     * Sets the user prefs' internship book file path.
     */
    void setInternshipBookFilePath(Path internshipBookFilePath);

    /**
     * Replaces internship book data with the data in {@code internshipBook}.
     */
    void setInternshipBook(ReadOnlyInternshipBook internshipBook);

    /** Returns the InternshipBook */
    ReadOnlyInternshipBook getInternshipBook();

    /**
     * Returns true if a internship with the same identity as {@code internship} exists in the internship book.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given internship.
     * The internship must exist in the internship book.
     */
    void deleteInternship(Internship target);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in the internship book.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in the internship book.
     * The internship identity of {@code editedInternship} must not be the same as
     * another existing internship in the internship book.
     */
    void setInternship(Internship target, Internship editedInternship);

    /** Returns an unmodifiable view of the filtered internship list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered internship list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);


    void sortList(ComparableCategory category);

    void reverseList();

    /**
     * Updates the filter of the filtered internship list to only show internshipToView.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void viewInternship(Internship internshipToView);
}
