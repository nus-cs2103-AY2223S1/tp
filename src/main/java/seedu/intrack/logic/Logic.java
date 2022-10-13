package seedu.intrack.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.intrack.commons.core.GuiSettings;
import seedu.intrack.logic.commands.CommandResult;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.internship.Internship;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the InTrack.
     *
     * @see seedu.intrack.model.Model#getInTrack()
     */
    ReadOnlyInTrack getInTrack();

    /** Returns an unmodifiable view of the filtered list of internships */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Returns the user prefs' internship tracker file path.
     */
    Path getInTrackFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
