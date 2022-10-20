package seedu.application.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.model.application.Application;
import seedu.application.model.application.interview.Interview;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Application> PREDICATE_SHOW_ALL_APPLICATIONS = unused -> true;

    Predicate<Application> PREDICATE_SHOW_APPLICATION_WITH_INTERVIEW = i -> i.getInterview().isPresent();

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
     * Returns the user prefs' application book file path.
     */
    Path getApplicationBookFilePath();

    /**
     * Sets the user prefs' application book file path.
     */
    void setApplicationBookFilePath(Path applicationBookFilePath);

    /**
     * Replaces application book data with the data in {@code applicationBook}.
     */
    void setApplicationBook(ReadOnlyApplicationBook applicationBook);

    /** Returns the ApplicationBook */
    ReadOnlyApplicationBook getApplicationBook();

    /**
     * Returns true if an application with the same identity as {@code application} exists in the application book.
     */
    boolean hasApplication(Application application);

    /**
     * Returns true if {@code application} has an interview date and time clashes with another application exists in the
     * application book.
     */
    boolean hasSameInterviewTime(Application application);

    /**
     * Returns true if {@code interview} has an interview date and time clashes with another application exists in the
     * application book.
     */
    boolean hasSameInterviewTime(Interview interview);

    /**
     * Returns true if {@code interview} has an interview date and time clashes with another application exists in the
     * application book, excluding the {@code application}. This enables the edit of the interview.
     */
    boolean hasSameInterviewTimeExcludeSelf(Interview interview, Application application);

    /**
     * Deletes the given application.
     * The application must exist in the application book.
     */
    void deleteApplication(Application target);

    /**
     * Adds the given application.
     * {@code application} must not already exist in the application book.
     */
    void addApplication(Application application);

    /**
     * Replaces the given application {@code target} with {@code editedApplication}.
     * {@code target} must exist in the application book.
     * The application identity of {@code editedApplication} must not be the same as
     * another existing application in the application book.
     */
    void setApplication(Application target, Application editedApplication);

    /** Returns an unmodifiable view of the filtered application list */
    ObservableList<Application> getFilteredApplicationList();

    /** Returns an unmodifiable view of the filtered application list with existing interview*/
    ObservableList<Application> getApplicationListWithInterview();

    /**
     * Updates the filter of the filtered application list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicationList(Predicate<Application> predicate);

    /**
     * Saves the current {@code ApplicationBook} state in the history.
     */
    void commitApplicationBook();

    /**
     * Returns true if the model has previous {@code ApplicationBook} states to restore.
     */
    boolean canUndoApplicationBook();

    /**
     * Returns true if the model has previously undone {@code ApplicationBook} states to redo.
     */
    boolean canRedoApplicationBook();

    /**
     * Restores the model's previous {@code ApplicationBook} state from the history.
     */
    void undoApplicationBook();

    /**
     * Restores the model's previously undone {@code ApplicationBook} state from the history.
     */
    void redoApplicationBook();

    /**
     * Updates the application list with interview when a new interview is added.
     */
    void updateApplicationListWithInterview();

}
