package seedu.trackascholar.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.trackascholar.commons.core.GuiSettings;
import seedu.trackascholar.logic.commands.CommandResult;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the TrackAScholar.
     *
     * @see seedu.trackascholar.model.Model#getTrackAScholar()
     */
    ReadOnlyTrackAScholar getTrackAScholar();

    /** Returns an unmodifiable view of the filtered list of applicants. */
    ObservableList<Applicant> getFilteredApplicantList();

    /** Returns an unmodifiable view of the pinned list of applicants. */
    ObservableList<Applicant> getPinnedApplicantList();

    /**
     * Returns the user prefs' TrackAScholar file path.
     */
    Path getTrackAScholarFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
