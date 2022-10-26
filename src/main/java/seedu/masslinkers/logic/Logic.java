package seedu.masslinkers.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.masslinkers.commons.core.GuiSettings;
import seedu.masslinkers.logic.commands.CommandResult;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.ReadOnlyMassLinkers;
import seedu.masslinkers.model.student.Student;

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
     * Returns the MassLinkers.
     *
     * @see seedu.masslinkers.model.Model#getMassLinkers()
     */
    ReadOnlyMassLinkers getMassLinkers();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns the user prefs' mass linkers file path.
     */
    Path getMassLinkersFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}
